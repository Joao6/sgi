package gerenciador.incubadora.model.entity;

import gerenciador.incubadora.model.base.BaseEntity;
import java.util.Date;

/**
 *
 * @author Rafael-pc
 */
public class Edital extends BaseEntity {

    private String nome;
    private String resumo;
    private Date dataInicio;
    private Date dataFim;
    private Date dataProrrogacao;
    private Gestor gestor;
    private String pathArquivo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
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

    public Date getDataProrrogacao() {
        return dataProrrogacao;
    }

    public void setDataProrrogacao(Date dataProrrogacao) {
        this.dataProrrogacao = dataProrrogacao;
    }

    public Gestor getGestor() {
        return gestor;
    }

    public void setGestor(Gestor gestor) {
        this.gestor = gestor;
    }

    public String getPathArquivo() {
        return pathArquivo;
    }

    public void setPathArquivo(String pathArquivo) {
        this.pathArquivo = pathArquivo;
    }

}
