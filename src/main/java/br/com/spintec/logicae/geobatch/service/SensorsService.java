package br.com.spintec.logicae.geobatch.service;

import br.com.spintec.logicae.geobatch.model.Sensors;
import br.com.spintec.logicae.geobatch.repository.SensorsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SensorsService {

    @Autowired
    private SensorsRepository sensorsRepository;

    final static Logger log = LoggerFactory.getLogger(SensorsService.class);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Sensors> saveAll(List<Sensors> sensors) {
        return sensorsRepository.saveAll(sensors);
    }

    public List<Sensors> findToCalibrations(String device, LocalDateTime collected) {
        return sensorsRepository.findToCalibrations(device, collected, 0L);
    }
}
