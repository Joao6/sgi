package gerenciador.incubadora.model;

import gerenciador.incubadora.model.dao.EmpreendimentoDAO;
import gerenciador.incubadora.model.entity.Empreendedor;
import gerenciador.incubadora.model.entity.Empreendimento;
import gerenciador.incubadora.model.service.EmpreendedorService;
import gerenciador.incubadora.model.service.EmpreendimentoService;
import java.sql.Connection;

/**
 *
 * @author Joao
 */
public class Main {

    public static void main(String[] args) throws Exception{
        
        EmpreendimentoService s = new EmpreendimentoService();
        EmpreendedorService es = new EmpreendedorService();
        Empreendimento empreendimento = s.readById(34L);
        Empreendedor empreendedor = es.readById(4L);        
        empreendimento.getEmpreendedorList().add(empreendedor);
        EmpreendimentoDAO dao = new EmpreendimentoDAO();
        //Connection conn = ConnectionManager.getInstance().getConnection();
        //dao.update(empreendimento, conn);
        s.update(empreendimento);
        //conn.close();
        
    }
}
