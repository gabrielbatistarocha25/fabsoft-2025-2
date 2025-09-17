package br.com.netbox.netbox_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
