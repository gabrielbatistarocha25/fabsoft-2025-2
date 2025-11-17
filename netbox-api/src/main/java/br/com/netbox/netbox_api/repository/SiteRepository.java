package br.com.netbox.netbox_api.repository;

import br.com.netbox.netbox_api.model.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List; 

@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {

    List<Site> findByLocationId(Long locationId);
}