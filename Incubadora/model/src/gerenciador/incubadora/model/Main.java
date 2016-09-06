package gerenciador.incubadora.model;

import gerenciador.incubadora.model.entity.Empreendimento;
import gerenciador.incubadora.model.service.EmpreendimentoService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Joao
 */
public class Main {

    public static void main(String[] args) throws Exception{
        
        EmpreendimentoService s = new EmpreendimentoService();
        List<Empreendimento> empreendimentoList = new ArrayList<>();
        Map<String, Object> criteria = new HashMap<>();
        criteria.put("0", 7L);
        empreendimentoList = s.readByCriteria(criteria);
        
    }
}
