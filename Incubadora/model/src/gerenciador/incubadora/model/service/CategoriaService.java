package gerenciador.incubadora.model.service;

import gerenciador.incubadora.model.ConnectionManager;
import gerenciador.incubadora.model.base.BaseService;
import gerenciador.incubadora.model.dao.CategoriaDAO;
import gerenciador.incubadora.model.entity.Categoria;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author William
 */
public class CategoriaService implements BaseService<Categoria> {

   @Override
   public void create(Categoria e) throws Exception {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public Categoria readById(Long id) throws Exception {
      Connection conn = ConnectionManager.getInstance().getConnection();
      CategoriaDAO dao = new CategoriaDAO();

      Categoria categoria = dao.readById(id, conn);
      conn.close();
      return categoria;
   }

   @Override
   public List<Categoria> readByCriteria(Map<String, Object> criteria) throws Exception {
      Connection conn = ConnectionManager.getInstance().getConnection();
      CategoriaDAO dao = new CategoriaDAO();
      List<Categoria> categorias = dao.readByCriteria(criteria, conn);
      conn.close();
      return categorias;
   }

   @Override
   public void update(Categoria e) throws Exception {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public void delete(Long id) throws Exception {
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
