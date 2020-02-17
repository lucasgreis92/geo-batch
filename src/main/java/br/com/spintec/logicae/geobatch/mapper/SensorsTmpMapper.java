package br.com.spintec.logicae.geobatch.mapper;

import br.com.spintec.logicae.geobatch.dto.SensorsDtoV1;
import br.com.spintec.logicae.geobatch.model.Sensors;
import br.com.spintec.logicae.geobatch.model.SensorsTmp;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Component
public class SensorsTmpMapper extends AbstractMapper<SensorsTmp, SensorsDtoV1> {

    @Override
    public SensorsTmp convertToModel(SensorsDtoV1 dto, SensorsTmp model) {
        model = new SensorsTmp();
        model.setId(dto.getId());
        model.setDeviceSerial(dto.getSerial());
        model.setModel(dto.getModel());
        model.setCollected(Instant.ofEpochMilli(dto.getTimestamp()*1000).atZone(ZoneId.systemDefault()).toLocalDateTime());
        model.setVersion(dto.getVersion());
        model.setValue(dto.getValue());
        long port = (long) dto.getSensor();
        model.setPort(port);
        model.setType(model.getType());
        model.setCreated(LocalDateTime.now());
        return model;
    }

    public List<SensorsTmp> convertToModelList(List<SensorsDtoV1> dtos) {
        List<SensorsTmp> retorno = new ArrayList<>();
        dtos.forEach( dto -> {
            retorno.add(convertToModel(dto,null));
        });
        return retorno;
    }

    @Override
    public SensorsDtoV1 convertToDto(SensorsTmp model) {
        return null;
    }

    public List<Sensors> tmpToSensorsList(List<SensorsTmp> sensores) {
        List<Sensors> retorno = new ArrayList<>();
        sensores.forEach( tmp -> {
            retorno.add(tmpToSensors(tmp));
        });
        return retorno;
    }

    public Sensors tmpToSensors(SensorsTmp sensorsTmp) {
        Sensors sensors = new Sensors();
        sensors.setValue(sensorsTmp.getValue());
        sensors.setCollected(sensorsTmp.getCollected());
        sensors.setDeviceSerial(sensorsTmp.getDeviceSerial());
        sensors.setCreated(sensorsTmp.getCreated());
        sensors.setId(sensorsTmp.getId());
        sensors.setModel(sensorsTmp.getModel());
        sensors.setPort(sensorsTmp.getPort());
        //sensors.setUserId(sensorsTmp.getUserId());
        //sensors.setUsername(sensorsTmp.getUsername());
        //sensors.setTags(sensorsTmp.getTags());
        sensors.setType(sensorsTmp.getType());
        sensors.setVersion(sensorsTmp.getVersion());
        return sensors;
    }














}
