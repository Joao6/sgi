
package gerenciador.incubadora.model.service;

import gerenciador.incubadora.model.ConnectionManager;
import gerenciador.incubadora.model.base.service.BaseCronogramaAnualService;
import gerenciador.incubadora.model.dao.CronogramaAnualDAO;
import gerenciador.incubadora.model.entity.CronogramaAnual;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author William
 */
public class CronogramaAnualService implements BaseCronogramaAnualService {

  @Override
  public void create(CronogramaAnual e) throws Exception {
    Connection conn = ConnectionManager.getInstance().getConnection();
    try {
      CronogramaAnualDAO dao = new CronogramaAnualDAO();
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
  public CronogramaAnual readById(Long id) throws Exception {
    Connection conn = ConnectionManager.getInstance().getConnection();
    CronogramaAnualDAO dao = new CronogramaAnualDAO();
    CronogramaAnual cronograma = dao.readById(id, conn);
    conn.close();
    return cronograma;
  }

  @Override
  public List<CronogramaAnual> readByCriteria(Map<String, Object> criteria) throws Exception {
    Connection conn = ConnectionManager.getInstance().getConnection();
    CronogramaAnualDAO dao = new CronogramaAnualDAO();
    List<CronogramaAnual> cronogramaList = dao.readByCriteria(criteria, conn);
    conn.close();
    return cronogramaList;
  }

  @Override
  public void update(CronogramaAnual e) throws Exception {
    Connection conn = ConnectionManager.getInstance().getConnection();
    try {
      CronogramaAnualDAO dao = new CronogramaAnualDAO();
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
      CronogramaAnualDAO dao = new CronogramaAnualDAO();
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
