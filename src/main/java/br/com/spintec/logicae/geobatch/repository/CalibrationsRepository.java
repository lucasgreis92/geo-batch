package br.com.spintec.logicae.geobatch.repository;

import br.com.spintec.logicae.geobatch.model.Calibrations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CalibrationsRepository extends JpaRepository<Calibrations, UUID> {


    List<Calibrations> findAllByDoneIsNull();

}
