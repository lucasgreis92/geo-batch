package br.com.spintec.logicae.geobatch.repository;

import br.com.spintec.logicae.geobatch.model.CalibrationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CalibrationDataRepository extends JpaRepository<CalibrationData, UUID> {


    @Query(value = "select cd.*  " +
            "from calibrations c " +
            "join calibration_data cd " +
            "on c. calibration_id = cd.calibration_id " +
            "where c.done is null",
            nativeQuery = true)
    List<CalibrationData> findAllNotDone();
}
