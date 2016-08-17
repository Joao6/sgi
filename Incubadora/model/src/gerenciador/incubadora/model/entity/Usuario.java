package gerenciador.incubadora.model.entity;

import gerenciador.incubadora.model.base.BaseEntity;

public class Usuario extends BaseEntity {

    public static final String TIPO_USUARIO_INCUBADORA = "1";
    public static final String TIPO_USUARIO_EMPREENDEDOR = "2";
    public static final String TIPO_USUARIO_AVALIADOR = "3";

    private String nome;
    private String sobrenome;
    private String telefone;
    private String email;
    private String senha;
    private String tipoUsuario;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

}
