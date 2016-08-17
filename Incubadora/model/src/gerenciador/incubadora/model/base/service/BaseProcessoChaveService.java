package gerenciador.incubadora.model.base.service;

import gerenciador.incubadora.model.base.BaseService;
import gerenciador.incubadora.model.entity.ProcessoChave;

public interface BaseProcessoChaveService extends BaseService<ProcessoChave> {

  public void getCodedEntity(ProcessoChave processoChave) throws Exception;
}
