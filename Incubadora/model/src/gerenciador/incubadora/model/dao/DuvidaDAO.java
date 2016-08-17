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
public class DuvidaDAO implements BaseDAO<Duvida> {

   public static final String CRITERION_DUVIDA_ILIKE = "1";
   public static final String CRITERION_EMPREENDEDOR_EQ = "2";
   public static final String CRITERION_DATA_HORA_ORDER_BY_ASC = "3";
   public static final String CRITERION_EMPREENDEDOR_ID = "4";

   @Override
   public void create(Duvida e, Connection conn) throws Exception {
      String sql = "INSERT INTO duvida(duvida, data_hora, empreendedor_fk) VALUES (?, ?, ?) RETURNING id;";
      PreparedStatement ps = conn.prepareStatement(sql);
      int i = 0;
      ps.setString(++i, e.getDuvida());
      ps.setTimestamp(++i, new Timestamp(e.getDataHora().getTime()));
      ps.setLong(++i, e.getEmpreendedor().getId());

      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
         e.setId(rs.getLong("id"));
      }
      rs.close();
      ps.close();

   }

   @Override
   public Duvida readById(Long id, Connection conn) throws Exception {
      String sql = "SELECT d.*, e.cpf as empreendedor_cpf, e.rg as empreendedor_rg, e.data_nascimento as empreendedor_data_nascimento, e.escolaridade as empreendedor_escolaridade, "
              + " e.formacao_profissional as empreendedor_formacao_profissional, e.ocupacao as empreendedor_ocupacao, e.fax as empreendedor_fax, "
              + " u.nome as empreendedor_nome, u.email as empreendedor_email, u.senha as empreendedor_senha, u.telefone as empreendedor_telefone, "
              + " u.tipo_usuario as empreendedor_tipo_usuario, u.sobrenome as empreendedor_sobrenome, rd.id as resposta_duvida_id, rd.resposta as resposta_duvida_resposta, rd.data_hora as resposta_duvida_data_hora, "
              + " ur.id as usuario_resposta_id, ur.nome as usuario_resposta_nome, ur.email as usuario_resposta_email, ur.senha as usuario_resposta_senha, ur.telefone as usuario_resposta_telefone, "
              + " ur.tipo_usuario as usuario_resposta_tipo_usuario, ur.sobrenome as usuario_resposta_sobrenome FROM duvida d LEFT JOIN empreendedor e ON e.usuario_fk = d.empreendedor_fk "
              + "LEFT JOIN usuario u ON u.id = e.usuario_fk LEFT JOIN resposta_duvida rd ON rd.duvida_fk = d.id LEFT JOIN usuario ur ON ur.id = rd.usuario_fk WHERE d.id=?;";

      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setLong(1, id);
      ResultSet rs = ps.executeQuery();
      Duvida duvida = null;
      List<RespostaDuvida> respostaDuvidaList = new ArrayList<RespostaDuvida>();
      while (rs.next()) {
         if (duvida == null) {
            duvida = new Duvida();
            duvida.setId(rs.getLong("id"));
            duvida.setDuvida(rs.getString("duvida"));
            duvida.setDataHora(rs.getTimestamp("data_hora"));

            Empreendedor empreendedor = new Empreendedor();
            empreendedor.setId(rs.getLong("empreendedor_fk"));
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
         }

         RespostaDuvida respostaDuvida = new RespostaDuvida();
         respostaDuvida.setId(rs.getLong("resposta_duvida_id"));
         respostaDuvida.setDataHora(rs.getTimestamp("resposta_duvida_data_hora"));
         respostaDuvida.setResposta(rs.getString("resposta_duvida_resposta"));

         Usuario usuario = new Usuario();
         usuario.setId(rs.getLong("usuario_resposta_id"));
         usuario.setNome(rs.getString("usuario_resposta_nome"));
         usuario.setEmail(rs.getString("usuario_resposta_email"));
         usuario.setTelefone(rs.getString("usuario_resposta_telefone"));
         usuario.setTipoUsuario(rs.getString("usuario_resposta_tipo_usuario"));
         usuario.setSobrenome(rs.getString("usuario_resposta_sobrenome"));

         respostaDuvida.setUsuario(usuario);

         respostaDuvidaList.add(respostaDuvida);

      }
      rs.close();
      ps.close();
      duvida.setRespostaDuvidaList(respostaDuvidaList);

      return duvida;
   }

   @Override
   public List<Duvida> readByCriteria(Map<String, Object> criteria, Connection conn) throws Exception {
      List<Duvida> duvidaList = new ArrayList<>();
      String sql = "SELECT d.*, e.cpf as empreendedor_cpf, e.rg as empreendedor_rg, e.data_nascimento as empreendedor_data_nascimento, e.escolaridade as empreendedor_escolaridade,"
              + " e.formacao_profissional as empreendedor_formacao_profissional, e.ocupacao as empreendedor_ocupacao, e.fax as empreendedor_fax, "
              + " u.nome as empreendedor_nome, u.email as empreendedor_email, u.senha as empreendedor_senha, u.telefone as empreendedor_telefone,"
              + "  u.tipo_usuario as empreendedor_tipo_usuario, u.sobrenome as empreendedor_sobrenome FROM duvida d LEFT JOIN empreendedor e ON e.usuario_fk = d.empreendedor_fk"
              + " LEFT JOIN usuario u ON u.id = e.usuario_fk WHERE 1=1 ";
      Statement s = conn.createStatement();

      if (criteria != null) {
         String criterionDuvidaIlike = (String) criteria.get(CRITERION_DUVIDA_ILIKE);
         if (criterionDuvidaIlike != null && !criterionDuvidaIlike.isEmpty()) {
            sql += " AND d.duvida ilike '%" + criterionDuvidaIlike + "%'";
         }
         Long criterionEmpreendedorEq = (Long) criteria.get(CRITERION_EMPREENDEDOR_EQ);
         if (criterionEmpreendedorEq != null && criterionEmpreendedorEq > 0L) {
            sql += " AND d.empreendedor_fk =" + criterionEmpreendedorEq;
         }
         
         //Deve ser sempre o último critério
         boolean criterionOrderByDataHoraAsc = (boolean) criteria.get(CRITERION_DATA_HORA_ORDER_BY_ASC);
         if (criterionOrderByDataHoraAsc == true) {
            sql += " AND 1=1 order by data_hora desc";
         }

      }

      ResultSet rs = s.executeQuery(sql);
      Duvida duvida;
      while (rs.next()) {
         duvida = new Duvida();
         duvida.setId(rs.getLong("id"));
         duvida.setDuvida(rs.getString("duvida"));
         duvida.setDataHora(rs.getTimestamp("data_hora"));

         Empreendedor empreendedor = new Empreendedor();
         empreendedor.setId(rs.getLong("empreendedor_fk"));
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

         duvidaList.add(duvida);
      }
      rs.close();
      s.close();

      for (Duvida aux : duvidaList) {
         String sqlResposta = "SELECT rd.*, ur.id as usuario_resposta_id, ur.nome as usuario_resposta_nome, ur.email as usuario_resposta_email, ur.senha as usuario_resposta_senha, "
                 + "  ur.telefone as usuario_resposta_telefone, ur.tipo_usuario as usuario_resposta_tipo_usuario, ur.sobrenome as usuario_resposta_sobrenome "
                 + " FROM resposta_duvida rd LEFT JOIN usuario ur ON ur.id = rd.usuario_fk WHERE rd.duvida_fk = ? and 1=1 order by data_hora asc";

         PreparedStatement psResposta = conn.prepareStatement(sqlResposta);
         psResposta.setLong(1, aux.getId());

         ResultSet rsResposta = psResposta.executeQuery();
         List<RespostaDuvida> respostaDuvidaList = new ArrayList<RespostaDuvida>();
         while (rsResposta.next()) {
            RespostaDuvida respostaDuvida = new RespostaDuvida();
            respostaDuvida.setId(rsResposta.getLong("id"));
            respostaDuvida.setDataHora(rsResposta.getTimestamp("data_hora"));
            respostaDuvida.setResposta(rsResposta.getString("resposta"));

            Usuario usuario = new Usuario();
            usuario.setId(rsResposta.getLong("usuario_resposta_id"));
            usuario.setNome(rsResposta.getString("usuario_resposta_nome"));
            usuario.setEmail(rsResposta.getString("usuario_resposta_email"));
            usuario.setTelefone(rsResposta.getString("usuario_resposta_telefone"));
            usuario.setTipoUsuario(rsResposta.getString("usuario_resposta_tipo_usuario"));
            usuario.setSobrenome(rsResposta.getString("usuario_resposta_sobrenome"));

            respostaDuvida.setUsuario(usuario);

            respostaDuvidaList.add(respostaDuvida);
         }
         rsResposta.close();
         psResposta.close();
         aux.setRespostaDuvidaList(respostaDuvidaList);
      }

      return duvidaList;
   }

   @Override
   public void update(Duvida e, Connection conn) throws Exception {
      String sql = "UPDATE duvida SET duvida=?, data_hora=?, empreendedor_fk=? WHERE id=?;";
      PreparedStatement ps = conn.prepareStatement(sql);
      int i = 0;
      ps.setString(++i, e.getDuvida());
      ps.setTimestamp(++i, new Timestamp(e.getDataHora().getTime()));
      ps.setLong(++i, e.getEmpreendedor().getId());
      ps.setLong(++i, e.getId());
      ps.execute();
      ps.close();
   }

   @Override
   public void delete(Long id, Connection conn) throws Exception {
      String sql = "DELETE FROM duvida WHERE id=?;";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setLong(1, id);
      ps.execute();
      ps.close();
   }

}
