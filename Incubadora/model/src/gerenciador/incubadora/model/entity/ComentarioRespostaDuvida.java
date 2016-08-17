package gerenciador.incubadora.model.entity;

import gerenciador.incubadora.model.base.BaseEntity;
import java.util.Date;

/**
 *
 * @author William
 */
public class ComentarioRespostaDuvida extends BaseEntity {

   private String comentario;
   private Date dataHora;
   private Usuario usuario;
   private Duvida duvida;

   public Usuario getUsuario() {
      return usuario;
   }

   public void setUsuario(Usuario usuario) {
      this.usuario = usuario;
   }

   public String getComentario() {
      return comentario;
   }

   public void setComentario(String comentario) {
      this.comentario = comentario;
   }

   public Duvida getDuvida() {
      return duvida;
   }

   public void setDuvida(Duvida duvida) {
      this.duvida = duvida;
   }

   public Date getDataHora() {
      return dataHora;
   }

   public void setDataHora(Date dataHora) {
      this.dataHora = dataHora;
   }

}
