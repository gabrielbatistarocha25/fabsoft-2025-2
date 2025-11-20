package br.com.netbox.netbox_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(mappedBy = "site")
    @JsonIgnore
    private List<Rack> racks;
    
    @OneToMany(mappedBy = "site")
    @JsonIgnore
    private List<Vlan> vlans;

    @OneToMany(mappedBy = "site")
    @JsonIgnore
    private List<Device> devices;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }
    public List<Rack> getRacks() { return racks; }
    public void setRacks(List<Rack> racks) { this.racks = racks; }
    public List<Vlan> getVlans() { return vlans; }
    public void setVlans(List<Vlan> vlans) { this.vlans = vlans; }
    public List<Device> getDevices() { return devices; }
    public void setDevices(List<Device> devices) { this.devices = devices; }
}