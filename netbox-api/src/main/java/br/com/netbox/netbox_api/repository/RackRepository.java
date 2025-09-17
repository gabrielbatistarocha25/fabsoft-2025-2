package br.com.netbox.netbox_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.netbox.netbox_api.model.Rack;

@Repository
public interface RackRepository extends JpaRepository<Rack, Long> {
}