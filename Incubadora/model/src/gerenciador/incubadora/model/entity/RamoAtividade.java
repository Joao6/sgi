/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.incubadora.model.entity;

import gerenciador.incubadora.model.base.BaseEntity;

/**
 *
 * @author William
 */
public class RamoAtividade extends BaseEntity{
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
