package br.com.netbox.netbox_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.netbox.netbox_api.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}