package gerenciador.incubadora.model.base.service;

import gerenciador.incubadora.model.base.BaseService;
import gerenciador.incubadora.model.entity.CronogramaAnual;
import gerenciador.incubadora.model.entity.CronogramaManager;

/**
 *
 * @author William
 */
public interface BaseCronogramaMenagerService extends BaseService<CronogramaManager> {
  
  public boolean insertGestor(CronogramaAnual ca) throws Exception;
  
  public boolean insertPraticaChave(CronogramaAnual ca) throws Exception;

  public boolean deleteGestor(Long idCronograma, Long idGestor) throws Exception;

  public boolean deletePraticaChave(CronogramaAnual cronogramaAnual) throws Exception;
}
