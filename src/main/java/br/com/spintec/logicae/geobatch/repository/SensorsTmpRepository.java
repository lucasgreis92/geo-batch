package br.com.spintec.logicae.geobatch.repository;

import br.com.spintec.logicae.geobatch.model.Sensors;
import br.com.spintec.logicae.geobatch.model.SensorsTmp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SensorsTmpRepository  extends JpaRepository<SensorsTmp, UUID> {

}
