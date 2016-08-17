package gerenciador.incubadora.model.entity;

import gerenciador.incubadora.model.base.BaseEntity;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Rafael
 */
public class RespostaDuvida extends BaseEntity {

    private String resposta;
    private Date dataHora;
    private Usuario usuario;
    private Duvida duvida;
    private List<ComentarioRespostaDuvida> comentRespDuvidaList;

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Duvida getDuvida() {
        return duvida;
    }

    public void setDuvida(Duvida duvida) {
        this.duvida = duvida;
    }

   public List<ComentarioRespostaDuvida> getComentRespDuvidaList() {
      return comentRespDuvidaList;
   }

   public void setComentRespDuvidaList(List<ComentarioRespostaDuvida> comentRespDuvidaList) {
      this.comentRespDuvidaList = comentRespDuvidaList;
   }

}
