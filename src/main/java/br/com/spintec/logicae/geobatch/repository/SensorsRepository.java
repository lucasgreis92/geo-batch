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

    @Query(value = "select * from sensors where collected > ?1 and port = ?2 ",
            nativeQuery = true)
    List<Sensors> findByCollected(LocalDateTime collected, Long port);

}
