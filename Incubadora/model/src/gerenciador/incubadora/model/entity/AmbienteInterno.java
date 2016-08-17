package gerenciador.incubadora.model.entity;

import gerenciador.incubadora.model.base.BaseEntity;

/**
 *
 * @author William
 */
public class AmbienteInterno extends BaseEntity {
    
	 private String capitalForte;
	 private String gestaoForte; 
	 private String empreendedorForte;
	 private String mercadoForte; 
	 private String tecnologicoForte;
         private String capitalFraco; 
	 private String gestaoFraco;
	 private String empreendedorFraco;
	 private String mercadoFraco;
	 private String tecnologicoFraco;
         private Empreendimento empreendimento;

    public String getCapitalForte() {
        return capitalForte;
    }

    public void setCapitalForte(String capitalForte) {
        this.capitalForte = capitalForte;
    }

    public String getGestaoForte() {
        return gestaoForte;
    }

    public void setGestaoForte(String gestaoForte) {
        this.gestaoForte = gestaoForte;
    }

    public String getEmpreendedorForte() {
        return empreendedorForte;
    }

    public void setEmpreendedorForte(String empreendedorForte) {
        this.empreendedorForte = empreendedorForte;
    }

    public String getMercadoForte() {
        return mercadoForte;
    }

    public void setMercadoForte(String mercadoForte) {
        this.mercadoForte = mercadoForte;
    }

    public String getTecnologicoForte() {
        return tecnologicoForte;
    }

    public void setTecnologicoForte(String tecnologicoForte) {
        this.tecnologicoForte = tecnologicoForte;
    }

    public String getCapitalFraco() {
        return capitalFraco;
    }

    public void setCapitalFraco(String capitalFraco) {
        this.capitalFraco = capitalFraco;
    }

    public String getGestaoFraco() {
        return gestaoFraco;
    }

    public void setGestaoFraco(String gestaoFraco) {
        this.gestaoFraco = gestaoFraco;
    }

    public String getEmpreendedorFraco() {
        return empreendedorFraco;
    }

    public void setEmpreendedorFraco(String empreendedorFraco) {
        this.empreendedorFraco = empreendedorFraco;
    }

    public String getMercadoFraco() {
        return mercadoFraco;
    }

    public void setMercadoFraco(String mercadoFraco) {
        this.mercadoFraco = mercadoFraco;
    }

    public String getTecnologicoFraco() {
        return tecnologicoFraco;
    }

    public void setTecnologicoFraco(String tecnologicoFraco) {
        this.tecnologicoFraco = tecnologicoFraco;
    }

    public Empreendimento getEmpreendimento() {
        return empreendimento;
    }

    public void setEmpreendimento(Empreendimento empreendimento) {
        this.empreendimento = empreendimento;
    }

}
