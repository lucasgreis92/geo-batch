package br.com.spintec.logicae.geobatch.model;


import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "devices")
@NamedStoredProcedureQuery(name= "Devices.generateData",
        procedureName = "generate_data",
        parameters = {@StoredProcedureParameter(mode = ParameterMode.IN,type = String.class, name= "serial_p")})
@TypeDef(
        name = "jsonb-node",
        typeClass = JsonNodeBinaryType.class
)
public class Devices {

  @Id
  @Column(name = "device_serial")
  private String deviceSerial;
  private String token;
  private LocalDateTime created;
  private Boolean disabled;
  @Column(name="contract_id", columnDefinition = "BINARY(16)")
  private UUID contractId;
  private String status;
  @Column(name = "last_ack")
  private LocalDateTime lastAck;
  @Type( type = "jsonb-node" )
  @Column(name = "callbacks_id", columnDefinition = "jsonb")
  private JsonNode callbacksId;
  private String parent;
  @Column(name = "type_id")
  private UUID typeId;
  @Column(name = "building_id")
  private UUID buildingId;

  @Column(name = "soil_humidity_offset")
  private Double soilHumidityOffset;

  private String subtype;



  public String getDeviceSerial() {
    return deviceSerial;
  }

  public void setDeviceSerial(String deviceSerial) {
    this.deviceSerial = deviceSerial;
  }

 /* public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }*/

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  public Boolean getDisabled() {
    return disabled;
  }

  public void setDisabled(Boolean disabled) {
    this.disabled = disabled;
  }

  public UUID getContractId() {
    return contractId;
  }

  public void setContractId(UUID contractId) {
    this.contractId = contractId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public LocalDateTime getLastAck() {
    return lastAck;
  }

  public void setLastAck(LocalDateTime lastAck) {
    this.lastAck = lastAck;
  }

  public String getParent() {
    return parent;
  }

  public void setParent(String parent) {
    this.parent = parent;
  }
/*
  public Long getLastResponseCode() {
    return lastResponseCode;
  }*/

  public JsonNode getCallbacksId() {
    return callbacksId;
  }

  public void setCallbacksId(JsonNode callbacksId) {
    this.callbacksId = callbacksId;
  }
/*
  public void setLastResponseCode(Long lastResponseCode) {
    this.lastResponseCode = lastResponseCode;
  }*/

  public UUID getTypeId() {
    return typeId;
  }

  public void setTypeId(UUID typeId) {
    this.typeId = typeId;
  }

  public UUID getBuildingId() {
    return buildingId;
  }

  public void setBuildingId(UUID buildingId) {
    this.buildingId = buildingId;
  }

  public Double getSoilHumidityOffset() {
    return soilHumidityOffset;
  }

  public void setSoilHumidityOffset(Double soilHumidityOffset) {
    this.soilHumidityOffset = soilHumidityOffset;
  }

  public String getSubtype() {
    return subtype;
  }

  public void setSubtype(String subtype) {
    this.subtype = subtype;
  }
}
