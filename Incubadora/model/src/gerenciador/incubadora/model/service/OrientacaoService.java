package gerenciador.incubadora.model.service;

import gerenciador.incubadora.model.ConnectionManager;
import gerenciador.incubadora.model.base.service.BaseOrientacaoService;
import gerenciador.incubadora.model.dao.OrientacaoDAO;
import gerenciador.incubadora.model.entity.Orientacao;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author William
 */
public class OrientacaoService implements BaseOrientacaoService {
   
   private static final String ENCODE_UTF_8 = "UTF-8";
   private static final String ENCODE_ISO_8859_1 = "ISO-8859-1";
   
   @Override
   public void getCodedEntity(Orientacao orientacao) throws Exception {
      String descricao = new String(orientacao.getDescricao().getBytes(ENCODE_ISO_8859_1), ENCODE_UTF_8);
      orientacao.setDescricao(descricao);
   }
   
   @Override
   public void create(Orientacao e) throws Exception {
      Connection conn = ConnectionManager.getInstance().getConnection();
      try {
         OrientacaoDAO dao = new OrientacaoDAO();
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
   public Orientacao readById(Long id) throws Exception {
      Connection conn = ConnectionManager.getInstance().getConnection();
      OrientacaoDAO dao = new OrientacaoDAO();
      Orientacao oritentacao = dao.readById(id, conn);
      conn.close();
      return oritentacao;
   }
   
   @Override
   public List<Orientacao> readByCriteria(Map<String, Object> criteria) throws Exception {
      Connection conn = ConnectionManager.getInstance().getConnection();
      OrientacaoDAO dao = new OrientacaoDAO();
      List<Orientacao> orientacaoList = dao.readByCriteria(criteria, conn);            
      conn.close();
      return orientacaoList;
   }
   
   @Override
   public void update(Orientacao e) throws Exception {
      Connection conn = ConnectionManager.getInstance().getConnection();
      try {
         OrientacaoDAO dao = new OrientacaoDAO();
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
         OrientacaoDAO dao = new OrientacaoDAO();
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
