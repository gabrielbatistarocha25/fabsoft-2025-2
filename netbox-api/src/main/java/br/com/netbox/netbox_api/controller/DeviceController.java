package br.com.netbox.netbox_api.controller;

import br.com.netbox.netbox_api.model.Device;
import br.com.netbox.netbox_api.model.DeviceModel;
import br.com.netbox.netbox_api.model.Manufacturer;
import br.com.netbox.netbox_api.service.DeviceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }


    @PostMapping("/manufacturers")
    public ResponseEntity<Manufacturer> createManufacturer(@Valid @RequestBody Manufacturer manufacturer) {
        return new ResponseEntity<>(deviceService.createManufacturer(manufacturer), HttpStatus.CREATED);
    }

    @GetMapping("/manufacturers")
    public ResponseEntity<List<Manufacturer>> getAllManufacturers() {
        return ResponseEntity.ok(deviceService.getAllManufacturers());
    }

    @GetMapping("/manufacturers/{id}")
    public ResponseEntity<Manufacturer> getManufacturerById(@PathVariable Long id) {
        return ResponseEntity.ok(deviceService.getManufacturerById(id));
    }

    @PutMapping("/manufacturers/{id}")
    public ResponseEntity<Manufacturer> updateManufacturer(@PathVariable Long id, @Valid @RequestBody Manufacturer manufacturerDetails) {
        return ResponseEntity.ok(deviceService.updateManufacturer(id, manufacturerDetails));
    }

    @DeleteMapping("/manufacturers/{id}")
    public ResponseEntity<Void> deleteManufacturer(@PathVariable Long id) {
        deviceService.deleteManufacturer(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/device-models")
    public ResponseEntity<DeviceModel> createDeviceModel(@Valid @RequestBody DeviceModel deviceModel) {
        return new ResponseEntity<>(deviceService.createDeviceModel(deviceModel), HttpStatus.CREATED);
    }

    @GetMapping("/device-models")
    public ResponseEntity<List<DeviceModel>> getAllDeviceModels() {
        return ResponseEntity.ok(deviceService.getAllDeviceModels());
    }

    @GetMapping("/device-models/{id}")
    public ResponseEntity<DeviceModel> getDeviceModelById(@PathVariable Long id) {
        return ResponseEntity.ok(deviceService.getDeviceModelById(id));
    }

    @PutMapping("/device-models/{id}")
    public ResponseEntity<DeviceModel> updateDeviceModel(@PathVariable Long id, @Valid @RequestBody DeviceModel modelDetails) {
        return ResponseEntity.ok(deviceService.updateDeviceModel(id, modelDetails));
    }

    @DeleteMapping("/device-models/{id}")
    public ResponseEntity<Void> deleteDeviceModel(@PathVariable Long id) {
        deviceService.deleteDeviceModel(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/devices")
    public ResponseEntity<Device> createDevice(@Valid @RequestBody Device device) {
        return new ResponseEntity<>(deviceService.createDevice(device), HttpStatus.CREATED);
    }

    @GetMapping("/devices")
    public ResponseEntity<List<Device>> getAllDevices() {
        return ResponseEntity.ok(deviceService.getAllDevices());
    }

    @GetMapping("/devices/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable Long id) {
        return ResponseEntity.ok(deviceService.getDeviceById(id));
    }

    @PutMapping("/devices/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable Long id, @Valid @RequestBody Device deviceDetails) {
        return ResponseEntity.ok(deviceService.updateDevice(id, deviceDetails));
    }

    @DeleteMapping("/devices/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Long id) {
        deviceService.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sites/{siteId}/devices")
    public ResponseEntity<List<Device>> getDevicesBySite(@PathVariable Long siteId) {
        List<Device> devices = deviceService.getDevicesBySite(siteId);
        return ResponseEntity.ok(devices);
    }
}