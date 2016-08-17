package gerenciador.incubadora.model.entity;

import gerenciador.incubadora.model.base.BaseEntity;
import java.util.Date;

/**
 *
 * @author Rafael
 */
public class Nota extends BaseEntity {

    private Avaliador avaliador;
    private CriterioAvaliacao criterioAvaliacao;
    private Empreendimento empreendimento;
    private Double nota;
    private Date dataHora;

    public Avaliador getAvaliador() {
        return avaliador;
    }

    public void setAvaliador(Avaliador avaliador) {
        this.avaliador = avaliador;
    }

    public CriterioAvaliacao getCriterioAvaliacao() {
        return criterioAvaliacao;
    }

    public void setCriterioAvaliacao(CriterioAvaliacao criterioAvaliacao) {
        this.criterioAvaliacao = criterioAvaliacao;
    }

    public Empreendimento getEmpreendimento() {
        return empreendimento;
    }

    public void setEmpreendimento(Empreendimento empreendimento) {
        this.empreendimento = empreendimento;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

}
