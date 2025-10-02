package br.com.netbox.netbox_api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // Lado "pai" da relação. Será incluído no JSON.
    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("manufacturer-models")
    private List<DeviceModel> deviceModels;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<DeviceModel> getDeviceModels() { return deviceModels; }
    public void setDeviceModels(List<DeviceModel> deviceModels) { this.deviceModels = deviceModels; }
}

