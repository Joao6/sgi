package gerenciador.incubadora.model.service;

import gerenciador.incubadora.model.ConnectionManager;
import gerenciador.incubadora.model.ErrorMessage;
import gerenciador.incubadora.model.base.service.BaseEixoService;
import gerenciador.incubadora.model.dao.EditalDAO;
import gerenciador.incubadora.model.dao.EixoDAO;
import gerenciador.incubadora.model.entity.Edital;
import gerenciador.incubadora.model.entity.Eixo;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Rafael
 */
public class EixoService implements BaseEixoService {

    @Override
    public void create(Eixo e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            EixoDAO dao = new EixoDAO();
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
    public Eixo readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        EixoDAO dao = new EixoDAO();
        Eixo eixo = dao.readById(id, conn);
        conn.close();
        return eixo;
    }

    @Override
    public List<Eixo> readByCriteria(Map<String, Object> criteria) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        EixoDAO dao = new EixoDAO();
        List<Eixo> eixoList = dao.readByCriteria(criteria, conn);
        conn.close();
        return eixoList;
    }

    @Override
    public void update(Eixo e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            EixoDAO dao = new EixoDAO();
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
            EixoDAO dao = new EixoDAO();
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
    public Map<String, String> validateForCreate(Map<String, Object> fields) {
        Map<String, String> errors = new HashMap<String, String>();

        String nome = (String) fields.get("nome");
        if (nome == null || nome.isEmpty()) {
            errors.put("nome", ErrorMessage.CAMPO_OBRIGATORIO);
        } else {
            //verificar se ja existe...
        }

        Double peso = (Double) fields.get("peso");
        if (peso == null) {
            errors.put("peso", ErrorMessage.CAMPO_OBRIGATORIO);
        } else {
            
        }

        return errors;
    }

    @Override
    public Map<String, String> validateForUpdate(Map<String, Object> fields) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
