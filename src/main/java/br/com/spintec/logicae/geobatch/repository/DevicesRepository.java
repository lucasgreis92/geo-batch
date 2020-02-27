package br.com.spintec.logicae.geobatch.repository;

import br.com.spintec.logicae.geobatch.model.Devices;
import br.com.spintec.logicae.geobatch.repository.custom.DevicesRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DevicesRepository extends JpaRepository<Devices,String>, DevicesRepositoryCustom {

    @Procedure
    void generateData(@Param("serial_p") String serial);
}
