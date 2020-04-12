package br.com.spintec.logicae.geobatch.repository;

import br.com.spintec.logicae.geobatch.model.Sensors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    List<Sensors> findByCollected(String device,
                                  LocalDateTime collected,
                                  Long port);


    @Query(value = "select * from sensors " +
            "where device_serial = ?1 " +
            "and collected >= ?2 " +
            "and collected <= ?3 " +
            "order by collected desc",
            nativeQuery = true)
    List<Sensors> findByFilter(String device,
                               LocalDateTime collectedIni,
                               LocalDateTime collectedFim);

    @Query(value = "select * from sensors " +
            "where device_serial = ?1 " +
            "and port = ?2 " +
            "and collected >= ?3 " +
            "and collected <= ?4 " +
            "order by collected desc",
            nativeQuery = true)
    List<Sensors> findByFilter(String device,
                               Long port ,
                               LocalDateTime collectedIni,
                               LocalDateTime collectedFim);

    Page<Sensors> findByDeviceSerialAndPortAndCollectedBetween(String device,
                                                               Long port ,
                                                               LocalDateTime collectedIni,
                                                               LocalDateTime collectedFim,
                                                               Pageable pageable);

    Page<Sensors> findByDeviceSerialAndCollectedBetween(String device,
                                                        LocalDateTime collectedIni,
                                                        LocalDateTime collectedFim,
                                                        Pageable pageable);

    @Query(value = "select * from sensors s " +
            "where s.collected = (select max(s1.collected)" +
            "                   from sensors s1 " +
            "                   where s1.device_serial = ?1" +
            "                   and s1.port = ?2) " +
            "and s.device_serial = ?1 " +
            "and s.port = ?2 " +
            "limit 1 ",
            nativeQuery = true)
    Sensors findLast(String device, Long port);

    @Query(value = "select * from sensors s " +
            "where s.collected = (select max(s1.collected) " +
            "                   from sensors s1 " +
            "                   where s1.device_serial = ?1 " +
            "                   and s1.port = ?2" +
            "                   and s1.value <> ?3) " +
            "and s.device_serial = ?1 " +
            "and s.port = ?2 " +
            "and s.value <> ?3 " +
            "limit 1 ",
            nativeQuery = true)
    Sensors findLastOtherValue(String device, Long port, Double value);

    @Query(value = "select * from sensors s " +
            "where s.collected = (select max(s1.collected) " +
            "                   from sensors s1 " +
            "                   where s1.device_serial = ?1 " +
            "                   and s1.port = ?2" +
            "                   and s1.value = ?3" +
            "                   and collected >= ?4) " +
            "and s.device_serial = ?1 " +
            "and s.port = ?2 " +
            "and s.value = ?3 " +
            "and collected >= ?4 " +
            "limit 1 ",
            nativeQuery = true)
    Sensors findFirstLastValue(String device, Long port, Double value, LocalDateTime data);
}
