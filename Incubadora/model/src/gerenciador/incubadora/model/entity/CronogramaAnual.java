package gerenciador.incubadora.model.entity;

import gerenciador.incubadora.model.base.BaseEntity;
import java.sql.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author William
 */
public class CronogramaAnual extends BaseEntity {

    public static final String STATUS_REALIZADO = "Realizado";
    public static final String STATUS_NAO_REALIZADO = "Não Realizado";

    @DateTimeFormat(pattern = "dd/MM/YYYY")
    private List<Gestor> gestorList;
    private CronogramaManager cronogramaManager;
    private List<PraticaChave> praticaList;
    private Date dataInicio;
    private Date dataFim;
    private String atividade;
    private String status;
    private String comentario;
    private String totalHoras;

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

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    public CronogramaManager getCronogramaManager() {
        return cronogramaManager;
    }
    
    public void setCronogramaManager(CronogramaManager cronogramaManager) {
        this.cronogramaManager = cronogramaManager;
    }

    public List<PraticaChave> getPraticaList() {
        return praticaList;
    }

    public void setPraticaList(List<PraticaChave> praticaList) {
        this.praticaList = praticaList;
    }

    public List<Gestor> getGestorList() {
        return gestorList;
    }

    public void setGestorList(List<Gestor> gestorList) {
        this.gestorList = gestorList;
    }

   public String getTotalHoras() {
      return totalHoras;
   }

   public void setTotalHoras(String totalHoras) {
      this.totalHoras = totalHoras;
   }
}
