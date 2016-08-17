package gerenciador.incubadora.model.dao;

import gerenciador.incubadora.model.base.BaseDAO;
import gerenciador.incubadora.model.entity.ArquivoPratica;
import gerenciador.incubadora.model.entity.CronogramaAnual;
import gerenciador.incubadora.model.entity.EstagioEvolucao;
import gerenciador.incubadora.model.entity.Gestor;
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
public class CronogramaAnualDAO implements BaseDAO<CronogramaAnual> {

   public static final String CRITERION_USUARIOS = "1";
   public static final String CRITERION_ORDERBY_DATE_ASC = "2";

   @Override
   public void create(CronogramaAnual e, Connection conn) throws Exception {
      String sqlCronograma = "INSERT INTO cronograma_anual"
              + " (atividade, data_inicio, data_fim, status, comentario, total_horas)"
              + " VALUES (?, ?, ?, ?, ?, ?) RETURNING id;";

      int i = 0;
      PreparedStatement psCronograma = conn.prepareStatement(sqlCronograma);
      psCronograma.setString(++i, e.getAtividade());
      psCronograma.setDate(++i, e.getDataInicio());
      psCronograma.setDate(++i, e.getDataFim());
      psCronograma.setString(++i, e.getStatus());
      psCronograma.setString(++i, e.getComentario());
      psCronograma.setString(++i, e.getTotalHoras());
      ResultSet rsCronograma = psCronograma.executeQuery();

      if (rsCronograma.next()) {
         e.setId(rsCronograma.getLong("id"));
      }
      rsCronograma.close();
      psCronograma.close();

      if (e.getPraticaList() != null && !e.getPraticaList().isEmpty()) {
         //Grava o relacionamento entre cronograma e prática-chave
         String sqlCronogramaPratica = "INSERT INTO cronograma_pratica(cronograma_fk, pratica_fk) VALUES (?, ?);";
         PreparedStatement psCronogramaPratica = conn.prepareStatement(sqlCronogramaPratica);
         psCronogramaPratica.setLong(1, e.getId());
         for (PraticaChave pc : e.getPraticaList()) {
            psCronogramaPratica.setLong(2, pc.getId());
            psCronogramaPratica.execute();
         }
         psCronogramaPratica.close();
      }
      if (e.getGestorList() != null && !e.getGestorList().isEmpty()) {
         //Grava o relacionamento entre cronograma e gestor
         String sqlCronogramaGestor = "INSERT INTO cronograma_gestor(cronograma_fk, gestor_fk) VALUES (?, ?);";
         PreparedStatement psCronogramaGestor = conn.prepareStatement(sqlCronogramaGestor);
         psCronogramaGestor.setLong(1, e.getId());
         for (Gestor g : e.getGestorList()) {
            psCronogramaGestor.setLong(2, g.getId());
            psCronogramaGestor.execute();
         }
         psCronogramaGestor.close();
      }
   }

   @Override
   public CronogramaAnual readById(Long id, Connection conn) throws Exception {
      String sqlCronograma = "SELECT ca.* FROM cronograma_anual ca WHERE ca.id=?";

      String sqlGestor = "SELECT g.*, u.nome FROM gestor g "
              + "  LEFT JOIN usuario u ON g.usuario_fk=u.id"
              + "  LEFT JOIN cronograma_gestor cg ON cg.gestor_fk=g.usuario_fk"
              + "  LEFT JOIN cronograma_anual ca ON ca.id=cg.cronograma_fk"
              + "  WHERE cg.cronograma_fk=?";

      String sqlPraticas = "SELECT ca.*, pc.id as pc_id, pc.nome as nome_pratica, pc.descricao, "
              + "            pc.data_evolucao,pc.processo_fk, pc.data_certificacao, pc.estagio_evolucao_fk FROM cronograma_pratica  cp "
              + "            LEFT JOIN pratica_chave pc ON pc.id=cp.pratica_fk "
              + "            LEFT JOIN cronograma_anual ca ON ca.id=cp.cronograma_fk "
              + "            WHERE ca.id=?";

      //Cronograma
      PreparedStatement psCronograma = conn.prepareStatement(sqlCronograma);
      psCronograma.setLong(1, id);
      ResultSet rsCronograma = psCronograma.executeQuery();
      //Gestor
      PreparedStatement psGestor = conn.prepareStatement(sqlGestor);
      CronogramaAnual cronogramaAnual = new CronogramaAnual();
      if (rsCronograma.next()) {
         //Cronograma
         cronogramaAnual = new CronogramaAnual();
         cronogramaAnual.setId(rsCronograma.getLong("id"));
         cronogramaAnual.setAtividade(rsCronograma.getString("atividade"));
         cronogramaAnual.setComentario(rsCronograma.getString("comentario"));
         cronogramaAnual.setDataInicio(rsCronograma.getDate("data_inicio"));
         cronogramaAnual.setDataFim(rsCronograma.getDate("data_fim"));
         cronogramaAnual.setTotalHoras(rsCronograma.getString("total_horas"));
         cronogramaAnual.setStatus(rsCronograma.getString("status"));

         //Gestor (Usuários)
         psGestor.setLong(1, cronogramaAnual.getId());
         ResultSet rsGestor = psGestor.executeQuery();
         List<Gestor> gestorList = new ArrayList<>();
         while (rsGestor.next()) {
            Gestor gestor = new Gestor();
            gestor.setId(rsGestor.getLong("usuario_fk"));
            gestor.setNome(rsGestor.getString("nome"));
            gestorList.add(gestor);
         }
         rsGestor.close();
         //Set Gestor
         cronogramaAnual.setGestorList(gestorList);
         //Set prática-chave
         PreparedStatement psPraticas = conn.prepareStatement(sqlPraticas);
         psPraticas.setLong(1, cronogramaAnual.getId());
         ResultSet rsPraticas = psPraticas.executeQuery();
         List<PraticaChave> praticas = new ArrayList<>();
         while (rsPraticas.next()) {
            PraticaChave pc = new PraticaChave();
            pc.setId(rsPraticas.getLong("pc_id"));
            pc.setNome(rsPraticas.getString("nome_pratica"));
            pc.setDescricao(rsPraticas.getString("descricao"));
            //Processo-Chave id
            ProcessoChave proCh = new ProcessoChave();
            proCh.setId(rsPraticas.getLong("processo_fk"));
            pc.setProcessoChave(proCh);
            //Arquivos
            pc.setArquivos(new ArrayList<ArquivoPratica>());
            pc.setDataCertificacao(rsPraticas.getDate("data_evolucao"));
            pc.setDataCertificacao(rsPraticas.getDate("data_certificacao"));
            //Estágio de Evolução id
            EstagioEvolucao ee = new EstagioEvolucao();
            ee.setId(rsPraticas.getLong("estagio_evolucao_fk"));
            pc.setEstagioEvolucao(ee);

            praticas.add(pc);
         }
         cronogramaAnual.setPraticaList(praticas);
         rsPraticas.close();
         psPraticas.close();

      }

      rsCronograma.close();
      psCronograma.close();
      //Close Gestor
      psGestor.close();
      return cronogramaAnual;

   }

   @Override
   public List<CronogramaAnual> readByCriteria(Map<String, Object> criteria, Connection conn) throws Exception {
      String sqlCronograma = "SELECT ca.* FROM cronograma_anual ca WHERE 1=1";

      String sqlGestor = "SELECT g.*, u.nome FROM gestor g "
              + "  LEFT JOIN usuario u ON g.usuario_fk=u.id"
              + "  LEFT JOIN cronograma_gestor cg ON cg.gestor_fk=g.usuario_fk"
              + "  LEFT JOIN cronograma_anual ca ON ca.id=cg.cronograma_fk"
              + "  WHERE cg.cronograma_fk=?";

      String sqlPraticas = "SELECT ca.*, pc.id as pc_id, pc.nome as nome_pratica, pc.descricao, "
              + " pc.data_evolucao,pc.processo_fk, pc.data_certificacao, pc.estagio_evolucao_fk FROM cronograma_pratica  cp "
              + " LEFT JOIN pratica_chave pc ON pc.id=cp.pratica_fk "
              + " LEFT JOIN cronograma_anual ca ON ca.id=cp.cronograma_fk "
              + " WHERE ca.id=?";

      //Deve ser sempre o último critério
      boolean criterionOrderbyDateAsc = (boolean) criteria.get(CRITERION_ORDERBY_DATE_ASC);
      if (criterionOrderbyDateAsc == true) {
         sqlCronograma += " ORDER BY ca.data_inicio, ca.data_fim ASC ";
      }
      //Cronograma
      PreparedStatement psCronograma = conn.prepareStatement(sqlCronograma);
      ResultSet rsCronograma = psCronograma.executeQuery();
      List<CronogramaAnual> cronogramaList = new ArrayList<>();
      //Gestor
      PreparedStatement psGestor = conn.prepareStatement(sqlGestor);

      // Práticas
      PreparedStatement psPraticas = conn.prepareStatement(sqlPraticas);

      while (rsCronograma.next()) {
         //Cronograma
         CronogramaAnual cronogramaAnual = new CronogramaAnual();
         cronogramaAnual.setId(rsCronograma.getLong("id"));
         cronogramaAnual.setAtividade(rsCronograma.getString("atividade"));
         cronogramaAnual.setComentario(rsCronograma.getString("comentario"));
         cronogramaAnual.setDataInicio(rsCronograma.getDate("data_inicio"));
         cronogramaAnual.setDataFim(rsCronograma.getDate("data_fim"));
         cronogramaAnual.setStatus(rsCronograma.getString("status"));
         cronogramaAnual.setTotalHoras(rsCronograma.getString("total_horas"));

         psGestor.setLong(1, cronogramaAnual.getId());
         ResultSet rsGestor = psGestor.executeQuery();
         List<Gestor> gestorList = new ArrayList<>();
         while (rsGestor.next()) {
            Gestor gestor = new Gestor();
            gestor.setId(rsGestor.getLong("usuario_fk"));
            gestor.setNome(rsGestor.getString("nome"));
            gestorList.add(gestor);
         }
         rsGestor.close();
         //Set Gestor
         cronogramaAnual.setGestorList(gestorList);
         //Set prática-chave
         psPraticas.setLong(1, cronogramaAnual.getId());
         ResultSet rsPraticas = psPraticas.executeQuery();
         List<PraticaChave> praticas = new ArrayList<>();
         while (rsPraticas.next()) {
            PraticaChave pc = new PraticaChave();
            pc.setId(rsPraticas.getLong("pc_id"));
            pc.setNome(rsPraticas.getString("nome_pratica"));
            pc.setDescricao(rsPraticas.getString("descricao"));
            //Processo-Chave id
            ProcessoChave proCh = new ProcessoChave();
            proCh.setId(rsPraticas.getLong("processo_fk"));
            pc.setProcessoChave(proCh);
            //Arquivos
            pc.setArquivos(new ArrayList<ArquivoPratica>());
            pc.setDataCertificacao(rsPraticas.getDate("data_evolucao"));
            pc.setDataCertificacao(rsPraticas.getDate("data_certificacao"));
            //Estágio de Evolução id
            EstagioEvolucao ee = new EstagioEvolucao();
            ee.setId(rsPraticas.getLong("estagio_evolucao_fk"));
            pc.setEstagioEvolucao(ee);

            praticas.add(pc);
         }
         cronogramaAnual.setPraticaList(praticas);
         rsPraticas.close();

         cronogramaList.add(cronogramaAnual);
      }
      psGestor.close();
      psPraticas.close();
      rsCronograma.close();
      psCronograma.close();
      //Close gestor
      return cronogramaList;
   }

   @Override
   public void update(CronogramaAnual e, Connection conn) throws Exception {
      String sqlCronograma = "UPDATE cronograma_anual SET atividade=?, data_inicio=?, data_fim=?, "
              + " status=?, comentario=?, total_horas=? WHERE id=?;";
      PreparedStatement psCronograma = conn.prepareStatement(sqlCronograma);
      int i = 0;
      psCronograma.setString(++i, e.getAtividade());
      psCronograma.setDate(++i, (Date) e.getDataInicio());
      psCronograma.setDate(++i, (Date) e.getDataFim());
      psCronograma.setString(++i, e.getStatus());
      psCronograma.setString(++i, e.getComentario());
      psCronograma.setString(++i, e.getTotalHoras());
      psCronograma.setLong(++i, e.getId());
      psCronograma.execute();
      psCronograma.close();

      String sqlDeletePratica = "DELETE FROM cronograma_pratica WHERE cronograma_fk=?";
      PreparedStatement psDeletePratica = conn.prepareStatement(sqlDeletePratica);

      psDeletePratica.setLong(1, e.getId());
      psDeletePratica.execute();

      String sqlCronogramaPratica = "INSERT INTO cronograma_pratica(cronograma_fk, pratica_fk) VALUES (?, ?);";
      PreparedStatement psCronogramaPratica = conn.prepareStatement(sqlCronogramaPratica);
      if (e.getPraticaList() != null && !e.getPraticaList().isEmpty()) {
         for (PraticaChave pc : e.getPraticaList()) {
            psCronogramaPratica.setLong(1, e.getId());
            psCronogramaPratica.setLong(2, pc.getId());
            psCronogramaPratica.execute();
         }
         psCronogramaPratica.close();
      }

      String sqlDeleteGestor = "DELETE FROM cronograma_gestor WHERE cronograma_fk=?";
      PreparedStatement psDeleteGestor = conn.prepareStatement(sqlDeleteGestor);
      psDeleteGestor.setLong(1, e.getId());
      psDeleteGestor.execute();

      String sqlCronogramaGestor = "INSERT INTO cronograma_gestor(cronograma_fk, gestor_fk) VALUES (?, ?);";
      PreparedStatement psCronogramaGestor = conn.prepareStatement(sqlCronogramaGestor);
      if (e.getGestorList() != null && !e.getGestorList().isEmpty()) {
         for (Gestor gestor : e.getGestorList()) {
            psCronogramaGestor.setLong(1, e.getId());
            psCronogramaGestor.setLong(2, gestor.getId());
            psCronogramaGestor.execute();
         }
         psCronogramaGestor.close();
      }

   }

   @Override
   public void delete(Long id, Connection conn) throws Exception {
      String sql = "DELETE FROM cronograma_anual WHERE id=?";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setLong(1, id);
      ps.execute();
      ps.close();
   }

}
