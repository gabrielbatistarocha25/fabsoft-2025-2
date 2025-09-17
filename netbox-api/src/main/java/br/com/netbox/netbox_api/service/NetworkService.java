package br.com.netbox.netbox_api.service;

import br.com.netbox.netbox_api.model.Site;
import br.com.netbox.netbox_api.model.Vlan;
import br.com.netbox.netbox_api.repository.SiteRepository;
import br.com.netbox.netbox_api.repository.VlanRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List; // Importação correta com 'L' maiúsculo

@Service // Anotação para indicar que esta é uma classe de serviço gerenciada pelo Spring
public class NetworkService {

    private final VlanRepository vlanRepository;
    private final SiteRepository siteRepository;

    // Injeção de dependência via construtor (melhor prática)
    public NetworkService(VlanRepository vlanRepository, SiteRepository siteRepository) {
        this.vlanRepository = vlanRepository;
        this.siteRepository = siteRepository;
    }

    public Vlan createVlan(Vlan vlan) {
        // Valida se o ID do site foi fornecido
        if (vlan.getSite() == null || vlan.getSite().getId() == null) {
            throw new IllegalArgumentException("O ID do Site é obrigatório para criar uma VLAN.");
        }
        // Garante que o site associado à VLAN existe
        Site site = siteRepository.findById(vlan.getSite().getId())
                .orElseThrow(() -> new EntityNotFoundException("Site com id " + vlan.getSite().getId() + " não encontrado."));

        vlan.setSite(site);
        return vlanRepository.save(vlan);
    }

    public List<Vlan> getAllVlans() {
        return vlanRepository.findAll();
    }

    // Método para futuras consultas
    public List<Vlan> getVlansBySite(Long siteId) {
        if (!siteRepository.existsById(siteId)) {
            throw new EntityNotFoundException("Site com id " + siteId + " não encontrado.");
        }
        // Este método ainda precisa ser criado no VlanRepository
        return vlanRepository.findBySiteId(siteId);
    }
}

