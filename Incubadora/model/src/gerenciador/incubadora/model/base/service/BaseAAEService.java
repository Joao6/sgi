package gerenciador.incubadora.model.base.service;

import gerenciador.incubadora.model.base.BaseService;
import gerenciador.incubadora.model.entity.AmbienteExterno;

/**
 *
 * @author William
 */
public interface BaseAAEService extends BaseService<AmbienteExterno> {

   public void getCodedEntity(AmbienteExterno aae) throws Exception;
}
