package br.com.netbox.netbox_api.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; // Importação alterada para wildcard
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

    @PutMapping("/{id}")
    public ResponseEntity<Rack> updateRack(@PathVariable Long id, @Valid @RequestBody Rack rackDetails) {
        return ResponseEntity.ok(rackService.updateRack(id, rackDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRack(@PathVariable Long id) {
        rackService.deleteRack(id);
        return ResponseEntity.noContent().build();
    }
}