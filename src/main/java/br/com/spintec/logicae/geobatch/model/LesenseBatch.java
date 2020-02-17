package br.com.spintec.logicae.geobatch.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "lesense_batch")
public class LesenseBatch {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Long id;

  @Column(name = "dt_create")
  private LocalDateTime dtCreate;

  @Column(name = "dt_finished")
  private LocalDateTime dtFinished;

  @Column(name = "qtd_rows_generated")
  private Long qtdRowsGenerated;

  @Column(name = "qtd_rows_sended")
  private Long qtdRowsSended;

  @Column(name = "error")
  private String error;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDateTime getDtCreate() {
    return dtCreate;
  }

  public void setDtCreate(LocalDateTime dtCreate) {
    this.dtCreate = dtCreate;
  }

  public LocalDateTime getDtFinished() {
    return dtFinished;
  }

  public void setDtFinished(LocalDateTime dtFinished) {
    this.dtFinished = dtFinished;
  }

  public Long getQtdRowsGenerated() {
    return qtdRowsGenerated;
  }

  public void setQtdRowsGenerated(Long qtdRowsGenerated) {
    this.qtdRowsGenerated = qtdRowsGenerated;
  }

  public Long getQtdRowsSended() {
    return qtdRowsSended;
  }

  public void setQtdRowsSended(Long qtdRowsSended) {
    this.qtdRowsSended = qtdRowsSended;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }
}
