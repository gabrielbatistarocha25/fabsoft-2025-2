package br.com.netbox.netbox_api.service;

import br.com.netbox.netbox_api.model.Device;
import br.com.netbox.netbox_api.model.DeviceModel;
import br.com.netbox.netbox_api.model.Manufacturer;
import br.com.netbox.netbox_api.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final DeviceModelRepository deviceModelRepository;
    private final SiteRepository siteRepository;
    private final RackRepository rackRepository;

    public DeviceService(DeviceRepository deviceRepository, ManufacturerRepository manufacturerRepository, DeviceModelRepository deviceModelRepository, SiteRepository siteRepository, RackRepository rackRepository) {
        this.deviceRepository = deviceRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.deviceModelRepository = deviceModelRepository;
        this.siteRepository = siteRepository;
        this.rackRepository = rackRepository;
    }

    public Manufacturer createManufacturer(Manufacturer manufacturer) {
        return manufacturerRepository.save(manufacturer);
    }

    public List<Manufacturer> getAllManufacturers() {
        return manufacturerRepository.findAll();
    }

    public DeviceModel createDeviceModel(DeviceModel deviceModel) {
        manufacturerRepository.findById(deviceModel.getManufacturer().getId())
                .orElseThrow(() -> new EntityNotFoundException("Fabricante com id " + deviceModel.getManufacturer().getId() + " não encontrado."));
        return deviceModelRepository.save(deviceModel);
    }

    public List<DeviceModel> getAllDeviceModels() {
        return deviceModelRepository.findAll();
    }

    public Device createDevice(Device device) {
        siteRepository.findById(device.getSite().getId())
                .orElseThrow(() -> new EntityNotFoundException("Site com id " + device.getSite().getId() + " não encontrado."));
        deviceModelRepository.findById(device.getDeviceModel().getId())
                .orElseThrow(() -> new EntityNotFoundException("Modelo de Dispositivo com id " + device.getDeviceModel().getId() + " não encontrado."));
        if (device.getRack() != null && device.getRack().getId() != null) {
            rackRepository.findById(device.getRack().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Rack com id " + device.getRack().getId() + " não encontrado."));
        }
        return deviceRepository.save(device);
    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public List<Device> getDevicesBySite(Long siteId) {
        if (!siteRepository.existsById(siteId)) {
            throw new EntityNotFoundException("Site com id " + siteId + " não encontrado.");
        }
        return deviceRepository.findBySiteId(siteId);
    }
}

