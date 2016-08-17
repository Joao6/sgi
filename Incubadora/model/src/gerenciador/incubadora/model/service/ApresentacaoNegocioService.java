package gerenciador.incubadora.model.service;

import gerenciador.incubadora.model.ConnectionManager;
import gerenciador.incubadora.model.base.service.BaseApresentacaoNegocioService;
import gerenciador.incubadora.model.dao.ApresentacaoNegocioDAO;
import gerenciador.incubadora.model.entity.ApresentacaoNegocio;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Rafael
 */
public class ApresentacaoNegocioService implements BaseApresentacaoNegocioService {

    @Override
    public void create(ApresentacaoNegocio e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            ApresentacaoNegocioDAO dao = new ApresentacaoNegocioDAO();
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
    public ApresentacaoNegocio readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        ApresentacaoNegocioDAO dao = new ApresentacaoNegocioDAO();
        ApresentacaoNegocio apresentacaoNegocio = dao.readById(id, conn);
        conn.close();
        return apresentacaoNegocio;
    }

    @Override
    public List<ApresentacaoNegocio> readByCriteria(Map<String, Object> criteria) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        ApresentacaoNegocioDAO dao = new ApresentacaoNegocioDAO();
        List<ApresentacaoNegocio> apresentacaoNegocioList = dao.readByCriteria(criteria, conn);
        conn.close();
        return apresentacaoNegocioList;
    }

    @Override
    public void update(ApresentacaoNegocio e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            ApresentacaoNegocioDAO dao = new ApresentacaoNegocioDAO();
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
            ApresentacaoNegocioDAO dao = new ApresentacaoNegocioDAO();
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, String> validateForUpdate(Map<String, Object> fields) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
