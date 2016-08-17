package gerenciador.incubadora.model.dao;

import gerenciador.incubadora.model.base.BaseDAO;
import gerenciador.incubadora.model.entity.AmbienteExterno;
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
public class AmbienteExternoDAO implements BaseDAO<AmbienteExterno> {

   @Override
   public void create(AmbienteExterno e, Connection conn) throws Exception {
      String sql = "INSERT INTO ambiente_externo(oportunidade, ameaca, empreendimento_fk)VALUES (?, ?, ?) RETURNING id;";
      PreparedStatement ps = conn.prepareStatement(sql);

      ps.setString(1, e.getOportunidades());
      ps.setString(2, e.getAmeacas());
      ps.setLong(3, e.getEmpreendimento().getId());
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
         e.setId(rs.getLong("id"));
      }
      rs.close();
      ps.close();

   }

   @Override
   public AmbienteExterno readById(Long id, Connection conn) throws Exception {
      String sql = "SELECT * FROM ambiente_externo WHERE id=?";

      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setLong(1, id);
      ResultSet rs = ps.executeQuery();

      AmbienteExterno aae = new AmbienteExterno();
      if (rs.next()) {
         aae.setId(rs.getLong("id"));
         aae.setAmeacas(rs.getString("ameaca"));
         aae.setOportunidades(rs.getString("oportunidade"));
      }
      rs.close();
      ps.close();
      return aae;
   }

   @Override
   public List<AmbienteExterno> readByCriteria(Map<String, Object> criteria, Connection conn) throws Exception {
      String sql = "SELECT * FROM ambiente_externo WHERE 1=1 ";
      List<AmbienteExterno> aaeList = new ArrayList<>();

      PreparedStatement ps = conn.prepareStatement(sql);

      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
         AmbienteExterno aae = new AmbienteExterno();
         aae.setId(rs.getLong("id"));
         aae.setAmeacas(rs.getString("ameaca"));
         aae.setOportunidades(rs.getString("oportunidade"));

         aaeList.add(aae);
      }
      rs.close();
      ps.close();
      return aaeList;
   }

   @Override
   public void update(AmbienteExterno e, Connection conn) throws Exception {
      String sql = "UPDATE ambiente_externo SET oportunidade=?, ameaca=? WHERE id=?";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setString(1, e.getOportunidades());
      ps.setString(2, e.getAmeacas());
      ps.setLong(3, e.getId());
      ps.execute();
      ps.close();
   }

   @Override
   public void delete(Long id, Connection conn) throws Exception {
      String sql = "DELETE FROM ambiente_externo WHERE id=?";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setLong(1, id);
      ps.execute();
      ps.close();
   }

}
