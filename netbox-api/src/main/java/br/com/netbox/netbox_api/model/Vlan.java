package br.com.netbox.netbox_api.model;

import jakarta.persistence.*;

@Entity
public class Vlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer vlanId;
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "site_id")
    private Site site;
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getVlanId() { return vlanId; }
    public void setVlanId(Integer vlanId) { this.vlanId = vlanId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Site getSite() { return site; }
    public void setSite(Site site) { this.site = site; }
}