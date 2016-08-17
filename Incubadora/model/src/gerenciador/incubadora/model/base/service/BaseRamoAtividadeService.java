package gerenciador.incubadora.model.base.service;

import gerenciador.incubadora.model.base.BaseService;
import gerenciador.incubadora.model.entity.RamoAtividade;

public interface BaseRamoAtividadeService extends BaseService<RamoAtividade> {

  public void getCodedEntity(RamoAtividade ramoAtividade) throws Exception;
}
