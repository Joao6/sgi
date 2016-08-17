package gerenciador.incubadora.model.dao;

import gerenciador.incubadora.model.base.BaseDAO;
import gerenciador.incubadora.model.entity.Categoria;
import gerenciador.incubadora.model.entity.Orientacao;
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
public class OrientacaoDAO implements BaseDAO<Orientacao> {

   public static final String CRITERION_CATEGORIA_ID = "0";

   @Override
   public void create(Orientacao e, Connection conn) throws Exception {
      String sql = "INSERT INTO orientacao(descricao, categoria_fk) VALUES (?,?) RETURNING id;";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setString(1, e.getDescricao());
      ps.setLong(2, e.getCategoria().getId());
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
         e.setId(rs.getLong("id"));
      }
      rs.close();
      ps.close();

   }

   @Override
   public Orientacao readById(Long id, Connection conn) throws Exception {
      String sql = "select o.id, o.descricao, ca.id as ca_id, ca.nome from orientacao o"
              + " left join categoria ca on ca.id=o.categoria_fk where o.id=?";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setLong(1, id);
      ResultSet rs = ps.executeQuery();

      Orientacao orientacao = new Orientacao();
      if (rs.next()) {
         orientacao.setId(rs.getLong("id"));
         orientacao.setDescricao(rs.getString("descricao"));

         Categoria categoria = new Categoria();
         categoria.setId(rs.getLong("ca_id"));
         categoria.setNome(rs.getString("nome"));
         orientacao.setCategoria(categoria);
      }
      rs.close();
      ps.close();
      return orientacao;
   }

   @Override
   public List<Orientacao> readByCriteria(Map<String, Object> criteria, Connection conn) throws Exception {
      String sql = "select o.id, o.descricao, ca.id as ca_id, ca.nome from orientacao o "
              + " left join categoria ca on ca.id=o.categoria_fk where 1=1 ";

      Long criterionCategoriaId = (Long) criteria.get(CRITERION_CATEGORIA_ID);
      if (criterionCategoriaId != null) {
         sql += " and ca.id=" + criterionCategoriaId;
      }

      PreparedStatement ps = conn.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();

      List<Orientacao> orientacaoList = new ArrayList<>();
      while (rs.next()) {
         Orientacao orientacao = new Orientacao();

         orientacao.setId(rs.getLong("id"));
         orientacao.setDescricao(rs.getString("descricao"));

         Categoria categoria = new Categoria();
         categoria.setId(rs.getLong("ca_id"));
         categoria.setNome(rs.getString("nome"));
         orientacao.setCategoria(categoria);

         orientacaoList.add(orientacao);
      }
      rs.close();
      ps.close();

      return orientacaoList;
   }

   @Override
   public void update(Orientacao e, Connection conn) throws Exception {
      String sql = "UPDATE orientacao SET descricao=? WHERE id=?;";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setString(1, e.getDescricao());
      ps.setLong(2, e.getId());
      ps.execute();
      ps.close();
   }

   @Override
   public void delete(Long id, Connection conn) throws Exception {
      String sql = "DELETE FROM orientacao WHERE id=?;";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setLong(1, id);
      ps.execute();
      ps.close();
   }

}
