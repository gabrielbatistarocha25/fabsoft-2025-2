package br.com.netbox.netbox_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.netbox.netbox_api.model.Device;
import br.com.netbox.netbox_api.model.DeviceModel;
import br.com.netbox.netbox_api.model.Manufacturer;
import br.com.netbox.netbox_api.model.Rack;
import br.com.netbox.netbox_api.model.Site;
import br.com.netbox.netbox_api.repository.DeviceModelRepository;
import br.com.netbox.netbox_api.repository.DeviceRepository;
import br.com.netbox.netbox_api.repository.ManufacturerRepository;
import br.com.netbox.netbox_api.repository.RackRepository;
import br.com.netbox.netbox_api.repository.SiteRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class DeviceService {

    private final ManufacturerRepository manufacturerRepository;
    private final DeviceModelRepository deviceModelRepository;
    private final DeviceRepository deviceRepository;
    private final SiteRepository siteRepository;
    private final RackRepository rackRepository;

    public DeviceService(ManufacturerRepository manufacturerRepository,
                         DeviceModelRepository deviceModelRepository,
                         DeviceRepository deviceRepository,
                         SiteRepository siteRepository,
                         RackRepository rackRepository) {
        this.manufacturerRepository = manufacturerRepository;
        this.deviceModelRepository = deviceModelRepository;
        this.deviceRepository = deviceRepository;
        this.siteRepository = siteRepository;
        this.rackRepository = rackRepository;
    }

    // Métodos para Manufacturer
    public Manufacturer createManufacturer(Manufacturer manufacturer) {
        return manufacturerRepository.save(manufacturer);
    }

    public List<Manufacturer> getAllManufacturers() {
        return manufacturerRepository.findAll();
    }

    // Métodos para DeviceModel
    public DeviceModel createDeviceModel(DeviceModel deviceModel) {
        // Garante que o fabricante associado existe no banco
        Manufacturer manufacturer = manufacturerRepository.findById(deviceModel.getManufacturer().getId())
                .orElseThrow(() -> new EntityNotFoundException("Fabricante com id " + deviceModel.getManufacturer().getId() + " não encontrado."));
        deviceModel.setManufacturer(manufacturer);
        return deviceModelRepository.save(deviceModel);
    }

    public List<DeviceModel> getAllDeviceModels() {
        return deviceModelRepository.findAll();
    }

    // Métodos para Device
    public Device createDevice(Device device) {
        // Valida e associa as entidades relacionadas (Site, DeviceModel, Rack)
        Site site = siteRepository.findById(device.getSite().getId())
                .orElseThrow(() -> new EntityNotFoundException("Site com id " + device.getSite().getId() + " não encontrado."));
        device.setSite(site);

        DeviceModel deviceModel = deviceModelRepository.findById(device.getDeviceModel().getId())
                .orElseThrow(() -> new EntityNotFoundException("Modelo de Dispositivo com id " + device.getDeviceModel().getId() + " não encontrado."));
        device.setDeviceModel(deviceModel);

        // O rack é opcional
        if (device.getRack() != null && device.getRack().getId() != null) {
            Rack rack = rackRepository.findById(device.getRack().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Rack com id " + device.getRack().getId() + " não encontrado."));
            device.setRack(rack);
        }

        return deviceRepository.save(device);
    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }
}