package gerenciador.incubadora.model.dao;

import gerenciador.incubadora.model.base.BaseDAO;
import gerenciador.incubadora.model.entity.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author William
 */
public class CategoriaDAO implements BaseDAO<Categoria> {

   @Override
   public void create(Categoria e, Connection conn) throws Exception {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public Categoria readById(Long id, Connection conn) throws Exception {
      String sql = "SELECT * FROM categoria WHERE id=?;";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setLong(1, id);
      ResultSet rs = ps.executeQuery();

      Categoria categoria = new Categoria();
      if (rs.next()) {
         categoria.setId(rs.getLong("id"));
         categoria.setNome(rs.getString("nome"));
      }
      ps.close();
      rs.close();
      return categoria;

   }

   @Override
   public List<Categoria> readByCriteria(Map<String, Object> criteria, Connection conn) throws Exception {
      String sql = "select ca.* from categoria ca where 1=1 ";
      PreparedStatement ps = conn.prepareStatement(sql);

      ResultSet rs = ps.executeQuery();

      List<Categoria> categorias = new ArrayList<>();
      while (rs.next()) {
         Categoria categoria = new Categoria();
         categoria.setId(rs.getLong("id"));
         categoria.setNome(rs.getString("nome"));
         categorias.add(categoria);
      }
      ps.close();
      rs.close();
      return categorias;
   }

   @Override
   public void update(Categoria e, Connection conn) throws Exception {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public void delete(Long id, Connection conn) throws Exception {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

}
