package gerenciador.incubadora.model.service;

import gerenciador.incubadora.model.ConnectionManager;
import gerenciador.incubadora.model.ErrorMessage;
import gerenciador.incubadora.model.base.service.BaseEditalService;
import gerenciador.incubadora.model.dao.EditalDAO;
import gerenciador.incubadora.model.entity.Edital;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Rafael-pc
 */
public class EditalService implements BaseEditalService {

    @Override
    public void create(Edital e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            EditalDAO dao = new EditalDAO();
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
    public Edital readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        EditalDAO dao = new EditalDAO();
        Edital edital = dao.readById(id, conn);
        conn.close();
        return edital;
    }

    @Override
    public List<Edital> readByCriteria(Map<String, Object> criteria) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        EditalDAO dao = new EditalDAO();
        List<Edital> editalList = dao.readByCriteria(criteria, conn);
        conn.close();
        return editalList;
    }

    @Override
    public void update(Edital e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            EditalDAO dao = new EditalDAO();
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
            EditalDAO dao = new EditalDAO();
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

        String nome = (String) fields.get("nome");
        if (nome == null || nome.isEmpty()) {
            errors.put("nome", "Campo Obrigatorio!");
        }
        String resumo = (String) fields.get("resumo");
        if (resumo == null || resumo.isEmpty()) {
            errors.put("resumo", "Campo Obrigatorio!");
        }
        String dataInicio = (String) fields.get("dataInicio");
        if (dataInicio == null || dataInicio.isEmpty()) {
            errors.put("dataInicio", "Campo Obrigatorio!");
        }else{
            try {
                DateService.parseDate(dataInicio, "dd/MM/yyyy");
            } catch (Exception e) {
                errors.put("dataInicio", ErrorMessage.DATA_INVALIDA);
            }
        }
        
        String dataFim = (String) fields.get("dataFim");
        if (dataFim == null || dataFim.isEmpty()) {
            errors.put("dataFim", "Campo Obrigatorio!");
        }else{
            try {
                DateService.parseDate(dataFim, "dd/MM/yyyy");
            } catch (Exception e) {
                errors.put("dataFim", ErrorMessage.DATA_INVALIDA);
            }
        }

        return errors;
    }

    @Override
    public void updateDataProrrogacao(Edital e) throws Exception {
        Connection connection = ConnectionManager.getInstance().getConnection();
        try {
            EditalDAO dao = new EditalDAO();
            dao.updateDataProrrogacao(e, connection);
            connection.commit();
            connection.close();
        } catch (Exception ex) {
            connection.rollback();
            connection.close();
            throw ex;
        }
    }

    @Override
    public Map<String, String> validateForUpdate(Map<String, Object> fields) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
