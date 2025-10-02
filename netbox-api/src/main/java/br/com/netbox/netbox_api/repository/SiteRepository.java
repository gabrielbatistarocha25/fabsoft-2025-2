package br.com.netbox.netbox_api.repository;

import br.com.netbox.netbox_api.model.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {}
