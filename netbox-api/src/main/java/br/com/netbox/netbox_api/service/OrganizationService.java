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

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Location getLocationById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Localização com id " + id + " não encontrada."));
    }

    public Location updateLocation(Long id, Location locationDetails) {
        Location existingLocation = locationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Localização com id " + id + " não encontrada."));

        existingLocation.setName(locationDetails.getName());
        existingLocation.setAddress(locationDetails.getAddress());

        return locationRepository.save(existingLocation);
    }

    public void deleteLocation(Long id) {
        if (!locationRepository.existsById(id)) {
            throw new EntityNotFoundException("Localização com id " + id + " não encontrada.");
        }
         if (!siteRepository.findByLocationId(id).isEmpty()) {
            throw new IllegalStateException("Não é possível deletar localização pois ela contém sites.");
            }
        locationRepository.deleteById(id);
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

    public List<Site> getAllSites() {
        return siteRepository.findAll();
    }

    public Site getSiteById(Long id) {
        return siteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Site com id " + id + " não encontrado."));
    }

    public Site updateSite(Long id, Site siteDetails) {
        Site existingSite = siteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Site com id " + id + " não encontrado."));

        if (siteDetails.getLocation() == null || siteDetails.getLocation().getId() == null) {
            throw new IllegalArgumentException("O ID da Localização é obrigatório.");
        }
        Location location = locationRepository.findById(siteDetails.getLocation().getId())
                .orElseThrow(() -> new EntityNotFoundException("Localização com id " + siteDetails.getLocation().getId() + " não encontrada."));
        
        existingSite.setName(siteDetails.getName());
        existingSite.setLocation(location);
        
        return siteRepository.save(existingSite);
    }

    public void deleteSite(Long id) {
        if (!siteRepository.existsById(id)) {
            throw new EntityNotFoundException("Site com id " + id + " não encontrado.");
        }
        siteRepository.deleteById(id);
    }
}