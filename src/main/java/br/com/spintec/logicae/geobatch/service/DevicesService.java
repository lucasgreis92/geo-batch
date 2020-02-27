package br.com.spintec.logicae.geobatch.service;

import br.com.spintec.logicae.geobatch.model.Devices;
import br.com.spintec.logicae.geobatch.repository.DevicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DevicesService {

    @Autowired
    private DevicesRepository devicesRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Devices save(Devices devices) {
        return devicesRepository.saveAndFlush(devices);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void generateData(String serial) {
        devicesRepository.flush();
        devicesRepository.generateData(serial);
        devicesRepository.flush();
    }

    public Optional<Devices> findById(String serial) {
        return devicesRepository.findById(serial);
    }
}
