package br.com.netbox.netbox_api.service;

import br.com.netbox.netbox_api.model.Device;
import br.com.netbox.netbox_api.model.DeviceModel;
import br.com.netbox.netbox_api.model.Manufacturer;
import br.com.netbox.netbox_api.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Manufacturer getManufacturerById(Long id) {
        return manufacturerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fabricante com id " + id + " não encontrado."));
    }

    public Manufacturer updateManufacturer(Long id, Manufacturer manufacturerDetails) {
        Manufacturer existingManufacturer = getManufacturerById(id);
        existingManufacturer.setName(manufacturerDetails.getName());
        return manufacturerRepository.save(existingManufacturer);
    }

    public void deleteManufacturer(Long id) {
        if (!manufacturerRepository.existsById(id)) {
            throw new EntityNotFoundException("Fabricante com id " + id + " não encontrado.");
        }
        manufacturerRepository.deleteById(id);
    }


    public DeviceModel createDeviceModel(DeviceModel deviceModel) {
        manufacturerRepository.findById(getManufacturerIdFromModel(deviceModel))
                .orElseThrow(() -> new EntityNotFoundException("Fabricante com id " + getManufacturerIdFromModel(deviceModel) + " não encontrado."));
        return deviceModelRepository.save(deviceModel);
    }

    public List<DeviceModel> getAllDeviceModels() {
        return deviceModelRepository.findAll();
    }

    public DeviceModel getDeviceModelById(Long id) {
        return deviceModelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Modelo de Dispositivo com id " + id + " não encontrado."));
    }

    public DeviceModel updateDeviceModel(Long id, DeviceModel modelDetails) {
        DeviceModel existingModel = getDeviceModelById(id);
        
        Manufacturer manufacturer = manufacturerRepository.findById(getManufacturerIdFromModel(modelDetails))
                .orElseThrow(() -> new EntityNotFoundException("Fabricante com id " + getManufacturerIdFromModel(modelDetails) + " não encontrado."));
        
        existingModel.setName(modelDetails.getName());
        existingModel.setManufacturer(manufacturer);

        return deviceModelRepository.save(existingModel);
    }

    public void deleteDeviceModel(Long id) {
        if (!deviceModelRepository.existsById(id)) {
            throw new EntityNotFoundException("Modelo de Dispositivo com id " + id + " não encontrado.");
        }
        deviceModelRepository.deleteById(id);
    }


    public Device createDevice(Device device) {
        validateDeviceDependencies(device); 
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

    public Device getDeviceById(Long id) {
        return deviceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dispositivo com id " + id + " não encontrado."));
    }

    public Device updateDevice(Long id, Device deviceDetails) {
        Device existingDevice = getDeviceById(id);
        validateDeviceDependencies(deviceDetails);

        existingDevice.setName(deviceDetails.getName());
        existingDevice.setPosition(deviceDetails.getPosition());
        existingDevice.setSite(deviceDetails.getSite());
        existingDevice.setDeviceModel(deviceDetails.getDeviceModel());
        existingDevice.setRack(deviceDetails.getRack()); 

        return deviceRepository.save(existingDevice);
    }

    public void deleteDevice(Long id) {
        if (!deviceRepository.existsById(id)) {
            throw new EntityNotFoundException("Dispositivo com id " + id + " não encontrado.");
        }
        deviceRepository.deleteById(id);
    }

    private Long getManufacturerIdFromModel(DeviceModel deviceModel) {
        if (deviceModel.getManufacturer() == null || deviceModel.getManufacturer().getId() == null) {
            throw new IllegalArgumentException("O ID do Fabricante é obrigatório para criar ou atualizar um Modelo de Dispositivo.");
        }
        return deviceModel.getManufacturer().getId();
    }

    private void validateDeviceDependencies(Device device) {

        if (device.getSite() == null || device.getSite().getId() == null) {
            throw new IllegalArgumentException("O ID do Site é obrigatório para criar ou atualizar um Dispositivo.");
        }
        siteRepository.findById(device.getSite().getId())
                .orElseThrow(() -> new EntityNotFoundException("Site com id " + device.getSite().getId() + " não encontrado."));
        
        if (device.getDeviceModel() == null || device.getDeviceModel().getId() == null) {
            throw new IllegalArgumentException("O ID do Modelo de Dispositivo é obrigatório.");
        }
        deviceModelRepository.findById(device.getDeviceModel().getId())
                .orElseThrow(() -> new EntityNotFoundException("Modelo de Dispositivo com id " + device.getDeviceModel().getId() + " não encontrado."));
        
        if (device.getRack() != null && device.getRack().getId() != null) {
            rackRepository.findById(device.getRack().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Rack com id " + device.getRack().getId() + " não encontrado."));
        } else {
            device.setRack(null); 
        }
    }
}