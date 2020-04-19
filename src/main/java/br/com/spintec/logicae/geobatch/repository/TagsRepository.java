package br.com.spintec.logicae.geobatch.repository;

import br.com.spintec.logicae.geobatch.model.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TagsRepository extends JpaRepository<Tags,Long> {

    List<Tags> findByDeviceSerial(String deviceSerial);

}
