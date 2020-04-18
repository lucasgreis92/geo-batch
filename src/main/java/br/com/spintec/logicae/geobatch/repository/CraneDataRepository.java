package br.com.spintec.logicae.geobatch.repository;

import br.com.spintec.logicae.geobatch.model.CraneData;
import br.com.spintec.logicae.geobatch.repository.custom.CraneDataRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface CraneDataRepository extends JpaRepository<CraneData, UUID>, CraneDataRepositoryCustom {

    @Query(value = "select coalesce(max(cd.off), current_timestamp - INTERVAL '180 days') " +
            "from crane_data cd " +
            "where cd.device_serial = ?1 " +
            "and cd.port = ?2",
            nativeQuery = true)
    LocalDateTime findLastOff(String deviceSerial, Integer port);

    @Query(value = "select * from crane_data cd " +
            "where device_serial = ?1 " +
            "and cd.on >= ?2 " +
            "and cd.on <= ?3 " +
            "order by cd.on desc",
            nativeQuery = true)
    List<CraneData> findByFilter(String device, LocalDateTime collectedIni, LocalDateTime collectedFim);

    @Query(value = "select * from crane_data cd " +
            "where device_serial = ?1 " +
            "and cd.port = ?2 " +
            "and cd.on >= ?3 " +
            "and cd.on <= ?4 " +
            "order by cd.on desc",
            nativeQuery = true)
    List<CraneData> findByFilter(String device, Integer port , LocalDateTime collectedIni, LocalDateTime collectedFim);

    Page<CraneData> findByDeviceSerialAndPortAndOffBetween(String device, Integer port , LocalDateTime collectedIni, LocalDateTime collectedFim, Pageable pageable);

    Page<CraneData> findByDeviceSerialAndOffBetween(String device, LocalDateTime collectedIni, LocalDateTime collectedFim, Pageable pageable);
}
