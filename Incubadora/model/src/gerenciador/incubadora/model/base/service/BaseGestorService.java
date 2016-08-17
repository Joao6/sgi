package gerenciador.incubadora.model.base.service;

import gerenciador.incubadora.model.base.BaseService;
import gerenciador.incubadora.model.entity.Gestor;

public interface BaseGestorService extends BaseService<Gestor> {

  public void getCodedEntity(Gestor incubadora) throws Exception;
}
