package gerenciador.incubadora.model.service;

import gerenciador.incubadora.model.ConnectionManager;
import gerenciador.incubadora.model.base.service.BasePlanejamentoService;
import gerenciador.incubadora.model.dao.AcaoDAO;
import gerenciador.incubadora.model.entity.Acao;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author William
 */
public class AcaoService implements BasePlanejamentoService {

   private static final String ENCODE_UTF_8 = "UTF-8";
   private static final String ENCODE_ISO_8859_1 = "ISO-8859-1";

   @Override
   public void create(Acao e) throws Exception {
      Connection conn = ConnectionManager.getInstance().getConnection();
      try {
         AcaoDAO dao = new AcaoDAO();
         this.getCodedEntity(e);
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
   public Acao readById(Long id) throws Exception {
      Connection conn = ConnectionManager.getInstance().getConnection();
      AcaoDAO dao = new AcaoDAO();
      Acao acao = dao.readById(id, conn);
      conn.close();
      return acao;
   }

   @Override
   public List<Acao> readByCriteria(Map<String, Object> criteria) throws Exception {
      Connection conn = ConnectionManager.getInstance().getConnection();
      AcaoDAO dao = new AcaoDAO();
      List<Acao> acaoList = dao.readByCriteria(criteria, conn);
      conn.close();
      return acaoList;
   }

   @Override
   public void update(Acao e) throws Exception {
      Connection conn = ConnectionManager.getInstance().getConnection();
      try {
         AcaoDAO dao = new AcaoDAO();
         this.getCodedEntity(e);
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
         AcaoDAO dao = new AcaoDAO();
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
   public void getCodedEntity(Acao Acao) throws Exception {
      String acao = new String(Acao.getNome().getBytes(ENCODE_ISO_8859_1),ENCODE_UTF_8);
      Acao.setNome(acao);
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
