/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.incubadora.model.service;

import gerenciador.incubadora.model.ConnectionManager;
import gerenciador.incubadora.model.base.service.BaseProcessoChaveService;
import gerenciador.incubadora.model.dao.ProcessoChaveDAO;
import gerenciador.incubadora.model.entity.ProcessoChave;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author William
 */
public class ProcessoChaveService implements BaseProcessoChaveService {

   private static final String ENCODE_UTF_8 = "UTF-8";
   private static final String ENCODE_ISO_8859_1 = "ISO-8859-1";

   @Override
   public void create(ProcessoChave e) throws Exception {
      Connection conn = ConnectionManager.getInstance().getConnection();
      try {
         ProcessoChaveDAO dao = new ProcessoChaveDAO();
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
   public ProcessoChave readById(Long id) throws Exception {
      Connection conn = ConnectionManager.getInstance().getConnection();
      ProcessoChaveDAO dao = new ProcessoChaveDAO();
      ProcessoChave processoChave = dao.readById(id, conn);
      conn.close();
      return processoChave;
   }

   @Override
   public List<ProcessoChave> readByCriteria(Map<String, Object> criteria) throws Exception {
      Connection conn = ConnectionManager.getInstance().getConnection();
      ProcessoChaveDAO dao = new ProcessoChaveDAO();
      List<ProcessoChave> processoChaveList = dao.readByCriteria(criteria, conn);
      conn.close();
      return processoChaveList;
   }

   @Override
   public void update(ProcessoChave e) throws Exception {
      Connection conn = ConnectionManager.getInstance().getConnection();
      try {
         ProcessoChaveDAO dao = new ProcessoChaveDAO();       
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
         ProcessoChaveDAO dao = new ProcessoChaveDAO();
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
   public void getCodedEntity(ProcessoChave processoChave) throws Exception {
      //ENCODE
      String nome = new String(processoChave.getNome().getBytes(ENCODE_ISO_8859_1), ENCODE_UTF_8);
      //UPDATE
      processoChave.setNome(nome);

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
