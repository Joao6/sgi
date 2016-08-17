/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.incubadora.model.entity;

import gerenciador.incubadora.model.base.BaseEntity;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author William
 */
public class PraticaChave extends BaseEntity implements Serializable {

  @DateTimeFormat(pattern = "dd/MM/yyyy")
  private String nome;
  private String descricao;
  private Date dataEvolucao;
  private Date dataCertificacao;
  private EstagioEvolucao estagioEvolucao;
  private List<ArquivoPratica> arquivos;
  private ProcessoChave processoChave;

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public EstagioEvolucao getEstagioEvolucao() {
    return estagioEvolucao;
  }

  public void setEstagioEvolucao(EstagioEvolucao estagioEvolucao) {
    this.estagioEvolucao = estagioEvolucao;
  }

  public Date getDataEvolucao() {
    return dataEvolucao;
  }

  public void setDataEvolucao(Date dataEvolucao) {
    this.dataEvolucao = dataEvolucao;
  }

  public Date getDataCertificacao() {
    return dataCertificacao;
  }

  public void setDataCertificacao(Date dataCertificacao) {
    this.dataCertificacao = dataCertificacao;
  }

  public ProcessoChave getProcessoChave() {
    return processoChave;
  }

  public void setProcessoChave(ProcessoChave processoChave) {
    this.processoChave = processoChave;
  }

  public List<ArquivoPratica> getArquivos() {
    return arquivos;
  }

  public void setArquivos(List<ArquivoPratica> arquivos) {
    this.arquivos = arquivos;
  }

}
