package gerenciador.incubadora.model.dao;

import gerenciador.incubadora.model.base.BaseDAO;
import gerenciador.incubadora.model.entity.Avaliador;
import gerenciador.incubadora.model.entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Rafael-pc
 */
public class AvaliadorDAO implements BaseDAO<Avaliador> {

   public static final String CRITERION_NOME_ILIKE = "1";
   public static final String CRITERION_CPF_EQ = "2";
   public static final String CRITERION_EMAIL_ILIKE = "3";
   public static final String CRITERION_ID_IN = "4";
           
   @Override
   public void create(Avaliador e, Connection conn) throws Exception {
      String sql = "INSERT INTO usuario(nome, email, senha, telefone, tipo_usuario, sobrenome) VALUES (?, ?, ?, ?, ?, ?) RETURNING id;";
      PreparedStatement ps = conn.prepareStatement(sql);
      int i = 0;
      ps.setString(++i, e.getNome());
      ps.setString(++i, e.getEmail());
      ps.setString(++i, e.getSenha());
      ps.setString(++i, e.getTelefone());
      ps.setString(++i, Usuario.TIPO_USUARIO_AVALIADOR);
      ps.setString(++i, e.getSobrenome());

      ResultSet rs = ps.executeQuery();

      if (rs.next()) {
         e.setId(rs.getLong("id"));
      }
      rs.close();
      ps.close();

      String sqlAvaliador = "INSERT INTO avaliador(usuario_fk, cpf) VALUES (?, ?);";
      PreparedStatement psAvaliador = conn.prepareStatement(sqlAvaliador);
      i = 0;
      psAvaliador.setLong(++i, e.getId());
      psAvaliador.setString(++i, e.getCpf());
      psAvaliador.execute();
      psAvaliador.close();
   }

   @Override
   public Avaliador readById(Long id, Connection conn) throws Exception {
      String sql = "select * from usuario u left join avaliador a on a.usuario_fk = u.id WHERE u.id = ?;";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setLong(1, id);
      ResultSet rs = ps.executeQuery();
      Avaliador avaliador = null;
      if (rs.next()) {
         avaliador = new Avaliador();
         avaliador.setId(id);
         avaliador.setNome(rs.getString("nome"));
         avaliador.setEmail(rs.getString("email"));
         avaliador.setSenha(rs.getString("senha"));
         avaliador.setTelefone(rs.getString("telefone"));
         avaliador.setTipoUsuario(rs.getString("tipo_usuario"));
         avaliador.setSobrenome(rs.getString("sobrenome"));
         avaliador.setCpf(rs.getString("cpf"));
      }
      rs.close();
      ps.close();
      return avaliador;
   }

   @Override
   public List<Avaliador> readByCriteria(Map<String, Object> criteria, Connection conn) throws Exception {
      List<Avaliador> avaliadorList = new ArrayList<>();
      String sql = " select * from usuario u join avaliador a on a.usuario_fk = u.id WHERE 1=1";
      Statement s = conn.createStatement();

      if (criteria != null) {
         String criterionCpfEq = (String) criteria.get(CRITERION_CPF_EQ);
         if (criterionCpfEq != null && !criterionCpfEq.isEmpty()) {
            sql += " AND a.cpf='" + criterionCpfEq + "'";
         }

         String criterionEmailIlike = (String) criteria.get(CRITERION_EMAIL_ILIKE);
         if (criterionEmailIlike != null && !criterionEmailIlike.isEmpty()) {
            sql += " AND u.email='" + criterionEmailIlike + "'";
         }                  
      }

      ResultSet rs = s.executeQuery(sql);
      Avaliador avaliador;
      while (rs.next()) {
         avaliador = new Avaliador();
         avaliador.setId(rs.getLong("id"));
         avaliador.setNome(rs.getString("nome"));
         avaliador.setEmail(rs.getString("email"));
         avaliador.setSenha(rs.getString("senha"));
         avaliador.setTelefone(rs.getString("telefone"));
         avaliador.setTipoUsuario(rs.getString("tipo_usuario"));
         avaliador.setSobrenome(rs.getString("sobrenome"));
         avaliador.setCpf(rs.getString("cpf"));
         avaliadorList.add(avaliador);
      }
      rs.close();
      s.close();
      return avaliadorList;
   }

   @Override
   public void update(Avaliador e, Connection conn) throws Exception {
      String sql = "UPDATE usuario SET nome=?, email=?, senha=?, telefone=?, tipo_usuario=?, sobrenome=? WHERE id=?;";
      PreparedStatement ps = conn.prepareStatement(sql);
      int i = 0;
      ps.setString(++i, e.getNome());
      ps.setString(++i, e.getEmail());
      ps.setString(++i, e.getSenha());
      ps.setString(++i, e.getTelefone());
      ps.setString(++i, e.getTipoUsuario());
      ps.setString(++i, e.getSobrenome());
      ps.setLong(++i, e.getId());
      ps.execute();
      ps.close();

      String sqlAvaliador = "UPDATE avaliador SET cpf=? WHERE usuario_fk=?;";
      PreparedStatement psAvaliador = conn.prepareStatement(sqlAvaliador);
      i = 0;
      psAvaliador.setString(++i, e.getCpf());
      psAvaliador.setLong(++i, e.getId());
      psAvaliador.execute();
      psAvaliador.close();

   }

   @Override
   public void delete(Long id, Connection conn) throws Exception {
      String sql = "DELETE FROM usuario WHERE id=?";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setLong(1, id);
      ps.execute();
      ps.close();
   }

   public void SetGestorHowAvaliador(Avaliador a, Connection conn) throws Exception {
      String sql = "INSERT INTO avaliador(usuario_fk,cpf) VALUES(?,?); ";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setLong(1, a.getId());
      ps.setString(2, a.getCpf());
      ps.execute();
   }

}
