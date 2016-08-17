/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.incubadora.model.dao;

import gerenciador.incubadora.model.base.BaseDAO;
import gerenciador.incubadora.model.entity.ArquivoPratica;
import gerenciador.incubadora.model.entity.EstagioEvolucao;
import gerenciador.incubadora.model.entity.PraticaChave;
import gerenciador.incubadora.model.entity.ProcessoChave;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author William
 */
public class PraticaChaveDAO implements BaseDAO<PraticaChave> {

   public static final String CRITERION_PROCESSO_ID = "1";
   public static final String CRITERION_PRATICA_NAMES_ILIKE = "2";

   @Override
   public void create(PraticaChave e, Connection conn) throws Exception {
      String sql = "INSERT INTO pratica_chave(nome, descricao, data_evolucao, "
              + "data_certificacao, estagio_evolucao_fk, processo_fk) VALUES (?, ?, ?, ?, ?, ?) RETURNING id";
      PreparedStatement ps = conn.prepareStatement(sql);

      if (e.getDataCertificacao() == null) {
         Date d = Date.valueOf("0001-01-01");
         e.setDataCertificacao(d);
      }

      int i = 0;
      ps.setString(++i, e.getNome());
      ps.setString(++i, e.getDescricao().trim());
      ps.setDate(++i, new Date(e.getDataEvolucao().getTime()));
      ps.setDate(++i, new Date(e.getDataCertificacao().getTime()));
      ps.setLong(++i, e.getEstagioEvolucao().getId());
      ps.setLong(++i, e.getProcessoChave().getId());
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
         e.setId(rs.getLong("id"));
      }
      rs.close();
      ps.close();

   }

   @Override
   public PraticaChave readById(Long id, Connection conn) throws Exception {
      String sql = "SELECT pc.*, ev.id as ev_id,ev.nome as ev_nome, pro.id as pro_id, "
              + " pro.nome as pro_nome, pro.gestor_fk FROM pratica_chave pc"
              + " LEFT JOIN estagio_evolucao ev ON pc.estagio_evolucao_fk=ev.id"
              + " LEFT JOIN processo_chave pro ON pc.processo_fk=pro.id WHERE pc.id=?";

      String sqlArquivos = "select ap.* from arquivo_pratica ap "
              + " left join pratica_chave pc on pc.id=ap.pratica_fk where pc.id=?";

      PreparedStatement psArquivos = conn.prepareStatement(sqlArquivos);

      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setLong(1, id);
      ResultSet rs = ps.executeQuery();

      PraticaChave praticaChave = null;
      if (rs.next()) {
         //PRÁTICA-CHAVE
         praticaChave = new PraticaChave();
         praticaChave.setId(rs.getLong("id"));
         praticaChave.setNome(rs.getString("nome"));
         praticaChave.setDataEvolucao(rs.getDate("data_evolucao"));
         praticaChave.setDataCertificacao(rs.getDate("data_certificacao"));
         praticaChave.setDescricao(rs.getString("descricao"));
         //ESTÁGIO DE EVOLUÇÃO
         EstagioEvolucao estagioEvolucao = new EstagioEvolucao();
         estagioEvolucao.setId(rs.getLong("ev_id"));
         estagioEvolucao.setNome(rs.getString("ev_nome"));
         praticaChave.setEstagioEvolucao(estagioEvolucao);
         //ARQUIVO
         psArquivos.setLong(1, praticaChave.getId());
         ResultSet rsArquivos = psArquivos.executeQuery();
         List<ArquivoPratica> arquivoPraticaList = new ArrayList<>();
         while (rsArquivos.next()) {
            ArquivoPratica ap = new ArquivoPratica(rsArquivos.getString("path_arquivo"));
            ap.setId(rsArquivos.getLong("id"));
            arquivoPraticaList.add(ap);
         }
         praticaChave.setArquivos(arquivoPraticaList);
         rsArquivos.close();
         psArquivos.close();
         //PROCESSO-CHAVE
         ProcessoChave processoChave = new ProcessoChave();
         processoChave.setId(rs.getLong("pro_id"));
         processoChave.setNome(rs.getString("pro_nome"));

         praticaChave.setProcessoChave(processoChave);
         praticaChave.setDescricao(rs.getString("descricao"));
         praticaChave.setEstagioEvolucao(estagioEvolucao);
      }
      rs.close();
      ps.close();
      return praticaChave;
   }

   @Override
   public List<PraticaChave> readByCriteria(Map<String, Object> criteria, Connection conn) throws Exception {
      String sql = "SELECT pc.*, ev.id as ev_id,ev.nome as ev_nome, pro.id as pro_id,"
              + "  pro.nome as pro_nome, pro.gestor_fk FROM pratica_chave pc"
              + "  LEFT JOIN estagio_evolucao ev ON pc.estagio_evolucao_fk=ev.id"
              + "  LEFT JOIN processo_chave pro ON pc.processo_fk=pro.id WHERE 1=1";

      String sqlArquivos = "select ap.* from arquivo_pratica ap "
              + " left join pratica_chave pc on pc.id=ap.pratica_fk where pc.id=?";

      PreparedStatement psArquivos = conn.prepareStatement(sqlArquivos);

      Long criterionProcessoId = (Long) criteria.get(CRITERION_PROCESSO_ID);
      if (criterionProcessoId != null) {
         sql += " AND pc.processo_fk=" + criterionProcessoId;
      }
      String praticaNome = (String) criteria.get(CRITERION_PRATICA_NAMES_ILIKE);
      if (praticaNome != null && !praticaNome.isEmpty()) {
         sql += " AND pc.nome ilike '" + praticaNome + "'";
      }

      PreparedStatement ps = conn.prepareStatement(sql);
      List<PraticaChave> praticaList = new ArrayList<>();
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
         //PRÁTICA-CHAVE
         PraticaChave praticaChave = new PraticaChave();
         praticaChave.setId(rs.getLong("id"));
         praticaChave.setNome(rs.getString("nome"));
         praticaChave.setDataEvolucao(rs.getDate("data_evolucao"));
         praticaChave.setDataCertificacao(rs.getDate("data_certificacao"));
         praticaChave.setDescricao(rs.getString("descricao"));
         //ARQUIVO
         psArquivos.setLong(1, praticaChave.getId());
         ResultSet rsArquivos = psArquivos.executeQuery();
         List<ArquivoPratica> arquivoPraticaList = new ArrayList<>();
         while (rsArquivos.next()) {
            ArquivoPratica ap = new ArquivoPratica(rsArquivos.getString("path_arquivo"));
            ap.setId(rsArquivos.getLong("id"));
            arquivoPraticaList.add(ap);
         }
         rsArquivos.close();
         praticaChave.setArquivos(arquivoPraticaList);
         //ESTÁGIO DE EVOLUÇÃO
         EstagioEvolucao estagioEvolucao = new EstagioEvolucao();
         estagioEvolucao.setId(rs.getLong("ev_id"));
         estagioEvolucao.setNome(rs.getString("ev_nome"));
         praticaChave.setEstagioEvolucao(estagioEvolucao);
         //PROCESSO-CHAVE
         ProcessoChave processoChave = new ProcessoChave();
         processoChave.setId(rs.getLong("pro_id"));
         processoChave.setNome(rs.getString("pro_nome"));

         praticaChave.setProcessoChave(processoChave);
         praticaChave.setDescricao(rs.getString("descricao"));
         praticaChave.setEstagioEvolucao(estagioEvolucao);
         praticaList.add(praticaChave);
      }      
      psArquivos.close();
      rs.close();
      ps.close();
      return praticaList;
   }

   @Override
   public void update(PraticaChave e, Connection conn) throws Exception {
      String sql = "UPDATE pratica_chave SET nome=?, descricao=?, data_evolucao=?, "
              + "data_certificacao=?, estagio_evolucao_fk=? WHERE id=?";
      PreparedStatement ps = conn.prepareStatement(sql);

      if (e.getDataCertificacao() == null) {
         Date d = Date.valueOf("0001-01-01");
         e.setDataCertificacao(d);
      }

      int i = 0;
      ps.setString(++i, e.getNome());
      ps.setString(++i, e.getDescricao());
      ps.setDate(++i, e.getDataEvolucao());
      ps.setDate(++i, e.getDataCertificacao());
      ps.setLong(++i, e.getEstagioEvolucao().getId());
      ps.setLong(++i, e.getId());
      ps.execute();
      ps.close();

      if (e.getArquivos() != null && !e.getArquivos().isEmpty()) {
         String sqlArquivos = "INSERT INTO arquivo_pratica(pratica_fk, path_arquivo) VALUES (?, ?) RETURNING id, path_arquivo;";
         PreparedStatement psArquivos = conn.prepareStatement(sqlArquivos);
         psArquivos.setLong(1, e.getId());
         psArquivos.setString(2, e.getArquivos().get(0).getPath());

         List<ArquivoPratica> arquivoPraticaList = new ArrayList<>();
         for (ArquivoPratica arquivo : e.getArquivos()) {
            ResultSet rsArquivos = psArquivos.executeQuery();
            if (rsArquivos.next()) {
               ArquivoPratica ap = new ArquivoPratica("path_arquivo");
               ap.setId(rsArquivos.getLong("id"));
               arquivoPraticaList.add(ap);
            }
            rsArquivos.close();
         }
         e.setArquivos(arquivoPraticaList);

         psArquivos.close();
      }

   }

   @Override
   public void delete(Long id, Connection conn) throws Exception {
      String sql = "DELETE FROM pratica_chave WHERE id=?";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setLong(1, id);
      ps.execute();
      ps.close();
   }

}
