package gerenciador.incubadora.model.entity;

import gerenciador.incubadora.model.base.BaseEntity;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class Empreendimento extends BaseEntity {

    public static final String EMPREENDIMENTO_STATUS_ENVIADA = "Proposta Enviada";
    public static final String EMPREENDIMENTO_STATUS_AP_AGENDADA = "Apresentação Agendada";
    public static final String EMPREENDIMENTO_STATUS_AP_REALIZADA = "Apresentação Realizada";
    public static final String EMPREENDIMENTO_STATUS_AV_REALIZADA = "Avaliação Realizada";
    public static final String EMPREENDIMENTO_STATUS_APROVADO = "Aprovado";
    public static final String EMPREENDIMENTO_STATUS_REPROVADO = "Não Aprovado";
    public static final String EMPREENDIMENTO_STATUS_CANCELADO = "Cancelado";    

    public static final String EMPREENDIMENTO_PATH_LOGO_DEFAULT = "C:\\imagens\\empreendimento\\default.png";
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String nome;
    private String razaoSocial;
    private String cnpj;
    private String inscricaoEstadual;
    private String inscricaoMunicipal;
    private String email;
    private String fax;
    private Date dataIngresso;
    private Date dataAbertura;
    private Date dataPrevisaoGraduacao;
    private String missao;
    private String visao;
    private String valores;
    private RamoAtividade ramoAtividade;
    private List<Empreendedor> empreendedorList;
    private String logo;
    private String status;
    private String telefone;
    private ApresentacaoNegocio apresentacaoNegocio;
    private Edital edital;
    private List<Nota> notaList;
    private List<Avaliador> avaliadorList;
    private List<String> statusList;
    private Boolean contratacaoAceita;
    private Date dataHoraApresentacao;
    private String localApresentacao;
    private String descricaoResultado;

    public Empreendimento() {
        this.empreendedorList = new ArrayList<>();
        avaliadorList = new ArrayList<>();
    }

    public String getDescricaoResultado() {
        return descricaoResultado;
    }

    public void setDescricaoResultado(String descricaoResultado) {
        this.descricaoResultado = descricaoResultado;
    }
        
    public String getMissao() {
        return missao;
    }

    public void setMissao(String missao) {
        this.missao = missao;
    }

    public String getVisao() {
        return visao;
    }

    public void setVisao(String visao) {
        this.visao = visao;
    }

    public String getValores() {
        return valores;
    }

    public void setValores(String valores) {
        this.valores = valores;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Date getDataIngresso() {
        return dataIngresso;
    }

    public void setDataIngresso(Date dataIngresso) {
        this.dataIngresso = dataIngresso;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public RamoAtividade getRamoAtividade() {
        return ramoAtividade;
    }

    public void setRamoAtividade(RamoAtividade ramoAtividade) {
        this.ramoAtividade = ramoAtividade;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Date getDataPrevisaoGraduacao() {
        return dataPrevisaoGraduacao;
    }

    public void setDataPrevisaoGraduacao(Date dataPrevisaoGraduacao) {
        this.dataPrevisaoGraduacao = dataPrevisaoGraduacao;
    }

    public List<Empreendedor> getEmpreendedorList() {
        return empreendedorList;
    }

    public void setEmpreendedorList(List<Empreendedor> empreendedorList) {
        this.empreendedorList = empreendedorList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getInscricaoMunicipal() {
        return inscricaoMunicipal;
    }

    public void setInscricaoMunicipal(String inscricaoMunicipal) {
        this.inscricaoMunicipal = inscricaoMunicipal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public ApresentacaoNegocio getApresentacaoNegocio() {
        return apresentacaoNegocio;
    }

    public void setApresentacaoNegocio(ApresentacaoNegocio apresentacaoNegocio) {
        this.apresentacaoNegocio = apresentacaoNegocio;
    }

    public Edital getEdital() {
        return edital;
    }

    public void setEdital(Edital edital) {
        this.edital = edital;
    }

    public List<Nota> getNotaList() {
        return notaList;
    }

    public void setNotaList(List<Nota> notaList) {
        this.notaList = notaList;
    }

    public List<Avaliador> getAvaliadorList() {
        return avaliadorList;
    }

    public void setAvaliadorList(List<Avaliador> avaliadorList) {
        this.avaliadorList = avaliadorList;
    }

    public Boolean getContratacaoAceita() {
        return contratacaoAceita;
    }

    public void setContratacaoAceita(Boolean contratacaoAceita) {
        this.contratacaoAceita = contratacaoAceita;
    }

    public void setDataHoraApresentacao(Date dataHoraApresentacao) {
        this.dataHoraApresentacao = dataHoraApresentacao;
    }

    public Date getDataHoraApresentacao() {
        return dataHoraApresentacao;
    }

    public String getLocalApresentacao() {
        return localApresentacao;
    }

    public void setLocalApresentacao(String localApresentacao) {
        this.localApresentacao = localApresentacao;
    }

   public List<String> getStatusList() {
      return statusList;
   }

   public void setStatusList(List<String> statusList) {
      this.statusList = statusList;
   }

}
