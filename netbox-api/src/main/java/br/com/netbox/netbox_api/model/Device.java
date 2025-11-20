package br.com.netbox.netbox_api.model;

import jakarta.persistence.*;

@Entity
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer position;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "site_id")
    private Site site;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rack_id")
    private Rack rack;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "devicemodel_id")
    private DeviceModel deviceModel;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getPosition() { return position; }
    public void setPosition(Integer position) { this.position = position; }
    public Site getSite() { return site; }
    public void setSite(Site site) { this.site = site; }
    public Rack getRack() { return rack; }
    public void setRack(Rack rack) { this.rack = rack; }
    public DeviceModel getDeviceModel() { return deviceModel; }
    public void setDeviceModel(DeviceModel deviceModel) { this.deviceModel = deviceModel; }
}