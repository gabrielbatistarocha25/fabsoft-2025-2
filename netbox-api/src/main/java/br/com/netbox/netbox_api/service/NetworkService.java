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
        Site site = siteRepository.findById(getSiteIdFromVlan(vlan))
                .orElseThrow(() -> new EntityNotFoundException("Site com id " + getSiteIdFromVlan(vlan) + " não encontrado."));
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

    public Vlan getVlanById(Long id) {
        return vlanRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("VLAN com id " + id + " não encontrada."));
    }

    public Vlan updateVlan(Long id, Vlan vlanDetails) {
        Vlan existingVlan = getVlanById(id);

        Site site = siteRepository.findById(getSiteIdFromVlan(vlanDetails))
                .orElseThrow(() -> new EntityNotFoundException("Site com id " + getSiteIdFromVlan(vlanDetails) + " não encontrado."));

        existingVlan.setName(vlanDetails.getName());
        existingVlan.setVlanId(vlanDetails.getVlanId());
        existingVlan.setSite(site);

        return vlanRepository.save(existingVlan);
    }

    public void deleteVlan(Long id) {
        if (!vlanRepository.existsById(id)) {
            throw new EntityNotFoundException("VLAN com id " + id + " não encontrada.");
        }
        vlanRepository.deleteById(id);
    }

    private Long getSiteIdFromVlan(Vlan vlan) {
        if (vlan.getSite() == null || vlan.getSite().getId() == null) {
            throw new IllegalArgumentException("O ID do Site é obrigatório para criar ou atualizar uma VLAN.");
        }
        return vlan.getSite().getId();
    }
}