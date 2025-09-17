package br.com.netbox.netbox_api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class Rack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do rack é obrigatório")
    private String name;

    private int uHeight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "site_id", nullable = false)
    @JsonBackReference // Evita loop na relação com Site
    private Site site;

    // A CORREÇÃO ESTÁ AQUI
    @OneToMany(mappedBy = "rack", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("rack-devices") // Lado principal da relação com Device
    private List<Device> devices;


    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getuHeight() { return uHeight; }
    public void setuHeight(int uHeight) { this.uHeight = uHeight; }
    public Site getSite() { return site; }
    public void setSite(Site site) { this.site = site; }
    public List<Device> getDevices() { return devices; }
    public void setDevices(List<Device> devices) { this.devices = devices; }
}

