package br.com.spintec.logicae.geobatch.repository;

import br.com.spintec.logicae.geobatch.model.Contracts;
import br.com.spintec.logicae.geobatch.model.CraneData;
import br.com.spintec.logicae.geobatch.repository.custom.CraneDataRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CraneDataRepository extends JpaRepository<CraneData, UUID>, CraneDataRepositoryCustom {


    @Query(value = "select * from crane_data cd1 " +
            "where cd1.off = " +
            "(select coalesce(max(cd.off), current_timestamp - INTERVAL '10min') " +
            "from crane_data cd " +
            "where cd.device_serial = ?1 " +
            "and cd.port = ?2)" +
            "and cd1.device_serial = ?1" +
            "and cd1.port = ?2 ",
            nativeQuery = true)
    List<CraneData> findLastOff(String deviceSerial, Integer port);
}
