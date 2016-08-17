package gerenciador.incubadora.model.service;

import gerenciador.incubadora.model.ConnectionManager;
import gerenciador.incubadora.model.ErrorMessage;
import gerenciador.incubadora.model.base.service.BaseCriterioAvaliacaoService;
import gerenciador.incubadora.model.dao.CriterioAvaliacaoDAO;
import gerenciador.incubadora.model.entity.CriterioAvaliacao;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Rafael
 */
public class CriterioAvaliacaoService implements BaseCriterioAvaliacaoService {

    @Override
    public void create(CriterioAvaliacao e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            CriterioAvaliacaoDAO dao = new CriterioAvaliacaoDAO();
            dao.create(e, conn);
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            conn.rollback();
            conn.close();
            throw ex;
        }
    }

    @Override
    public CriterioAvaliacao readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        CriterioAvaliacaoDAO dao = new CriterioAvaliacaoDAO();
        CriterioAvaliacao criterioAvaliacao = dao.readById(id, conn);
        conn.close();
        return criterioAvaliacao;
    }

    @Override
    public List<CriterioAvaliacao> readByCriteria(Map<String, Object> criteria) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        CriterioAvaliacaoDAO dao = new CriterioAvaliacaoDAO();
        List<CriterioAvaliacao> criterioAvaliacaoList = dao.readByCriteria(criteria, conn);
        conn.close();
        return criterioAvaliacaoList;
    }

    @Override
    public void update(CriterioAvaliacao e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            CriterioAvaliacaoDAO dao = new CriterioAvaliacaoDAO();
            dao.update(e, conn);
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            conn.rollback();
            conn.close();
            throw ex;
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            CriterioAvaliacaoDAO dao = new CriterioAvaliacaoDAO();
            dao.delete(id, conn);
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            conn.rollback();
            conn.close();
            throw ex;
        }
    }

    @Override
    public Map<String, String> validateForCreate(Map<String, Object> fields) throws Exception {
        Map<String, String> errors = new HashMap<String, String>();
        
        String nome = (String) fields.get("nome");
        if(nome == null || nome.isEmpty()){
            errors.put("nome", ErrorMessage.CAMPO_OBRIGATORIO);
        }else{
            //criteria nome eq...
        }
        
        return errors;
    }

    @Override
    public Map<String, String> validateForUpdate(Map<String, Object> fields) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
