package gerenciador.incubadora.model.base.service;

import gerenciador.incubadora.model.entity.Avaliacao;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Joao
 */
public interface BaseAvaliacaoService {
    
    public Map<String, List<Avaliacao>> getAvaliacaoEmpreendimento(Long idEmpreendimento) throws Exception;   
    
    public Map<String, Double> getNotaEixoAvaliador(Long idEmpreendimento, Long idAvaliador) throws Exception;
    
}
