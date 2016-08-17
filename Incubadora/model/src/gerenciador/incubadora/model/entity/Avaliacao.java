package gerenciador.incubadora.model.entity;

import gerenciador.incubadora.model.base.BaseEntity;

/**
 *
 * @author Joao
 */
public class Avaliacao extends BaseEntity{
    
    private Usuario usuario;
    private CriterioAvaliacao criterioAvaliacao;
    private Double nota;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }      

    public CriterioAvaliacao getCriterioAvaliacao() {
        return criterioAvaliacao;
    }

    public void setCriterioAvaliacao(CriterioAvaliacao criterioAvaliacao) {
        this.criterioAvaliacao = criterioAvaliacao;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }
    
}
