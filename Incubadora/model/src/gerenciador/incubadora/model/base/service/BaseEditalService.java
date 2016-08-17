package gerenciador.incubadora.model.base.service;

import gerenciador.incubadora.model.base.BaseService;
import gerenciador.incubadora.model.entity.Edital;
import java.util.Map;

/**
 *
 * @author Rafael-pc
 */
public interface BaseEditalService extends BaseService<Edital> {
    public void updateDataProrrogacao(Edital e) throws Exception;
}
