package br.com.spintec.logicae.geobatch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "calibration_data")
public class CalibrationData {

    @Id
    @Column(name = "data_id", columnDefinition = "BINARY(16)")
    private UUID dataId;

    @Column(name = "calibration_id", columnDefinition = "BINARY(16)")
    private UUID calibrationId;

    @Column
    private Double value;

    @Column
    private LocalDateTime collected;

    @Column(name = "best_humidity")
    private boolean bestHumidity = false;

    @Column(name = "humidity_m_2")
    private boolean humidityM2 = false;

    @Column(name = "humidity_p_2")
    private boolean humidityP2 = false;

    @Column(name = "humidity_p_4")
    private boolean humidityP4 = false;

    @Column
    private Integer step;

    public UUID getDataId() {
        return dataId;
    }

    public void setDataId(UUID dataId) {
        this.dataId = dataId;
    }

    public UUID getCalibrationId() {
        return calibrationId;
    }

    public void setCalibrationId(UUID calibrationId) {
        this.calibrationId = calibrationId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public LocalDateTime getCollected() {
        return collected;
    }

    public void setCollected(LocalDateTime collected) {
        this.collected = collected;
    }

    public boolean isBestHumidity() {
        return bestHumidity;
    }

    public void setBestHumidity(boolean bestHumidity) {
        this.bestHumidity = bestHumidity;
    }

    public boolean isHumidityM2() {
        return humidityM2;
    }

    public void setHumidityM2(boolean humidityM2) {
        this.humidityM2 = humidityM2;
    }

    public boolean isHumidityP2() {
        return humidityP2;
    }

    public void setHumidityP2(boolean humidityP2) {
        this.humidityP2 = humidityP2;
    }

    public boolean isHumidityP4() {
        return humidityP4;
    }

    public void setHumidityP4(boolean humidityP4) {
        this.humidityP4 = humidityP4;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }
}
