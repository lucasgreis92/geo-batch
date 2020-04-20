package br.com.spintec.logicae.geobatch.service;

import br.com.spintec.logicae.geobatch.mapper.SensorsTmpMapper;
import br.com.spintec.logicae.geobatch.model.*;
import br.com.spintec.logicae.geobatch.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class LesenseBatchService {

    @Value("${geo.force.sensors}")
    private String geoForceSensors;

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

    @Autowired
    private CalibrationDataRepository calibrationDataRepository;

    @Autowired
    private CalibrationsRepository calibrationsRepository;

    final static Logger log = LoggerFactory.getLogger(LesenseBatchService.class);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void sendSensorsStart() {
        try {
            log.info("######################### iniciado sendSensors #########################");
            sendSensors();
            log.info("######################### finalizado sendSensors #########################");
        } catch (Exception ex) {
            log.error("erro sendSensors",ex);
        }
    }

    public LesenseBatch newLesenseBatch() {
        LesenseBatch batch = new LesenseBatch();
        batch.setDtCreate(findNow());
        batch.setQtdRowsSended(0L);
        batch.setQtdRowsGenerated(0L);
        return lesenseBatchRepository.save(batch);
    }

    public LesenseBatch finalizarBatch(LesenseBatch batch, Long qtdRowsGenerated, Long qtdRowsSended,String error) {
        batch.setDtFinished(findNow());
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
                    dev.setCreated(findNow();
                    dev.setDeviceSerial(sensor.getDeviceSerial());
                    dev.setType(sensor.getType());
                    dev.setDisabled(false);
                    newDevices.add(dev);*/
                } else {
                    if (device.get().getContractId() != null) {
                        Optional<Contracts> contract = contracts.stream().filter( c -> {
                            return c.getContractId().toString().equalsIgnoreCase(device.get().getContractId().toString());
                        }).findFirst();
                        device.get().setLastAck(findNow());
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

    public void generateData() {
        log.info("######################### iniciado generateData #########################");
        List<Devices> devices = devicesRepository.findAll();
        devices.forEach( d -> {
            if (geoForceSensors.equalsIgnoreCase("-")) {

            } else if (geoForceSensors.equalsIgnoreCase("*")) {
                devicesService.generateCraneData(d);
            } else if (Arrays.asList(geoForceSensors.split(",")).stream().filter( serial -> {
                return serial.equalsIgnoreCase(d.getDeviceSerial());
            }).findFirst().isPresent()) {
                devicesService.generateCraneData(d);
            }
        });
        devices.forEach( d -> {
            devicesService.generateCraneData(d);
        });
        log.info("######################### finalizado generateData #########################");
    }

    public LocalDateTime findNow() {
        return LocalDateTime.now().minusHours(3);
    }

    public void generateCalibrationData() {
        log.info("######################### iniciado generateCalibrationData #########################");
        List<Calibrations> calibrations = calibrationsRepository.findAllByDoneIsNull();
        List<CalibrationData> datas = calibrationDataRepository.findAllNotDone();
        calibrations.forEach(c -> {
            List<CalibrationData> cdList = datas.stream()
                    .filter( d -> {
                        return  d.getCalibrationId().toString().equals(c.getCalibrationId().toString());
                    }).collect(Collectors.toList());
            Integer step = 1;
            cdList.sort((a,b) -> {
                return b.getCollected().compareTo(a.getCollected());
            });
            Optional<CalibrationData> lastCd = cdList.stream()
                    .findFirst();
            if (lastCd.isPresent()) {
                if (lastCd.get().isBestHumidity()) {
                    if (!lastCd.get().isHumidityM2()) {
                        step = 2;
                    } else if (!lastCd.get().isHumidityP2()) {
                        step = 3;
                    } else if (!lastCd.get().isHumidityP4()) {
                        step = 4;
                    } else {
                        step = 0;
                    }
                }
            }
            if (step > 0) {
                List<CalibrationData> stepList = findByStep(step, cdList);
                if (stepList.size() < 5) {
                    List<Sensors> sensors = sensorsService
                            .findToCalibrations(c.getDeviceSerial(), lastCd.isPresent()?
                                    lastCd.get().getCollected() : LocalDateTime.now().minusHours(1));

                    sensors.sort((a,b) -> {
                        return a.getCollected().compareTo(b.getCollected());
                    });
                    for (int i = stepList.size(); i < 5;i++) {
                        if (!sensors.isEmpty() && sensors.size() > i ) {
                            CalibrationData data = new CalibrationData();
                            Sensors sensor = sensors.get(i);
                            data.setValue(sensor.getValue());
                            data.setStep(step);
                            data.setCollected(sensor.getCollected());
                            data.setCalibrationId(c.getCalibrationId());
                            data.setDataId(sensor.getId());
                            if (lastCd.isPresent()) {
                                data.setBestHumidity(lastCd.get().isBestHumidity());
                                data.setHumidityM2(lastCd.get().isHumidityM2());
                                data.setHumidityP2(lastCd.get().isHumidityP2());
                                data.setHumidityP4(lastCd.get().isHumidityP4());
                            }
                            calibrationDataRepository.save(data);
                            log.info("Gerando callibration data to device " + sensor.getDeviceSerial() + " step " + step + " calibration_id " + c.getCalibrationId());
                        }
                    }
                }
            }
        });
        log.info("######################### finalizado generateCalibrationData #########################");
    }

    public List<CalibrationData> findByStep(Integer step, List<CalibrationData> cdList) {
        return cdList.stream().filter( ccd -> {
            return ccd.getStep().intValue() == step.intValue();
        }).collect(Collectors.toList());
    }
}
