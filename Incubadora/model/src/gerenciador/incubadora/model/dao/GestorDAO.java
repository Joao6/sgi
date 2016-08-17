package gerenciador.incubadora.model.dao;

import gerenciador.incubadora.model.base.BaseDAO;
import gerenciador.incubadora.model.entity.Gestor;
import gerenciador.incubadora.model.entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GestorDAO implements BaseDAO<Gestor> {

   public static final String CRITERION_MENAGER_NAMES_ILIKE = "1";
   public static final String CRITERION_EMAIL_ILIKE = "2";
   public static final String CRITERION_CPF_EQ = "3";

   @Override
   public void create(Gestor e, Connection conn) throws Exception {

      //CREATE INCUBADORA
      String sqlIncubadora = "INSERT INTO gestor(usuario_fk,cpf) VALUES (?,?)";
      PreparedStatement psIncubadora = conn.prepareStatement(sqlIncubadora);
      psIncubadora.setLong(1, e.getId());
      psIncubadora.setString(2, e.getCpf());
      psIncubadora.execute();
      psIncubadora.close();

   }

   @Override
   public Gestor readById(Long id, Connection conn) throws Exception {
      String sqlUsuarioIncubadora = "SELECT g.*, u.id as u_id, u.email, u.senha, u.telefone, u.nome, u.sobrenome FROM gestor g \n"
              + "               LEFT JOIN usuario u ON g.usuario_fk=u.id  WHERE g.usuario_fk=? ";
      PreparedStatement psGestor = conn.prepareStatement(sqlUsuarioIncubadora);
      psGestor.setLong(1, id);
      ResultSet rsGestor = psGestor.executeQuery();
      Gestor gestor = null;
      while (rsGestor.next()) {
         gestor = new Gestor();
         gestor.setId(rsGestor.getLong("u_id"));
         gestor.setNome(rsGestor.getString("nome"));
         gestor.setSobrenome(rsGestor.getString("sobrenome"));
         gestor.setCpf(rsGestor.getString("cpf"));
         gestor.setEmail(rsGestor.getString("email"));
         gestor.setSenha(rsGestor.getString("senha"));
         gestor.setTelefone(rsGestor.getString("telefone"));
      }
      rsGestor.close();
      psGestor.close();
      return gestor;
   }

   @Override
   public List<Gestor> readByCriteria(Map<String, Object> criteria, Connection conn) throws Exception {
      String sqlGestor = "SELECT g.*, u.id as u_id, u.email, u.senha, u.telefone, u.nome, u.sobrenome FROM gestor g "
              + "  INNER JOIN usuario u ON g.usuario_fk=u.id  WHERE 1=1 ";

      String criterionNomeIlike = (String) criteria.get(CRITERION_MENAGER_NAMES_ILIKE);
      if (criterionNomeIlike != null && !criterionNomeIlike.isEmpty()) {
         sqlGestor += "AND u.nome ILIKE '" + criterionNomeIlike + "'";
      }

      String criterionEmailIlike = (String) criteria.get(CRITERION_EMAIL_ILIKE);
      if (criterionEmailIlike != null && !criterionEmailIlike.isEmpty()) {
         sqlGestor += " AND u.email ILIKE '" + criterionEmailIlike + "'";
      }

      String criterionCPFeq = (String) criteria.get(CRITERION_CPF_EQ);
      if (criterionCPFeq != null && !criterionCPFeq.isEmpty()) {
         sqlGestor += " AND g.cpf='" + criterionCPFeq + "'";
      }

      PreparedStatement psGestor = conn.prepareStatement(sqlGestor);
      ResultSet rsGestor = psGestor.executeQuery();
      List<Gestor> gestorList = new ArrayList<>();

      while (rsGestor.next()) {
         Gestor gestor = new Gestor();
         gestor.setId(rsGestor.getLong("u_id"));
         gestor.setNome(rsGestor.getString("nome"));
         gestor.setSobrenome(rsGestor.getString("sobrenome"));
         gestor.setEmail(rsGestor.getString("email"));
         gestor.setSenha(rsGestor.getString("senha"));
         gestor.setTelefone(rsGestor.getString("telefone"));
         gestor.setCpf(rsGestor.getString("cpf"));

         gestorList.add(gestor);
      }
      rsGestor.close();
      psGestor.close();
      return gestorList;
   }

   @Override
   public void update(Gestor e, Connection conn) throws Exception {
      String sqlUsuario = "UPDATE usuario SET nome=?,email=?,senha=?,telefone=?,tipo_usuario=?, sobrenome=? WHERE id=?";
      PreparedStatement psUsuario = conn.prepareStatement(sqlUsuario);
      int i = 0;
      psUsuario.setString(++i, e.getNome());
      psUsuario.setString(++i, e.getEmail());
      psUsuario.setString(++i, e.getSenha());
      psUsuario.setString(++i, e.getTelefone());
      psUsuario.setString(++i, Usuario.TIPO_USUARIO_INCUBADORA);
      psUsuario.setString(++i, e.getSobrenome());
      psUsuario.setLong(++i, e.getId());
      psUsuario.execute();
      psUsuario.close();

      String sqlIncubadora = "UPDATE gestor SET cpf=? WHERE usuario_fk=?";
      PreparedStatement psIncubadora = conn.prepareStatement(sqlIncubadora);
      int k = 0;
      psIncubadora.setString(++k, e.getCpf());
      psIncubadora.setLong(++k, e.getId());
      psIncubadora.execute();
      psIncubadora.close();
   }

   @Override
   public void delete(Long id, Connection conn) throws Exception {
      String sql = "DELETE FROM incubadora WHERE usuario_fk=?";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setLong(1, id);
      ps.execute();
      ps.close();
   }

}
