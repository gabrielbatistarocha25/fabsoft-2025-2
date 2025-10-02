package br.com.netbox.netbox_api.service;

import br.com.netbox.netbox_api.model.Site;
import br.com.netbox.netbox_api.model.Vlan;
import br.com.netbox.netbox_api.repository.SiteRepository;
import br.com.netbox.netbox_api.repository.VlanRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NetworkService {

    private final VlanRepository vlanRepository;
    private final SiteRepository siteRepository;

    public NetworkService(VlanRepository vlanRepository, SiteRepository siteRepository) {
        this.vlanRepository = vlanRepository;
        this.siteRepository = siteRepository;
    }

    public Vlan createVlan(Vlan vlan) {
        if (vlan.getSite() == null || vlan.getSite().getId() == null) {
            throw new IllegalArgumentException("O ID do Site é obrigatório para criar uma VLAN.");
        }
        Site site = siteRepository.findById(vlan.getSite().getId())
                .orElseThrow(() -> new EntityNotFoundException("Site com id " + vlan.getSite().getId() + " não encontrado."));

        vlan.setSite(site);
        return vlanRepository.save(vlan);
    }

    public List<Vlan> getAllVlans() {
        return vlanRepository.findAll();
    }

    public List<Vlan> getVlansBySite(Long siteId) {
        if (!siteRepository.existsById(siteId)) {
            throw new EntityNotFoundException("Site com id " + siteId + " não encontrado.");
        }
        return vlanRepository.findBySiteId(siteId);
    }
}

