package br.com.spintec.logicae.geobatch.service;

import br.com.spintec.logicae.geobatch.mapper.SensorsTmpMapper;
import br.com.spintec.logicae.geobatch.model.*;
import br.com.spintec.logicae.geobatch.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;

@Service
public class LesenseBatchService {

    @Autowired
    private LesenseBatchRepository lesenseBatchRepository;

    @Autowired
    private SensorsService sensorsService;

    @Autowired
    private DevicesRepository devicesRepository;

    @Autowired
    private SensorsTmpMapper sensorsTmpMapper;

    @Autowired
    private SensorsTmpService sensorsTmpService;

    @Autowired
    private ContractsRepository contractsRepository;

    @Autowired
    private DevicesService devicesService;

    private Semaphore semaphoreSendSensors = new Semaphore(1);
    private static boolean ieSendSensors = false;

    final static Logger log = LoggerFactory.getLogger(LesenseBatchService.class);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void sendSensorsStart() {
        try {
            semaphoreSendSensors.acquire();
            if (ieSendSensors) {
                return;
            }
            ieSendSensors= true;
            semaphoreSendSensors.release();

            log.info("######################### iniciado sendSensors #########################");
            sendSensors();
            log.info("######################### finalizado sendSensors #########################");
            semaphoreSendSensors.acquire();
            ieSendSensors = false;
            semaphoreSendSensors.release();
        } catch (Exception ex) {
            log.error("erro sendSensors",ex);
            ieSendSensors = false;
            semaphoreSendSensors.release();
        }
    }

    public LesenseBatch newLesenseBatch() {
        LesenseBatch batch = new LesenseBatch();
        batch.setDtCreate(LocalDateTime.now());
        batch.setQtdRowsSended(0L);
        batch.setQtdRowsGenerated(0L);
        return lesenseBatchRepository.save(batch);
    }

    public LesenseBatch finalizarBatch(LesenseBatch batch, Long qtdRowsGenerated, Long qtdRowsSended,String error) {
        batch.setDtFinished(LocalDateTime.now());
        batch.setQtdRowsGenerated(qtdRowsGenerated);
        batch.setQtdRowsSended(qtdRowsSended);
        batch.setError(error);
        return lesenseBatchRepository.save(batch);
    }

    public void sendSensors() {
        LesenseBatch batch = null;
        Long qtdRowsGenerated = 0L;
        Long qtdRowsSended = 0L;
        String error = null;
        try {
            batch = newLesenseBatch();
            List<Devices> devices = devicesRepository.findAll();
            log.info("######################### iniciado generateData #########################");
            devices.forEach( d -> {
                devicesService.generateData(d.getDeviceSerial());

            });
            log.info("######################### finalizado generateData #########################");
            List<SensorsTmp> tmps = sensorsTmpService.findAll();
            if (tmps.isEmpty()) {
                return;
            }
            List<Sensors> sensores = sensorsTmpMapper.tmpToSensorsList(tmps);

            List<Contracts> contracts = contractsRepository.findAll();
            List<Devices> newDevices = new ArrayList<>();
            sensores.forEach( sensor -> {
                Optional<Devices> device =  devices.stream().filter(d -> {
                    return d.getDeviceSerial().equalsIgnoreCase(sensor.getDeviceSerial());
                }).findFirst();
                if (!device.isPresent()) {
                  /*  Devices dev = new Devices();
                    dev.setCreated(LocalDateTime.now());
                    dev.setDeviceSerial(sensor.getDeviceSerial());
                    dev.setType(sensor.getType());
                    dev.setDisabled(false);
                    newDevices.add(dev);*/
                } else {
                    if (device.get().getContractId() != null) {
                        Optional<Contracts> contract = contracts.stream().filter( c -> {
                            return c.getContractId().toString().equalsIgnoreCase(device.get().getContractId().toString());
                        }).findFirst();
                        device.get().setLastAck(LocalDateTime.now());
                        if (contract.isPresent()) {
                            sensor.setUserId(contract.get().getUserId());
                        }
                    }
                }
            });
            sensores = sensores.stream().filter( s -> {
                return s.getUserId() != null;
            }).collect(Collectors.toList());
            if (!newDevices.isEmpty()) {
                devices.addAll(devicesRepository.saveAll(newDevices));
            }
            devicesRepository.saveAll(devices);
            if (!sensores.isEmpty()) {
                sensores =  sensorsService.saveAll(sensores);
            }
            sensorsTmpService.deleteAll(tmps);
            if (!sensores.isEmpty()) {
                HashMap<String,String> seriais = new HashMap<>();
                sensores.forEach( s -> {
                    seriais.put(s.getDeviceSerial(),s.getDeviceSerial());
                });
                seriais.keySet().forEach( serial -> {
                    devicesService.generateData(serial);
                });
            }


        } catch (Exception ex) {
            log.error("erro sendSensors", ex);
            error = ex.getMessage();
            for (StackTraceElement trace : ex.getStackTrace()) {
                error += "\n" + ex.getStackTrace()[0].toString();
            }
        } finally {
            finalizarBatch(batch, qtdRowsGenerated, qtdRowsSended, error);
        }
    }

}
