package gerenciador.incubadora.model.dao;

import gerenciador.incubadora.model.base.BaseDAO;
import gerenciador.incubadora.model.entity.Duvida;
import gerenciador.incubadora.model.entity.Empreendedor;
import gerenciador.incubadora.model.entity.RespostaDuvida;
import gerenciador.incubadora.model.entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Rafael
 */
public class RespostaDuvidaDAO implements BaseDAO<RespostaDuvida> {

   public static final String CRITERION_DATA_HORA_ORDER_BY_ASC = "0";

   @Override
   public void create(RespostaDuvida e, Connection conn) throws Exception {
      String sql = "INSERT INTO resposta_duvida(resposta, data_hora, duvida_fk, usuario_fk) VALUES (?, ?, ?, ?) RETURNING id;";
      PreparedStatement ps = conn.prepareStatement(sql);
      int i = 0;
      ps.setString(++i, e.getResposta());
      ps.setTimestamp(++i, new Timestamp(e.getDataHora().getTime()));
      ps.setLong(++i, e.getDuvida().getId());
      ps.setLong(++i, e.getUsuario().getId());

      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
         e.setId(rs.getLong("id"));
      }
      rs.close();
      ps.close();
   }

   @Override
   public RespostaDuvida readById(Long id, Connection conn) throws Exception {
      String sql = "SELECT rd.*, d.duvida as duvida_duvida, d.data_hora as duvida_data_hora, ur.id as usuario_resposta_id, ur.nome as usuario_resposta_nome, ur.email as usuario_resposta_email, "
              + " ur.senha as usuario_resposta_senha, ur.telefone as usuario_resposta_telefone, ur.tipo_usuario as usuario_resposta_tipo_usuario, ur.sobrenome as usuario_resposta_sobrenome, "
              + " e.usuario_fk as empreendedor_id, e.cpf as empreendedor_cpf, e.rg as empreendedor_rg, e.data_nascimento as empreendedor_data_nascimento, e.escolaridade as empreendedor_escolaridade, "
              + " e.formacao_profissional as empreendedor_formacao_profissional, e.ocupacao as empreendedor_ocupacao, e.fax as empreendedor_fax, u.nome as empreendedor_nome, u.email as empreendedor_email, u.senha as empreendedor_senha, u.telefone as empreendedor_telefone, "
              + " u.tipo_usuario as empreendedor_tipo_usuario, u.sobrenome as empreendedor_sobrenome FROM resposta_duvida rd LEFT JOIN duvida d ON d.id = rd.duvida_fk "
              + " LEFT JOIN usuario ur ON ur.id = rd.usuario_fk LEFT JOIN empreendedor e ON e.usuario_fk = d.empreendedor_fk LEFT JOIN usuario u ON u.id = e.usuario_fk WHERE rd.id=?";

      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setLong(1, id);
      ResultSet rs = ps.executeQuery();
      RespostaDuvida respostaDuvida = null;
      if (rs.next()) {
         respostaDuvida = new RespostaDuvida();
         respostaDuvida.setId(rs.getLong("id"));
         respostaDuvida.setResposta(rs.getString("resposta"));
         respostaDuvida.setDataHora(rs.getTimestamp("data_hora"));

         Usuario usuario = new Usuario();
         usuario.setId(rs.getLong("usuario_resposta_id"));
         usuario.setNome(rs.getString("usuario_resposta_nome"));
         usuario.setEmail(rs.getString("usuario_resposta_email"));
         usuario.setTelefone(rs.getString("usuario_resposta_telefone"));
         usuario.setTipoUsuario(rs.getString("usuario_resposta_tipo_usuario"));
         usuario.setSobrenome(rs.getString("usuario_resposta_sobrenome"));

         respostaDuvida.setUsuario(usuario);

         Duvida duvida = new Duvida();
         duvida.setId(rs.getLong("duvida_id"));
         duvida.setDuvida(rs.getString("duvida_duvida"));
         duvida.setDataHora(rs.getTimestamp("duvida_data_hora"));

         Empreendedor empreendedor = new Empreendedor();
         empreendedor.setId(rs.getLong("empreendedor_id"));
         empreendedor.setNome(rs.getString("empreendedor_nome"));
         empreendedor.setEmail(rs.getString("empreendedor_email"));
         empreendedor.setTelefone(rs.getString("empreendedor_telefone"));
         empreendedor.setTipoUsuario(rs.getString("empreendedor_tipo_usuario"));
         empreendedor.setSobrenome(rs.getString("empreendedor_sobrenome"));

         empreendedor.setCpf(rs.getString("empreendedor_cpf"));
         empreendedor.setRg(rs.getString("empreendedor_rg"));
         empreendedor.setDataNascimento(new java.util.Date(rs.getDate("empreendedor_data_nascimento").getTime()));
         empreendedor.setEscolaridade(rs.getString("empreendedor_escolaridade"));
         empreendedor.setFormacaoProfissional(rs.getString("empreendedor_formacao_profissional"));
         empreendedor.setOcupacao(rs.getString("empreendedor_ocupacao"));
         empreendedor.setFax(rs.getString("empreendedor_fax"));

         duvida.setEmpreendedor(empreendedor);

         respostaDuvida.setDuvida(duvida);
      }
      rs.close();
      ps.close();

      return respostaDuvida;
   }

   @Override
   public List<RespostaDuvida> readByCriteria(Map<String, Object> criteria, Connection conn) throws Exception {
      List<RespostaDuvida> respostaDuvidaList = new ArrayList<RespostaDuvida>();

      String sql = "SELECT rd.*, d.duvida as duvida_duvida, d.data_hora as duvida_data_hora, ur.id as usuario_resposta_id, ur.nome as usuario_resposta_nome, ur.email as usuario_resposta_email, "
              + " ur.senha as usuario_resposta_senha, ur.telefone as usuario_resposta_telefone, ur.tipo_usuario as usuario_resposta_tipo_usuario, ur.sobrenome as usuario_resposta_sobrenome, "
              + " e.usuario_fk as empreendedor_id, e.cpf as empreendedor_cpf, e.rg as empreendedor_rg, e.data_nascimento as empreendedor_data_nascimento, e.escolaridade as empreendedor_escolaridade, "
              + " e.formacao_profissional as empreendedor_formacao_profissional, e.ocupacao as empreendedor_ocupacao, e.fax as empreendedor_fax, u.nome as empreendedor_nome, u.email as empreendedor_email, u.senha as empreendedor_senha, u.telefone as empreendedor_telefone, "
              + " u.tipo_usuario as empreendedor_tipo_usuario, u.sobrenome as empreendedor_sobrenome FROM resposta_duvida rd LEFT JOIN duvida d ON d.id = rd.duvida_fk "
              + " LEFT JOIN usuario ur ON ur.id = rd.usuario_fk LEFT JOIN empreendedor e ON e.usuario_fk = d.empreendedor_fk LEFT JOIN usuario u ON u.id = e.usuario_fk WHERE 1=1 ";

      Statement s = conn.createStatement();

      if (criteria != null) {
         boolean criterionDataHoraOrderByAsc = (boolean) criteria.get(CRITERION_DATA_HORA_ORDER_BY_ASC);
         if (criterionDataHoraOrderByAsc == true) {
            sql += " and 1=1 order by data_hora asc  ";
         }

      }

      ResultSet rs = s.executeQuery(sql);
      RespostaDuvida respostaDuvida = null;

      while (rs.next()) {
         respostaDuvida = new RespostaDuvida();
         respostaDuvida.setId(rs.getLong("id"));
         respostaDuvida.setResposta(rs.getString("resposta"));
         respostaDuvida.setDataHora(rs.getTimestamp("data_hora"));

         Usuario usuario = new Usuario();
         usuario.setId(rs.getLong("usuario_resposta_id"));
         usuario.setNome(rs.getString("usuario_resposta_nome"));
         usuario.setEmail(rs.getString("usuario_resposta_email"));
         usuario.setTelefone(rs.getString("usuario_resposta_telefone"));
         usuario.setTipoUsuario(rs.getString("usuario_resposta_tipo_usuario"));
         usuario.setSobrenome(rs.getString("usuario_resposta_sobrenome"));

         respostaDuvida.setUsuario(usuario);

         Duvida duvida = new Duvida();
         duvida.setId(rs.getLong("duvida_id"));
         duvida.setDuvida(rs.getString("duvida_duvida"));
         duvida.setDataHora(rs.getTimestamp("duvida_data_hora"));

         Empreendedor empreendedor = new Empreendedor();
         empreendedor.setId(rs.getLong("empreendedor_id"));
         empreendedor.setNome(rs.getString("empreendedor_nome"));
         empreendedor.setEmail(rs.getString("empreendedor_email"));
         empreendedor.setTelefone(rs.getString("empreendedor_telefone"));
         empreendedor.setTipoUsuario(rs.getString("empreendedor_tipo_usuario"));
         empreendedor.setSobrenome(rs.getString("empreendedor_sobrenome"));

         empreendedor.setCpf(rs.getString("empreendedor_cpf"));
         empreendedor.setRg(rs.getString("empreendedor_rg"));
         empreendedor.setDataNascimento(new java.util.Date(rs.getDate("empreendedor_data_nascimento").getTime()));
         empreendedor.setEscolaridade(rs.getString("empreendedor_escolaridade"));
         empreendedor.setFormacaoProfissional(rs.getString("empreendedor_formacao_profissional"));
         empreendedor.setOcupacao(rs.getString("empreendedor_ocupacao"));
         empreendedor.setFax(rs.getString("empreendedor_fax"));

         duvida.setEmpreendedor(empreendedor);

         respostaDuvida.setDuvida(duvida);

         respostaDuvidaList.add(respostaDuvida);
      }

      rs.close();

      s.close();

      return respostaDuvidaList;
   }

   @Override
   public void update(RespostaDuvida e, Connection conn) throws Exception {
      String sql = "UPDATE resposta_duvida SET resposta=? WHERE id=?;";
      PreparedStatement ps = conn.prepareStatement(sql);
      int i = 0;
      ps.setString(++i, e.getResposta());
      ps.setLong(++i, e.getId());
      ps.execute();
      ps.close();
   }

   @Override
   public void delete(Long id, Connection conn) throws Exception {
      String sql = "DELETE FROM resposta_duvida WHERE id=?;";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setLong(1, id);
      ps.execute();
      ps.close();
   }

}
