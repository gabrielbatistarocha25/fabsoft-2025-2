package br.com.netbox.netbox_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.netbox.netbox_api.model.Vlan; // Importar List

@Repository
public interface VlanRepository extends JpaRepository<Vlan, Long> {

    // Novo método para encontrar todas as VLANs associadas a um ID de site específico
    List<Vlan> findBySiteId(Long siteId);
}
