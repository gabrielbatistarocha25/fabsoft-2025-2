package br.com.netbox.netbox_api.controller;

import br.com.netbox.netbox_api.model.Location;
import br.com.netbox.netbox_api.model.Site;
import br.com.netbox.netbox_api.service.OrganizationService;
import jakarta.validation.Valid;
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

    // --- Endpoints para Locations ---
    @PostMapping("/locations")
    public ResponseEntity<Location> createLocation(@Valid @RequestBody Location location) {
        Location newLocation = organizationService.createLocation(location);
        return new ResponseEntity<>(newLocation, HttpStatus.CREATED);
    }

    @GetMapping("/locations")
    public ResponseEntity<List<Location>> getAllLocations() {
        List<Location> locations = organizationService.getAllLocations();
        return ResponseEntity.ok(locations);
    }

    @GetMapping("/locations/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable Long id) {
        return ResponseEntity.ok(organizationService.getLocationById(id));
    }

    // --- Endpoints para Sites ---
    @PostMapping("/sites")
    public ResponseEntity<Site> createSite(@Valid @RequestBody Site site) {
        Site newSite = organizationService.createSite(site);
        return new ResponseEntity<>(newSite, HttpStatus.CREATED);
    }

    @GetMapping("/sites")
    public ResponseEntity<List<Site>> getAllSites() {
        List<Site> sites = organizationService.getAllSites();
        return ResponseEntity.ok(sites);
    }

    @GetMapping("/sites/{id}")
    public ResponseEntity<Site> getSiteById(@PathVariable Long id) {
        return ResponseEntity.ok(organizationService.getSiteById(id));
    }
}

