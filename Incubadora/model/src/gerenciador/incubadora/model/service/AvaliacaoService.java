package gerenciador.incubadora.model.service;

import gerenciador.incubadora.model.ConnectionManager;
import gerenciador.incubadora.model.base.service.BaseAvaliacaoService;
import gerenciador.incubadora.model.dao.AvaliacaoDAO;
import gerenciador.incubadora.model.entity.Avaliacao;
import gerenciador.incubadora.model.entity.Usuario;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Joao
 */
public class AvaliacaoService implements BaseAvaliacaoService {

    @Override
    public void create(Avaliacao e) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Avaliacao readById(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Avaliacao> readByCriteria(Map<String, Object> criteria) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Avaliacao e) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, String> validateForCreate(Map<String, Object> fields) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, String> validateForUpdate(Map<String, Object> fields) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, List<Avaliacao>> getAvaliacaoEmpreendimento(Long idEmpreendimento) throws Exception {

        Map<String, List<Avaliacao>> avaliacaoAvaliador = new HashMap<>();

        Connection conn = ConnectionManager.getInstance().getConnection();

        AvaliacaoDAO dao = new AvaliacaoDAO();
        avaliacaoAvaliador = dao.getAvaliacaoEmpreendimento(conn, idEmpreendimento);
        conn.close();

        return avaliacaoAvaliador;
    }

}
