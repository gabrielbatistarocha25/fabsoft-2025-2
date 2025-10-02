package br.com.netbox.netbox_api.repository;

import br.com.netbox.netbox_api.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    // A CORREÇÃO ESTÁ AQUI: Adiciona o método de busca que o serviço precisa
    List<Device> findBySiteId(Long siteId);
}

