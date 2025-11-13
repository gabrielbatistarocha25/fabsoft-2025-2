package br.com.netbox.netbox_api.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; // Importação alterada para wildcard
import br.com.netbox.netbox_api.model.Vlan;
import br.com.netbox.netbox_api.service.NetworkService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/vlans")
public class NetworkController {

    private final NetworkService networkService;

    public NetworkController(NetworkService networkService) {
        this.networkService = networkService;
    }

    @PostMapping
    public ResponseEntity<Vlan> createVlan(@Valid @RequestBody Vlan vlan) {
        Vlan createdVlan = networkService.createVlan(vlan);
        return new ResponseEntity<>(createdVlan, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Vlan>> getAllVlans() {
        List<Vlan> vlans = networkService.getAllVlans();
        return ResponseEntity.ok(vlans);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vlan> getVlanById(@PathVariable Long id) {
        return ResponseEntity.ok(networkService.getVlanById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vlan> updateVlan(@PathVariable Long id, @Valid @RequestBody Vlan vlanDetails) {
        return ResponseEntity.ok(networkService.updateVlan(id, vlanDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVlan(@PathVariable Long id) {
        networkService.deleteVlan(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-site/{siteId}")
    public ResponseEntity<List<Vlan>> getVlansBySite(@PathVariable Long siteId) {
        List<Vlan> vlans = networkService.getVlansBySite(siteId);
        return ResponseEntity.ok(vlans);
    }
}