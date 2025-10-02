package br.com.netbox.netbox_api.repository;

import br.com.netbox.netbox_api.model.Vlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VlanRepository extends JpaRepository<Vlan, Long> {

    List<Vlan> findBySiteId(Long siteId);
}

