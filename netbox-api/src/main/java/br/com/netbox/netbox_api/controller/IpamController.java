package br.com.netbox.netbox_api.controller;

import br.com.netbox.netbox_api.model.IpAddress;
import br.com.netbox.netbox_api.model.Prefix;
import br.com.netbox.netbox_api.service.IpamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ipam")
@CrossOrigin(origins = "https://glowing-bassoon-v65944vpw9j73x9g-4200.app.github.dev")
public class IpamController {

    private final IpamService ipamService;

    public IpamController(IpamService ipamService) {
        this.ipamService = ipamService;
    }

    @PostMapping("/prefixes")
    public ResponseEntity<Prefix> createPrefix(@RequestBody Prefix prefix) {
        return new ResponseEntity<>(ipamService.createPrefix(prefix), HttpStatus.CREATED);
    }

    @GetMapping("/prefixes")
    public ResponseEntity<List<Prefix>> getAllPrefixes() {
        return ResponseEntity.ok(ipamService.getAllPrefixes());
    }

    @GetMapping("/prefixes/{id}")
    public ResponseEntity<Prefix> getPrefixById(@PathVariable Long id) {
        return ResponseEntity.ok(ipamService.getPrefixById(id));
    }

    @PutMapping("/prefixes/{id}")
    public ResponseEntity<Prefix> updatePrefix(@PathVariable Long id, @RequestBody Prefix details) {
        return ResponseEntity.ok(ipamService.updatePrefix(id, details));
    }

    @DeleteMapping("/prefixes/{id}")
    public ResponseEntity<Void> deletePrefix(@PathVariable Long id) {
        ipamService.deletePrefix(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/ip-addresses")
    public ResponseEntity<IpAddress> createIpAddress(@RequestBody IpAddress ip) {
        return new ResponseEntity<>(ipamService.createIpAddress(ip), HttpStatus.CREATED);
    }

    @GetMapping("/ip-addresses")
    public ResponseEntity<List<IpAddress>> getAllIpAddresses() {
        return ResponseEntity.ok(ipamService.getAllIpAddresses());
    }

    @GetMapping("/ip-addresses/{id}")
    public ResponseEntity<IpAddress> getIpAddressById(@PathVariable Long id) {
        return ResponseEntity.ok(ipamService.getIpAddressById(id));
    }

    @PutMapping("/ip-addresses/{id}")
    public ResponseEntity<IpAddress> updateIpAddress(@PathVariable Long id, @RequestBody IpAddress details) {
        return ResponseEntity.ok(ipamService.updateIpAddress(id, details));
    }

    @DeleteMapping("/ip-addresses/{id}")
    public ResponseEntity<Void> deleteIpAddress(@PathVariable Long id) {
        ipamService.deleteIpAddress(id);
        return ResponseEntity.noContent().build();
    }
}