package gerenciador.incubadora.model.service;

import gerenciador.incubadora.model.ConnectionManager;
import gerenciador.incubadora.model.base.service.BaseRamoAtividadeService;
import gerenciador.incubadora.model.dao.RamoAtividadeDAO;
import gerenciador.incubadora.model.entity.RamoAtividade;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class RamoAtividadeService implements BaseRamoAtividadeService {

  @Override
  public void create(RamoAtividade e) throws Exception {
    Connection conn = ConnectionManager.getInstance().getConnection();
    try {
      RamoAtividadeDAO dao = new RamoAtividadeDAO();
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
  public RamoAtividade readById(Long id) throws Exception {
    RamoAtividade ramoAtividade = null;
    Connection conn = ConnectionManager.getInstance().getConnection();
    RamoAtividadeDAO dao = new RamoAtividadeDAO();
    ramoAtividade = dao.readById(id, conn);
    conn.close();
    return ramoAtividade;
  }

  @Override
  public List<RamoAtividade> readByCriteria(Map<String, Object> criteria) throws Exception {
    List<RamoAtividade> ramoAtividadeList = null;
    Connection conn = ConnectionManager.getInstance().getConnection();
    RamoAtividadeDAO dao = new RamoAtividadeDAO();
    ramoAtividadeList = dao.readByCriteria(criteria, conn);
    conn.close();
    return ramoAtividadeList;
  }

  @Override
  public void update(RamoAtividade e) throws Exception {
    Connection conn = ConnectionManager.getInstance().getConnection();
    try {
      RamoAtividadeDAO dao = new RamoAtividadeDAO();
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
      RamoAtividadeDAO dao = new RamoAtividadeDAO();
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
  public void  getCodedEntity(RamoAtividade ramoAtividade) throws Exception {
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

}
