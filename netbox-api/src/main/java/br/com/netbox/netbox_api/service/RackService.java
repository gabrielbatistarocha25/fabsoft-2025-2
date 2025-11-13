package br.com.netbox.netbox_api.service;

import java.util.List;
import org.springframework.stereotype.Service;
import br.com.netbox.netbox_api.model.Rack;
import br.com.netbox.netbox_api.model.Site;
import br.com.netbox.netbox_api.repository.RackRepository;
import br.com.netbox.netbox_api.repository.SiteRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class RackService {

    private final RackRepository rackRepository;
    private final SiteRepository siteRepository;

    public RackService(RackRepository rackRepository, SiteRepository siteRepository) {
        this.rackRepository = rackRepository;
        this.siteRepository = siteRepository;
    }

    public Rack createRack(Rack rack) {
        Site site = siteRepository.findById(getSiteIdFromRack(rack))
                .orElseThrow(() -> new EntityNotFoundException("Site com id " + getSiteIdFromRack(rack) + " não encontrado."));
        rack.setSite(site);
        return rackRepository.save(rack);
    }

    public List<Rack> getAllRacks() {
        return rackRepository.findAll();
    }

    public Rack getRackById(Long id) {
        return rackRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rack com id " + id + " não encontrado."));
    }

    public Rack updateRack(Long id, Rack rackDetails) {
        Rack existingRack = getRackById(id); // Reusa o método que já lança exceção

        Site site = siteRepository.findById(getSiteIdFromRack(rackDetails))
                .orElseThrow(() -> new EntityNotFoundException("Site com id " + getSiteIdFromRack(rackDetails) + " não encontrado."));

        existingRack.setName(rackDetails.getName());
        existingRack.setuHeight(rackDetails.getuHeight());
        existingRack.setSite(site);

        return rackRepository.save(existingRack);
    }

    public void deleteRack(Long id) {
        if (!rackRepository.existsById(id)) {
            throw new EntityNotFoundException("Rack com id " + id + " não encontrado.");
        }
        rackRepository.deleteById(id);
    }

    private Long getSiteIdFromRack(Rack rack) {
        if (rack.getSite() == null || rack.getSite().getId() == null) {
            throw new IllegalArgumentException("O ID do Site é obrigatório para criar ou atualizar um Rack.");
        }
        return rack.getSite().getId();
    }
}