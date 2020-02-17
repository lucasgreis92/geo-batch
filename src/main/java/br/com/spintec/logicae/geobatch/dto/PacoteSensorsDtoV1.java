package br.com.spintec.logicae.geobatch.dto;

import java.util.List;
import java.util.Optional;

public class PacoteSensorsDtoV1 {

/*    private String serial;*/
    private List<SensorsDtoV1> data;

    public PacoteSensorsDtoV1() {
    }

    public PacoteSensorsDtoV1(List<SensorsDtoV1> data) {
        this.data = data;
        Optional<SensorsDtoV1> s = data.stream().findFirst();
/*        if (s.isPresent()) {
            this.serial = s.get().getSerial();
        }*/
    }

    public List<SensorsDtoV1> getData() {
        return data;
    }

    public void setData(List<SensorsDtoV1> data) {
        this.data = data;
    }
/*
    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }*/
}
