package gerenciador.incubadora.model.service;

import gerenciador.incubadora.model.ConnectionManager;
import gerenciador.incubadora.model.ErrorMessage;
import gerenciador.incubadora.model.base.service.BaseRespostaDuvidaService;
import gerenciador.incubadora.model.dao.RespostaDuvidaDAO;
import gerenciador.incubadora.model.entity.RespostaDuvida;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Rafael
 */
public class RespostaDuvidaService implements BaseRespostaDuvidaService {

    @Override
    public void create(RespostaDuvida e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            RespostaDuvidaDAO dao = new RespostaDuvidaDAO();
            
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
    public RespostaDuvida readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        RespostaDuvidaDAO dao = new RespostaDuvidaDAO();
        RespostaDuvida respostaDuvida = dao.readById(id, conn);
        conn.close();
        return respostaDuvida;
    }

    @Override
    public List<RespostaDuvida> readByCriteria(Map<String, Object> criteria) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        RespostaDuvidaDAO dao = new RespostaDuvidaDAO();
        List<RespostaDuvida> respostaDuvida = dao.readByCriteria(criteria, conn);
        conn.close();
        return respostaDuvida;
    }

    @Override
    public void update(RespostaDuvida e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            RespostaDuvidaDAO dao = new RespostaDuvidaDAO();
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
            RespostaDuvidaDAO dao = new RespostaDuvidaDAO();
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
        Map<String, String> errors = new HashMap<>();
        
        String resposta = (String) fields.get("resposta");
        if(resposta == null || resposta.isEmpty()){
            errors.put("resposta", ErrorMessage.CAMPO_OBRIGATORIO);
        }
        
        return errors;
    }

    @Override
    public Map<String, String> validateForUpdate(Map<String, Object> fields) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
