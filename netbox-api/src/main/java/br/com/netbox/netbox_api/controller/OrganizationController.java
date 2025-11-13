package br.com.netbox.netbox_api.controller;

import br.com.netbox.netbox_api.model.Location;
import br.com.netbox.netbox_api.model.Site;
import br.com.netbox.netbox_api.service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/organization")
public class OrganizationController {
    
    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping("/locations")
    public ResponseEntity<Location> createLocation(@RequestBody Location location) {
        return new ResponseEntity<>(organizationService.createLocation(location), HttpStatus.CREATED);
    }

    @GetMapping("/locations")
    public ResponseEntity<List<Location>> getAllLocations() {
        return ResponseEntity.ok(organizationService.getAllLocations());
    }

    @GetMapping("/locations/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable Long id) {
        return ResponseEntity.ok(organizationService.getLocationById(id));
    }

    @PutMapping("/locations/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable Long id, @RequestBody Location locationDetails) {
        return ResponseEntity.ok(organizationService.updateLocation(id, locationDetails));
    }

    @DeleteMapping("/locations/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        organizationService.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/sites")
    public ResponseEntity<Site> createSite(@RequestBody Site site) {
        return new ResponseEntity<>(organizationService.createSite(site), HttpStatus.CREATED);
    }
    
    @GetMapping("/sites")
    public ResponseEntity<List<Site>> getAllSites() {
        return ResponseEntity.ok(organizationService.getAllSites());
    }

    @GetMapping("/sites/{id}")
    public ResponseEntity<Site> getSiteById(@PathVariable Long id) {
        return ResponseEntity.ok(organizationService.getSiteById(id));
    }

    @PutMapping("/sites/{id}")
    public ResponseEntity<Site> updateSite(@PathVariable Long id, @RequestBody Site siteDetails) {
        return ResponseEntity.ok(organizationService.updateSite(id, siteDetails));
    }

    @DeleteMapping("/sites/{id}")
    public ResponseEntity<Void> deleteSite(@PathVariable Long id) {
        organizationService.deleteSite(id);
        return ResponseEntity.noContent().build();
    }
}