package gerenciador.incubadora.model.entity;

import gerenciador.incubadora.model.base.BaseEntity;
import java.util.List;

public class ProcessoChave extends BaseEntity {

  private String nome;
  private Usuario gestor;
  private ArquivoProcesso anexo;

  private List<PraticaChave> praticaChaveList;

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public List<PraticaChave> getPraticaChaveList() {
    return praticaChaveList;
  }

  public void setPraticaChaveList(List<PraticaChave> praticaChaveList) {
    this.praticaChaveList = praticaChaveList;
  }
  
  public ArquivoProcesso getAnexo() {
    return anexo;
  }

  public void setAnexo(ArquivoProcesso anexo) {
    this.anexo = anexo;
  }

    public Usuario getGestor() {
        return gestor;
    }

    public void setGestor(Usuario gestor) {
        this.gestor = gestor;
    }


}
