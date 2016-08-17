package gerenciador.incubadora.model.entity;

import gerenciador.incubadora.model.base.BaseEntity;
import java.util.List;

/**
 *
 * @author William
 */
public class CronogramaManager extends BaseEntity {

   private String[] menagerNames;
   private String [] praticaChaveNames;
   private List<Gestor> gestorList;
   private List<PraticaChave> praticaList;
   private CronogramaAnual cronogramaAnual;
   

   public String[] getManagerNames() {
      return menagerNames;
   }

   public void setManagerNames(String[] menagerNames) {
      this.menagerNames = menagerNames;
   }

   public CronogramaAnual getCronogramaAnual() {
      return cronogramaAnual;
   }

   public void setCronogramaAnual(CronogramaAnual cronogramaAnual) {
      this.cronogramaAnual = cronogramaAnual;
   }

  public String[] getPraticaChaveNames() {
    return praticaChaveNames;
  }

  public void setPraticaChaveNames(String[] praticaChaveNames) {
    this.praticaChaveNames = praticaChaveNames;
  }

  public List<PraticaChave> getPraticaList() {
    return praticaList;
  }

  public void setPraticaList(List<PraticaChave> praticaList) {
    this.praticaList = praticaList;
  }

    public String[] getMenagerNames() {
        return menagerNames;
    }

    public void setMenagerNames(String[] menagerNames) {
        this.menagerNames = menagerNames;
    }

    public List<Gestor> getGestorList() {
        return gestorList;
    }

    public void setGestorList(List<Gestor> gestorList) {
        this.gestorList = gestorList;
    }


}
