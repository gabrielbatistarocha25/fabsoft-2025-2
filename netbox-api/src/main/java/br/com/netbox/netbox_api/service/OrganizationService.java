package br.com.netbox.netbox_api.service;

import br.com.netbox.netbox_api.model.Location;
import br.com.netbox.netbox_api.model.Site;
import br.com.netbox.netbox_api.repository.LocationRepository;
import br.com.netbox.netbox_api.repository.SiteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Site createSite(Site site) {
        if (site.getLocation() == null || site.getLocation().getId() == null) {
            throw new IllegalArgumentException("O ID da Localização é obrigatório.");
        }
        Location location = locationRepository.findById(site.getLocation().getId())
                .orElseThrow(() -> new EntityNotFoundException("Localização com id " + site.getLocation().getId() + " não encontrada."));
        site.setLocation(location);
        return siteRepository.save(site);
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public List<Site> getAllSites() {
        return siteRepository.findAll();
    }
}
