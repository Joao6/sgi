package gerenciador.incubadora.model.service;

import gerenciador.incubadora.model.ConnectionManager;
import gerenciador.incubadora.model.ErrorMessage;
import gerenciador.incubadora.model.base.service.BaseDuvidaService;
import gerenciador.incubadora.model.dao.DuvidaDAO;
import gerenciador.incubadora.model.entity.Duvida;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Rafael
 */
public class DuvidaService implements BaseDuvidaService {

    @Override
    public void create(Duvida e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            DuvidaDAO dao = new DuvidaDAO();
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
    public Duvida readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        DuvidaDAO dao = new DuvidaDAO();
        Duvida duvida = dao.readById(id, conn);
        conn.close();
        return duvida;
    }

    @Override
    public List<Duvida> readByCriteria(Map<String, Object> criteria) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        DuvidaDAO dao = new DuvidaDAO();
        List<Duvida> duvidaList = dao.readByCriteria(criteria, conn);

        DateFormat df = DateFormat.getDateInstance();

        for (Duvida duvida : duvidaList) {
            Date dataHora = duvida.getDataHora();
            String dateTime = df.format(dataHora);
            try {
                duvida.setDataHora(DateService.parseDate(dateTime, "dd/MM/yyyy hh:mm:ss"));
            } catch (Exception ex) {
                System.out.println(ex);
                duvida.setDataHora(dataHora);
            }
        }

        conn.close();
        return duvidaList;
    }

    @Override
    public void update(Duvida e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            DuvidaDAO dao = new DuvidaDAO();
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
            DuvidaDAO dao = new DuvidaDAO();
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

        String pergunta = (String) fields.get("pergunta");
        if (pergunta == null || pergunta.isEmpty()) {
            errors.put("pergunta", ErrorMessage.CAMPO_OBRIGATORIO);
        }

        return errors;
    }

    @Override
    public Map<String, String> validateForUpdate(Map<String, Object> fields) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
