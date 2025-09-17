package br.com.netbox.netbox_api.repository;

import org.springframework.data.jpa.repository.JpaRepository; // IMPORT CORRETO
import org.springframework.stereotype.Repository;

import br.com.netbox.netbox_api.model.Site;

@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {
}