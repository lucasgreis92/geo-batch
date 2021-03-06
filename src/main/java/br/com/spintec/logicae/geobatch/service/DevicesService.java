package br.com.spintec.logicae.geobatch.service;

import br.com.spintec.logicae.geobatch.model.CraneData;
import br.com.spintec.logicae.geobatch.model.Devices;
import br.com.spintec.logicae.geobatch.model.Sensors;
import br.com.spintec.logicae.geobatch.repository.CraneDataRepository;
import br.com.spintec.logicae.geobatch.repository.DevicesRepository;
import br.com.spintec.logicae.geobatch.repository.SensorsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DevicesService {

    @Autowired
    private DevicesRepository devicesRepository;

    @Autowired
    private CraneDataRepository craneDataRepository;

    @Autowired
    private SensorsRepository sensorsRepository;

    final static Logger log = LoggerFactory.getLogger(LesenseBatchService.class);

    private static final List<Integer> PORT_LIST = Arrays.asList(0,1,2,3,4,5,6,7,8,9,10);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Devices save(Devices devices) {
        return devicesRepository.saveAndFlush(devices);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void generateCraneData(Devices devices) {
        if (devices.getSubtype() != null && devices.getSubtype().equalsIgnoreCase("crane")) {
            PORT_LIST.forEach( port -> {

                LocalDateTime lastOff = craneDataRepository.findLastOff(devices.getDeviceSerial(),port);
                if (lastOff != null) {
                    List<Sensors> sensorsList = sensorsRepository.findByCollected(devices.getDeviceSerial(), lastOff, (long) port);
                    if (sensorsList != null && !sensorsList.isEmpty()) {
                        List<CraneData> inserts = new ArrayList<>();
                        CraneData newCrane = null;
                        CraneData lastCrane = null;
                        for (Sensors sensors : sensorsList) {
                            if (newCrane == null && sensors.getValue() == 1d
                                    && (lastCrane == null
                                    || lastCrane.getOff().isBefore(sensors.getCollected())
                                    || lastCrane.getOff().equals(sensors.getCollected()))) {

                                newCrane = new CraneData();
                                newCrane.setCreated(findNow());
                                newCrane.setDeviceSerial(devices.getDeviceSerial());
                                newCrane.setPort((int)sensors.getPort());
                                newCrane.setId(sensors.getId());
                                newCrane.setUserId(sensors.getUserId());
                                newCrane.setOn(sensors.getCollected());

                            } else if (newCrane != null && sensors.getValue() == 0d) {

                                newCrane.setOff(sensors.getCollected());
                                inserts.add(newCrane);
                                lastCrane = newCrane;
                                newCrane = null;
                            }

                        }
                        if (!inserts.isEmpty()) {
                            log.info(" ######### inserindo sensor "+ devices.getDeviceSerial() + " porta " + port + " quantidade ("+inserts.size()+") ######### ");
                            craneDataRepository.saveAll(inserts);
                        }

                    }

                }

            });
        }


      /*  devicesRepository.flush();
        devicesRepository.generateData(serial);
        devicesRepository.flush();*/
    }

    public LocalDateTime findNow() {
        return LocalDateTime.now().minusHours(3);
    }

    public Optional<Devices> findById(String serial) {
        return devicesRepository.findById(serial);
    }
}
