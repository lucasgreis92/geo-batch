package br.com.spintec.logicae.geobatch.repository;

import br.com.spintec.logicae.geobatch.model.Devices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevicesRepository extends JpaRepository<Devices,String> {
}
