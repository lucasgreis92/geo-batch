package br.com.spintec.logicae.geobatch.repository;

import br.com.spintec.logicae.geobatch.model.LesenseBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LesenseBatchRepository extends JpaRepository<LesenseBatch,Long> {

}
