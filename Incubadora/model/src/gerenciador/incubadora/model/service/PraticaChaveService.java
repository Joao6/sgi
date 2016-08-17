/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.incubadora.model.service;

import gerenciador.incubadora.model.ConnectionManager;
import gerenciador.incubadora.model.base.service.BasePraticaChaveService;
import gerenciador.incubadora.model.dao.PraticaChaveDAO;
import gerenciador.incubadora.model.entity.PraticaChave;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author William
 */
public class PraticaChaveService implements BasePraticaChaveService {

   @Override
   public void create(PraticaChave e) throws Exception {
      Connection conn = ConnectionManager.getInstance().getConnection();
      try {
         PraticaChaveDAO dao = new PraticaChaveDAO();
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
   public PraticaChave readById(Long id) throws Exception {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PraticaChaveDAO dao = new PraticaChaveDAO();
      PraticaChave praticaChave = dao.readById(id, conn);

      Date dataDefault = Date.valueOf("0001-01-01");
      if (praticaChave != null) {
         if (praticaChave.getDataCertificacao().equals(dataDefault)) {
            praticaChave.setDataCertificacao(null);
         }
      }

      conn.close();
      return praticaChave;
   }

   @Override
   public List<PraticaChave> readByCriteria(Map<String, Object> criteria) throws Exception {
      Connection conn = ConnectionManager.getInstance().getConnection();
      PraticaChaveDAO dao = new PraticaChaveDAO();
      List<PraticaChave> praticaList = dao.readByCriteria(criteria, conn);

      Date dataDefault = Date.valueOf("0001-01-01");
      for (PraticaChave pratica : praticaList) {
         if (pratica.getDataCertificacao().equals(dataDefault)) {
            pratica.setDataCertificacao(null);
         }
      }
      conn.close();
      return praticaList;
   }

   @Override
   public void update(PraticaChave e) throws Exception {
      Connection conn = ConnectionManager.getInstance().getConnection();
      try {
         PraticaChaveDAO dao = new PraticaChaveDAO();
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
         PraticaChaveDAO dao = new PraticaChaveDAO();
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
   public void getCodedEntity(PraticaChave pc) throws Exception {


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
