package gerenciador.incubadora.model.service;

import gerenciador.incubadora.model.ConnectionManager;
import gerenciador.incubadora.model.base.service.BaseAAEService;
import gerenciador.incubadora.model.dao.AmbienteExternoDAO;
import gerenciador.incubadora.model.entity.AmbienteExterno;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author William
 */
public class AmbienteExternoService implements BaseAAEService {

   @Override
   public void getCodedEntity(AmbienteExterno aae) throws Exception {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public void create(AmbienteExterno e) throws Exception {
      Connection conn = ConnectionManager.getInstance().getConnection();
      try {
         AmbienteExternoDAO dao = new AmbienteExternoDAO();
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
   public AmbienteExterno readById(Long id) throws Exception {
      Connection conn = ConnectionManager.getInstance().getConnection();
      AmbienteExternoDAO dao = new AmbienteExternoDAO();
      AmbienteExterno aae = dao.readById(id, conn);
      conn.close();
      return aae;
   }

   @Override
   public List<AmbienteExterno> readByCriteria(Map<String, Object> criteria) throws Exception {
      Connection conn = ConnectionManager.getInstance().getConnection();
      AmbienteExternoDAO dao = new AmbienteExternoDAO();
      List<AmbienteExterno> aaeList = dao.readByCriteria(criteria, conn);
      conn.close();
      return aaeList;
   }

   @Override
   public void update(AmbienteExterno e) throws Exception {
      Connection conn = ConnectionManager.getInstance().getConnection();
      try {
         AmbienteExternoDAO dao = new AmbienteExternoDAO();
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
         AmbienteExternoDAO dao = new AmbienteExternoDAO();
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
