package gerenciador.incubadora.model.entity;

import gerenciador.incubadora.model.base.BaseEntity;

/**
 *
 * @author William
 */
public class Orientacao extends BaseEntity {

   private String descricao;
   private Categoria categoria;

   public String getDescricao() {
      return descricao;
   }

   public void setDescricao(String descricao) {
      this.descricao = descricao;
   }

   public Categoria getCategoria() {
      return categoria;
   }

   public void setCategoria(Categoria categoria) {
      this.categoria = categoria;
   }
}
