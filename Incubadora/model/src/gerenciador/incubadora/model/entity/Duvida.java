package gerenciador.incubadora.model.entity;

import gerenciador.incubadora.model.base.BaseEntity;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Rafael
 */
public class Duvida extends BaseEntity {

    private String duvida;
    private Date dataHora;
    private Empreendedor empreendedor;
    private List<RespostaDuvida> respostaDuvidaList;

    public String getDuvida() {
        return duvida;
    }

    public void setDuvida(String duvida) {
        this.duvida = duvida;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public Empreendedor getEmpreendedor() {
        return empreendedor;
    }

    public void setEmpreendedor(Empreendedor empreendedor) {
        this.empreendedor = empreendedor;
    }

    public List<RespostaDuvida> getRespostaDuvidaList() {
        return respostaDuvidaList;
    }

    public void setRespostaDuvidaList(List<RespostaDuvida> respostaDuvidaList) {
        this.respostaDuvidaList = respostaDuvidaList;
    }
}
