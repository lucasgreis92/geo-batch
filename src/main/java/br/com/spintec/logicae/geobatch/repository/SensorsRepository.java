package br.com.spintec.logicae.geobatch.repository;

import br.com.spintec.logicae.geobatch.model.Sensors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface SensorsRepository extends JpaRepository<Sensors, UUID> {

    @Query(value = "select * from sensors where device_serial = ?1 and collected > ?2 and port = ?3 order by collected asc limit 5000 ",
            nativeQuery = true)
    List<Sensors> findByCollected(String device, LocalDateTime collected, Long port);

}
