package br.com.spintec.logicae.geobatch.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "sensors")
public class Sensors {

  @Id
  @Column(name = "id", columnDefinition = "BINARY(16)")
  private UUID id;
  private String username;

  @Column(name = "user_id", columnDefinition = "BINARY(16)")
  private UUID userId;

  @Column(name = "device_serial")
  private String deviceSerial;
  private LocalDateTime collected;
  private LocalDateTime created;
  private String version;
  private String type;
  private Double value;
  private Long port;
  private String tags;
  private String model;


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getDeviceSerial() {
    return deviceSerial;
  }

  public void setDeviceSerial(String deviceSerial) {
    this.deviceSerial = deviceSerial;
  }

  public LocalDateTime getCollected() {
    return collected;
  }

  public void setCollected(LocalDateTime collected) {
    this.collected = collected;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public void setPort(Long port) {
    this.port = port;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }


  public long getPort() {
    return port;
  }


  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }


  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
  }
}
