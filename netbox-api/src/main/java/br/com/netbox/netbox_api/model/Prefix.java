package br.com.netbox.netbox_api.model;

import jakarta.persistence.*;

@Entity
public class Prefix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String cidr;
    private String name; 

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "site_id")
    private Site site;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vlan_id")
    private Vlan vlan;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCidr() { return cidr; }
    public void setCidr(String cidr) { this.cidr = cidr; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Site getSite() { return site; }
    public void setSite(Site site) { this.site = site; }
    public Vlan getVlan() { return vlan; }
    public void setVlan(Vlan vlan) { this.vlan = vlan; }
}