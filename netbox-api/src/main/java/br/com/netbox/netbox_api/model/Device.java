package br.com.netbox.netbox_api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do dispositivo é obrigatório")
    private String name;

    private Integer position; // Posição no Rack (U)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "site_id", nullable = false)
    @NotNull(message = "O Site é obrigatório")
    private Site site;

    // A CORREÇÃO ESTÁ AQUI
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rack_id")
    @JsonBackReference("rack-devices") // Referência de volta para Rack
    private Rack rack;

    // A CORREÇÃO ESTÁ AQUI
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "devicemodel_id", nullable = false)
    @NotNull(message = "O Modelo de Dispositivo é obrigatório")
    @JsonBackReference("model-devices") // Referência de volta para DeviceModel
    private DeviceModel deviceModel;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Rack getRack() {
        return rack;
    }

    public void setRack(Rack rack) {
        this.rack = rack;
    }

    public DeviceModel getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(DeviceModel deviceModel) {
        this.deviceModel = deviceModel;
    }
}
