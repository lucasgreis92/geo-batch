package br.com.spintec.logicae.geobatch.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "contracts")
public class Contracts {

  @Id
  @Column(name = "contract_id", columnDefinition = "BINARY(16)")
  private UUID contractId;

  private String username;
  private String description;
  private LocalDateTime created;
  private LocalDateTime expires;
  private boolean disabled;
  @Column(name= "user_id", columnDefinition = "BINARY(16)")
  private UUID userId;
  private String identifier;

  public UUID getContractId() {
    return contractId;
  }

  public void setContractId(UUID contractId) {
    this.contractId = contractId;
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

  public LocalDateTime getExpires() {
    return expires;
  }

  public void setExpires(LocalDateTime expires) {
    this.expires = expires;
  }

  public boolean isDisabled() {
    return disabled;
  }

  public void setDisabled(boolean disabled) {
    this.disabled = disabled;
  }

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
  }
}
