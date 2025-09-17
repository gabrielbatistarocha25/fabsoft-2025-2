package br.com.netbox.netbox_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.netbox.netbox_api.model.Device;
import br.com.netbox.netbox_api.model.DeviceModel;
import br.com.netbox.netbox_api.model.Manufacturer;
import br.com.netbox.netbox_api.service.DeviceService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    // Endpoints para Manufacturer
    @PostMapping("/manufacturers")
    public ResponseEntity<Manufacturer> createManufacturer(@Valid @RequestBody Manufacturer manufacturer) {
        return new ResponseEntity<>(deviceService.createManufacturer(manufacturer), HttpStatus.CREATED);
    }

    @GetMapping("/manufacturers")
    public ResponseEntity<List<Manufacturer>> getAllManufacturers() {
        return ResponseEntity.ok(deviceService.getAllManufacturers());
    }

    // Endpoints para DeviceModel
    @PostMapping("/device-models")
    public ResponseEntity<DeviceModel> createDeviceModel(@Valid @RequestBody DeviceModel deviceModel) {
        return new ResponseEntity<>(deviceService.createDeviceModel(deviceModel), HttpStatus.CREATED);
    }

    @GetMapping("/device-models")
    public ResponseEntity<List<DeviceModel>> getAllDeviceModels() {
        return ResponseEntity.ok(deviceService.getAllDeviceModels());
    }

    // Endpoints para Device
    @PostMapping("/devices")
    public ResponseEntity<Device> createDevice(@Valid @RequestBody Device device) {
        return new ResponseEntity<>(deviceService.createDevice(device), HttpStatus.CREATED);
    }

    @GetMapping("/devices")
    public ResponseEntity<List<Device>> getAllDevices() {
        return ResponseEntity.ok(deviceService.getAllDevices());
    }
}
