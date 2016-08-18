package gerenciador.incubadora.model.base.service;

import gerenciador.incubadora.model.base.BaseService;
import gerenciador.incubadora.model.entity.Avaliacao;
import gerenciador.incubadora.model.entity.Usuario;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Joao
 */
public interface BaseAvaliacaoService extends BaseService<Avaliacao> {
    
    public Map<String, List<Avaliacao>> getAvaliacaoEmpreendimento(Long idEmpreendimento) throws Exception;       
    
}
