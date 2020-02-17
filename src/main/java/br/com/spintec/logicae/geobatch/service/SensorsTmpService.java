package br.com.spintec.logicae.geobatch.service;

import br.com.spintec.logicae.geobatch.dto.SensorsDtoV1;
import br.com.spintec.logicae.geobatch.mapper.SensorsTmpMapper;
import br.com.spintec.logicae.geobatch.model.SensorsTmp;
import br.com.spintec.logicae.geobatch.repository.SensorsTmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SensorsTmpService {

    @Autowired
    private SensorsTmpRepository sensorsTmpRepository;

    @Autowired
    private SensorsTmpMapper sensorsTmpMapper;

    public List<SensorsTmp> findAll() {
        return sensorsTmpRepository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteAll(List<SensorsTmp> sensores) {
        sensorsTmpRepository.deleteAll(sensores);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<SensorsTmp> savelAll(List<SensorsDtoV1> sensores){
        return sensorsTmpRepository.saveAll(sensorsTmpMapper.convertToModelList(sensores));
    }
}
