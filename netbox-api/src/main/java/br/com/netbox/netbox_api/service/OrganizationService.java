package br.com.netbox.netbox_api.service;

// IMPORTS CORRIGIDOS COM O NOVO PACOTE
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.netbox.netbox_api.model.Location;
import br.com.netbox.netbox_api.model.Site;
import br.com.netbox.netbox_api.repository.LocationRepository;
import br.com.netbox.netbox_api.repository.SiteRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class OrganizationService {

    private final LocationRepository locationRepository;
    private final SiteRepository siteRepository;

    public OrganizationService(LocationRepository locationRepository, SiteRepository siteRepository) {
        this.locationRepository = locationRepository;
        this.siteRepository = siteRepository;
    }

    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Location getLocationById(Long id) {
        return locationRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Location não encontrada com id: " + id));
    }

    public Site createSite(Site site) {
        Location location = locationRepository.findById(site.getLocation().getId())
            .orElseThrow(() -> new EntityNotFoundException("Para criar um Site, é preciso fornecer uma Location válida com id: " + site.getLocation().getId()));
        site.setLocation(location);
        return siteRepository.save(site);
    }

    public List<Site> getAllSites() {
        return siteRepository.findAll();
    }

    public Site getSiteById(Long id) {
        return siteRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Site não encontrado com id: " + id));
    }
}

