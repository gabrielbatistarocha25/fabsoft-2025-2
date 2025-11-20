package br.com.netbox.netbox_api.model;

import jakarta.persistence.*;

@Entity
public class IpAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address; // Ex: 10.0.0.5/24
    private String status;  // Ex: Active, Reserved, DHCP
    private String dnsName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "device_id")
    private Device device;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getDnsName() { return dnsName; }
    public void setDnsName(String dnsName) { this.dnsName = dnsName; }
    public Device getDevice() { return device; }
    public void setDevice(Device device) { this.device = device; }
}