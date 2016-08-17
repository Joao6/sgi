package gerenciador.incubadora.model.service;

import gerenciador.incubadora.model.ConnectionManager;
import gerenciador.incubadora.model.base.service.BaseAAIService;
import gerenciador.incubadora.model.dao.AmbienteInternoDAO;
import gerenciador.incubadora.model.entity.AmbienteInterno;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author William
 */
public class AmbienteInternoService implements BaseAAIService {

   @Override
   public void getCodedEntity(AmbienteInterno ambienteInterno) throws Exception {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public void create(AmbienteInterno e) throws Exception {
      Connection conn = ConnectionManager.getInstance().getConnection();
      try {
         AmbienteInternoDAO dao = new AmbienteInternoDAO();
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
   public AmbienteInterno readById(Long id) throws Exception {
      Connection conn = ConnectionManager.getInstance().getConnection();
      AmbienteInternoDAO dao = new AmbienteInternoDAO();
      AmbienteInterno aai = dao.readById(id, conn);
      conn.close();
      return aai;
   }

   @Override
   public List<AmbienteInterno> readByCriteria(Map<String, Object> criteria) throws Exception {
      Connection conn = ConnectionManager.getInstance().getConnection();
      AmbienteInternoDAO dao = new AmbienteInternoDAO();
      List<AmbienteInterno> internoList = dao.readByCriteria(criteria, conn);
      conn.close();
      return internoList;

   }

   @Override
   public void update(AmbienteInterno e) throws Exception {
      Connection conn = ConnectionManager.getInstance().getConnection();
      try {
         AmbienteInternoDAO dao = new AmbienteInternoDAO();
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
         AmbienteInternoDAO dao = new AmbienteInternoDAO();
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
