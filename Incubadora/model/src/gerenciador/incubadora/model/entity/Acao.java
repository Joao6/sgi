package gerenciador.incubadora.model.entity;

import gerenciador.incubadora.model.base.BaseEntity;
import java.sql.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author William
 */
public class Acao extends BaseEntity {

   @DateTimeFormat(pattern = "dd/MM/YYYY")
   private Date dataInicio;
   private Date dataFim;
   private Categoria categoria;
   private String nome;
   private String responsavel;
   private Empreendimento empreendimento;

   public Categoria getCategoria() {
      return categoria;
   }

   public void setCategoria(Categoria categoria) {
      this.categoria = categoria;
   }

   public String getResponsavel() {
      return responsavel;
   }

   public void setResponsavel(String responsavel) {
      this.responsavel = responsavel;
   }

   public Empreendimento getEmpreendimento() {
      return empreendimento;
   }

   public void setEmpreendimento(Empreendimento empreendimento) {
      this.empreendimento = empreendimento;
   }

   public Date getDataInicio() {
      return dataInicio;
   }

   public void setDataInicio(Date dataInicio) {
      this.dataInicio = dataInicio;
   }

   public Date getDataFim() {
      return dataFim;
   }

   public void setDataFim(Date dataFim) {
      this.dataFim = dataFim;
   }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
