package gerenciador.incubadora.model.entity;

import gerenciador.incubadora.model.base.BaseEntity;

/**
 *
 * @author Rafael
 */
public class ApresentacaoNegocio extends BaseEntity {

    private String miniCurriculo;
    private String disponibilidade;
    private String inovacaoProduto;
    private String tempoDesenvolvimento;
    private String identificacao;//melhorar nome desse atributo
    private String mercadoAlvo;
    private String vantagemCompetitiva;
    private String investimento;
    private String retorno;
    private String parcerias;
    private String estruturaOrganizacional;
    private Empreendimento empreendimento;

    public String getMiniCurriculo() {
        return miniCurriculo;
    }

    public void setMiniCurriculo(String miniCurriculo) {
        this.miniCurriculo = miniCurriculo;
    }

    public String getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public String getInovacaoProduto() {
        return inovacaoProduto;
    }

    public void setInovacaoProduto(String inovacaoProduto) {
        this.inovacaoProduto = inovacaoProduto;
    }

    public String getTempoDesenvolvimento() {
        return tempoDesenvolvimento;
    }

    public void setTempoDesenvolvimento(String tempoDesenvolvimento) {
        this.tempoDesenvolvimento = tempoDesenvolvimento;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getMercadoAlvo() {
        return mercadoAlvo;
    }

    public void setMercadoAlvo(String mercadoAlvo) {
        this.mercadoAlvo = mercadoAlvo;
    }

    public String getVantagemCompetitiva() {
        return vantagemCompetitiva;
    }

    public void setVantagemCompetitiva(String vantagemCompetitiva) {
        this.vantagemCompetitiva = vantagemCompetitiva;
    }

    public String getInvestimento() {
        return investimento;
    }

    public void setInvestimento(String investimento) {
        this.investimento = investimento;
    }

    public String getRetorno() {
        return retorno;
    }

    public void setRetorno(String retorno) {
        this.retorno = retorno;
    }

    public String getParcerias() {
        return parcerias;
    }

    public void setParcerias(String parcerias) {
        this.parcerias = parcerias;
    }

    public String getEstruturaOrganizacional() {
        return estruturaOrganizacional;
    }

    public void setEstruturaOrganizacional(String estruturaOrganizacional) {
        this.estruturaOrganizacional = estruturaOrganizacional;
    }

    public Empreendimento getEmpreendimento() {
        return empreendimento;
    }

    public void setEmpreendimento(Empreendimento empreendimento) {
        this.empreendimento = empreendimento;
    }

}
