package br.com.spintec.logicae.geobatch.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "crane_data")
public class CraneData {

    @Id
    @Column(name = "data_id", columnDefinition = "BINARY(16)")
    private UUID id;

    private String username;

    @Column(name = "device_serial")
    private String deviceSerial;

    private LocalDateTime created;

    @Column(name = "\"on\"")
    private LocalDateTime on;

    @Column(name = "\"off\"")
    private LocalDateTime off;

    private Integer port;

    private Double value;

    @Column(name = "user_id")
    private UUID userId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDeviceSerial() {
        return deviceSerial;
    }

    public void setDeviceSerial(String deviceSerial) {
        this.deviceSerial = deviceSerial;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getOn() {
        return on;
    }

    public void setOn(LocalDateTime on) {
        this.on = on;
    }

    public LocalDateTime getOff() {
        return off;
    }

    public void setOff(LocalDateTime off) {
        this.off = off;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
