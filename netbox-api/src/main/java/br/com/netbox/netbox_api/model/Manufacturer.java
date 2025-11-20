package br.com.netbox.netbox_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<DeviceModel> deviceModels;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<DeviceModel> getDeviceModels() { return deviceModels; }
    public void setDeviceModels(List<DeviceModel> deviceModels) { this.deviceModels = deviceModels; }
}