package gerenciador.incubadora.model.service;

import gerenciador.incubadora.model.ConnectionManager;
import gerenciador.incubadora.model.base.BaseService;
import gerenciador.incubadora.model.dao.ComentRespDuvidaDAO;
import gerenciador.incubadora.model.entity.ComentarioRespostaDuvida;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author William
 */
public class ComentRespDuvidaService implements BaseService<ComentarioRespostaDuvida> {

   @Override
   public void create(ComentarioRespostaDuvida e) throws Exception {
      Connection conn = ConnectionManager.getInstance().getConnection();
      try {
         ComentRespDuvidaDAO dao = new ComentRespDuvidaDAO();
         dao.create(e, conn);
         conn.commit();
         conn.close();
      } catch (Exception ex) {
         conn.rollback();
         conn.close();
      }
   }

   @Override
   public ComentarioRespostaDuvida readById(Long id) throws Exception {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public List<ComentarioRespostaDuvida> readByCriteria(Map<String, Object> criteria) throws Exception {
      Connection conn = ConnectionManager.getInstance().getConnection();
      ComentRespDuvidaDAO dao = new ComentRespDuvidaDAO();
      List<ComentarioRespostaDuvida> comentRespDuvidaList = dao.readByCriteria(criteria, conn);
      conn.close();
      return comentRespDuvidaList;
   }

   @Override
   public void update(ComentarioRespostaDuvida e) throws Exception {
      Connection conn = ConnectionManager.getInstance().getConnection();
      try {
         ComentRespDuvidaDAO dao = new ComentRespDuvidaDAO();
         dao.update(e, conn);
         conn.commit();
         conn.close();
      } catch (Exception ex) {
         conn.rollback();
         conn.close();
      }
   }

   @Override
   public void delete(Long id) throws Exception {
      Connection conn = ConnectionManager.getInstance().getConnection();
      try {
         ComentRespDuvidaDAO dao = new ComentRespDuvidaDAO();
         dao.delete(id, conn);
         conn.commit();
         conn.close();
      } catch (Exception e) {
         conn.rollback();
         conn.close();
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
