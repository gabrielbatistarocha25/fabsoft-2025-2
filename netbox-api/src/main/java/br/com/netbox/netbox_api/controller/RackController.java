package br.com.netbox.netbox_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.netbox.netbox_api.model.Rack;
import br.com.netbox.netbox_api.service.RackService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/racks")
public class RackController {

    private final RackService rackService;

    public RackController(RackService rackService) {
        this.rackService = rackService;
    }

    @PostMapping
    public ResponseEntity<Rack> createRack(@Valid @RequestBody Rack rack) {
        return new ResponseEntity<>(rackService.createRack(rack), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Rack>> getAllRacks() {
        return ResponseEntity.ok(rackService.getAllRacks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rack> getRackById(@PathVariable Long id) {
        return ResponseEntity.ok(rackService.getRackById(id));
    }
}
