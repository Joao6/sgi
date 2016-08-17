package gerenciador.incubadora.model.base.service;

import gerenciador.incubadora.model.base.BaseService;
import gerenciador.incubadora.model.entity.Orientacao;

/**
 *
 * @author William
 */
public interface BaseOrientacaoService extends BaseService<Orientacao> {

   public void getCodedEntity(Orientacao orientacao) throws Exception;
}
