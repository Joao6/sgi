/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.incubadora.model.base.service;

import gerenciador.incubadora.model.base.BaseService;
import gerenciador.incubadora.model.entity.PraticaChave;

/**
 *
 * @author William
 */
public interface BasePraticaChaveService extends BaseService<PraticaChave> {

  public void getCodedEntity(PraticaChave praticaChave) throws Exception;
}
