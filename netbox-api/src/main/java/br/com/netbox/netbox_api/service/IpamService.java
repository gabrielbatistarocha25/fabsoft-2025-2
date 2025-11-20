package br.com.netbox.netbox_api.service;

import br.com.netbox.netbox_api.model.IpAddress;
import br.com.netbox.netbox_api.model.Prefix;
import br.com.netbox.netbox_api.repository.IpAddressRepository;
import br.com.netbox.netbox_api.repository.PrefixRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class IpamService {

    private final PrefixRepository prefixRepository;
    private final IpAddressRepository ipAddressRepository;

    public IpamService(PrefixRepository prefixRepository, IpAddressRepository ipAddressRepository) {
        this.prefixRepository = prefixRepository;
        this.ipAddressRepository = ipAddressRepository;
    }

    public Prefix createPrefix(Prefix prefix) {
        return prefixRepository.save(prefix);
    }

    public List<Prefix> getAllPrefixes() {
        return prefixRepository.findAll();
    }

    public Prefix getPrefixById(Long id) {
        return prefixRepository.findById(id).orElseThrow(() -> new RuntimeException("Prefix not found"));
    }

    public Prefix updatePrefix(Long id, Prefix details) {
        Prefix prefix = getPrefixById(id);
        prefix.setCidr(details.getCidr());
        prefix.setName(details.getName());
        prefix.setSite(details.getSite());
        prefix.setVlan(details.getVlan());
        return prefixRepository.save(prefix);
    }

    public void deletePrefix(Long id) {
        prefixRepository.deleteById(id);
    }

    public IpAddress createIpAddress(IpAddress ip) {
        return ipAddressRepository.save(ip);
    }

    public List<IpAddress> getAllIpAddresses() {
        return ipAddressRepository.findAll();
    }

    public IpAddress getIpAddressById(Long id) {
        return ipAddressRepository.findById(id).orElseThrow(() -> new RuntimeException("IP not found"));
    }

    public IpAddress updateIpAddress(Long id, IpAddress details) {
        IpAddress ip = getIpAddressById(id);
        ip.setAddress(details.getAddress());
        ip.setStatus(details.getStatus());
        ip.setDnsName(details.getDnsName());
        ip.setDevice(details.getDevice());
        return ipAddressRepository.save(ip);
    }

    public void deleteIpAddress(Long id) {
        ipAddressRepository.deleteById(id);
    }
}