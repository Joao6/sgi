package gerenciador.incubadora.model.entity;

import gerenciador.incubadora.model.base.BaseEntity;

/**
 *
 * @author Rafael
 */
public class CriterioAvaliacao extends BaseEntity {

    private String nome;
    private Boolean ativo;
    private Eixo eixo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Eixo getEixo() {
        return eixo;
    }

    public void setEixo(Eixo eixo) {
        this.eixo = eixo;
    }

}
