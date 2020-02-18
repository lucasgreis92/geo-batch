package br.com.spintec.logicae.geobatch.service;

import br.com.spintec.logicae.geobatch.dto.SensorsDtoV1;
import br.com.spintec.logicae.geobatch.mapper.SensorsTmpMapper;
import br.com.spintec.logicae.geobatch.model.SensorsTmp;
import br.com.spintec.logicae.geobatch.repository.SensorsTmpRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    final static Logger log = LoggerFactory.getLogger(LesenseBatchService.class);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteAll(List<SensorsTmp> sensores) {
        sensorsTmpRepository.deleteAll(sensores);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<SensorsTmp> savelAll(List<SensorsDtoV1> sensores){
        ObjectMapper objectMapper = new ObjectMapper();
        List<SensorsTmp> tmps = sensorsTmpMapper.convertToModelList(sensores);
        try {
            log.info("################################# recived ##################################");
            log.info(objectMapper.writeValueAsString(sensores));
            log.info("################################# tmps ##################################");
            log.info(objectMapper.writeValueAsString(tmps));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return sensorsTmpRepository.saveAll(tmps);
    }
}
