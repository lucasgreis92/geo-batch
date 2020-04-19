package br.com.spintec.logicae.geobatch.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "calibrations")
@TypeDef(
        name = "jsonb-node",
        typeClass = JsonNodeBinaryType.class
)
public class Calibrations {

    @Id
    @Column(name = "calibration_id", columnDefinition = "BINARY(16)")
    private UUID calibrationId;

    @Column
    private String username;

    @Column
    private String description;

    @Column
    private LocalDateTime created;

    @Column(name = "device_serial")
    private String deviceSerial;

    @Column(name = "material_id", columnDefinition = "BINARY(16)")
    private UUID materialId;

    @Column
    private LocalDateTime done;

    @Column(name = "building_id", columnDefinition = "BINARY(16)")
    private UUID buildingId;

    @Type( type = "jsonb-node" )
    @Column(name = "coefficients", columnDefinition = "jsonb")
    private JsonNode coefficients;

    @Column(name = "humidity_m_2")
    private Double humidityM2;

    @Column(name = "humidity_p_2")
    private Double humidityP2;

    @Column(name = "humidity_p_4")
    private Double humidityP4;

    @Column(name = "best_humidity")
    private Double bestHumidity;

    @Column(name = "\"number\"")
    private Long number;

    @Column(name = "user_id", columnDefinition = "BINARY(16)")
    private UUID userId;

    public UUID getCalibrationId() {
        return calibrationId;
    }

    public void setCalibrationId(UUID calibrationId) {
        this.calibrationId = calibrationId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getDeviceSerial() {
        return deviceSerial;
    }

    public void setDeviceSerial(String deviceSerial) {
        this.deviceSerial = deviceSerial;
    }

    public UUID getMaterialId() {
        return materialId;
    }

    public void setMaterialId(UUID materialId) {
        this.materialId = materialId;
    }

    public LocalDateTime getDone() {
        return done;
    }

    public void setDone(LocalDateTime done) {
        this.done = done;
    }

    public UUID getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(UUID buildingId) {
        this.buildingId = buildingId;
    }

    public JsonNode getCoefficients() {
        return coefficients;
    }

    public void setCoefficients(JsonNode coefficients) {
        this.coefficients = coefficients;
    }

    public Double getHumidityM2() {
        return humidityM2;
    }

    public void setHumidityM2(Double humidityM2) {
        this.humidityM2 = humidityM2;
    }

    public Double getHumidityP2() {
        return humidityP2;
    }

    public void setHumidityP2(Double humidityP2) {
        this.humidityP2 = humidityP2;
    }

    public Double getHumidityP4() {
        return humidityP4;
    }

    public void setHumidityP4(Double humidityP4) {
        this.humidityP4 = humidityP4;
    }

    public Double getBestHumidity() {
        return bestHumidity;
    }

    public void setBestHumidity(Double bestHumidity) {
        this.bestHumidity = bestHumidity;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
