package gerenciador.incubadora.model.service;

import gerenciador.incubadora.model.ConnectionManager;
import gerenciador.incubadora.model.base.service.BaseCronogramaMenagerService;
import gerenciador.incubadora.model.dao.CronogramaManagerDAO;
import gerenciador.incubadora.model.entity.CronogramaAnual;
import gerenciador.incubadora.model.entity.CronogramaManager;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author William
 */
public class CronogramaManagerService implements BaseCronogramaMenagerService {

  @Override
  public void create(CronogramaManager e) throws Exception {
    Connection conn = ConnectionManager.getInstance().getConnection();
    try {

      CronogramaManagerDAO dao = new CronogramaManagerDAO();   
    
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
  public CronogramaManager readById(Long id) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public List<CronogramaManager> readByCriteria(Map<String, Object> criteria) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void update(CronogramaManager e) throws Exception {

  }

  @Override
  public void delete(Long id) throws Exception {
    Connection conn = ConnectionManager.getInstance().getConnection();
    CronogramaManagerDAO dao = new CronogramaManagerDAO();
    dao.delete(id, conn);
    conn.commit();
    conn.close();
  }

  @Override
  public boolean deleteGestor(Long idCronograma, Long idGestor) throws Exception {
    boolean success = false;
    Connection conn = ConnectionManager.getInstance().getConnection();
    CronogramaManagerDAO dao = new CronogramaManagerDAO();
    success = dao.deleteGestor(conn, idCronograma, idGestor);
    conn.commit();
    conn.close();
    return success;
  }

  @Override
  public boolean deletePraticaChave(CronogramaAnual cronogramaAnual) throws Exception {
    boolean success = false;
    Connection conn = ConnectionManager.getInstance().getConnection();
    try {
      CronogramaManagerDAO dao = new CronogramaManagerDAO();
      dao.deletePraticaChave(conn, cronogramaAnual);
      conn.commit();
      conn.close();
      success = true;
    } catch (Exception e) {
      conn.rollback();
      conn.close();
      throw e;
    }
    return success;
  }

  @Override
  public boolean insertGestor(CronogramaAnual ca) throws Exception {
     boolean success = false;
    Connection conn = ConnectionManager.getInstance().getConnection();
    CronogramaManagerDAO dao = new CronogramaManagerDAO();
    success = dao.insertGestor(conn, ca);
    conn.commit();
    conn.close();
    return success;
  }

  @Override
  public boolean insertPraticaChave(CronogramaAnual ca) throws Exception {
    boolean success = false;
    Connection conn = ConnectionManager.getInstance().getConnection();
    CronogramaManagerDAO dao = new CronogramaManagerDAO();
    success = dao.insertPraticaChave(conn, ca);
    conn.commit();
    conn.close();
    return success;
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
