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
        if (rack.getSite() == null || rack.getSite().getId() == null) {
            throw new IllegalArgumentException("O ID do Site é obrigatório para criar um Rack.");
        }

        Site site = siteRepository.findById(rack.getSite().getId())
                .orElseThrow(() -> new EntityNotFoundException("Site com id " + rack.getSite().getId() + " não encontrado."));

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
}

