package gerenciador.incubadora.model.entity;

import gerenciador.incubadora.model.base.BaseEntity;

/**
 *
 * @author William
 */
public class AmbienteExterno extends BaseEntity {

   private String oportunidades;
   private String ameacas;
   private Empreendimento empreendimento;

    public String getOportunidades() {
        return oportunidades;
    }

    public void setOportunidades(String oportunidades) {
        this.oportunidades = oportunidades;
    }

    public String getAmeacas() {
        return ameacas;
    }

    public void setAmeacas(String ameacas) {
        this.ameacas = ameacas;
    }

    public Empreendimento getEmpreendimento() {
        return empreendimento;
    }

    public void setEmpreendimento(Empreendimento empreendimento) {
        this.empreendimento = empreendimento;
    }
}
