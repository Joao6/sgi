package gerenciador.incubadora.model.base.service;

import gerenciador.incubadora.model.base.BaseService;
import gerenciador.incubadora.model.entity.Nota;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Rafael
 */
public interface BaseNotaService extends BaseService<Nota> {
    
        public Map<String, Double> getAvaliacao(Long idEmpreendimento) throws Exception;               
        
        public Map<String, Double> getNotaAvaliador(Long idAvaliador, Long idEmpreendimento) throws Exception;
        
        public void updateNotaEmpreendimento(Nota e) throws Exception;


}
