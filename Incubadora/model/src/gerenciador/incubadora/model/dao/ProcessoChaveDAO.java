package gerenciador.incubadora.model.dao;

import gerenciador.incubadora.model.base.BaseDAO;
import gerenciador.incubadora.model.entity.ArquivoProcesso;
import gerenciador.incubadora.model.entity.Gestor;
import gerenciador.incubadora.model.entity.ProcessoChave;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProcessoChaveDAO implements BaseDAO<ProcessoChave> {


  @Override
  public void create(ProcessoChave e, Connection conn) throws Exception {
    String sql = "INSERT INTO processo_chave(nome,gestor_fk) VALUES(?,?) RETURNING id";
    
    PreparedStatement ps = conn.prepareStatement(sql);
    int i = 0;
    ps.setString(++i, e.getNome());
    ps.setLong(++i, e.getGestor().getId());
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
      e.setId(rs.getLong("id"));
    }
    rs.close();
    ps.close();
  }

  @Override
  public ProcessoChave readById(Long id, Connection conn) throws Exception {
    ProcessoChave processo = new ProcessoChave();
    String sql = "SELECT * FROM processo_chave WHERE id=?;";
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setLong(1, id);
    ResultSet rs = ps.executeQuery();
    if (rs.next()) {
      processo = new ProcessoChave();
      processo.setId(rs.getLong("id"));
      processo.setNome(rs.getString("nome"));
      // Anexo
      ArquivoProcesso ap = new ArquivoProcesso(rs.getString("path_arquivo"));
      processo.setAnexo(ap);
      // Gestor
      Gestor gestor = new Gestor();
      gestor.setId(rs.getLong("gestor_fk"));
      processo.setGestor(gestor);
    }
    rs.close();
    ps.close();
    return processo;

  }

  @Override
  public List<ProcessoChave> readByCriteria(Map<String, Object> criteria, Connection conn) throws Exception {
    List<ProcessoChave> processoList = new ArrayList<>();
    String sql = "SELECT * FROM processo_chave WHERE 1=1";

    Statement s = conn.createStatement();
    ResultSet rs = s.executeQuery(sql);
    while (rs.next()) {
      ProcessoChave processo = new ProcessoChave();
      processo.setId(rs.getLong("id"));
      processo.setNome(rs.getString("nome"));
       // Anexo
      ArquivoProcesso ap = new ArquivoProcesso(rs.getString("path_arquivo"));
      processo.setAnexo(ap);
      // Gestor
      Gestor gestor = new Gestor();
      gestor.setId(rs.getLong("gestor_fk"));
      processo.setGestor(gestor);
      processoList.add(processo);
    }
    rs.close();
    s.close();
    return processoList;
  }

  @Override
  public void update(ProcessoChave e, Connection conn) throws Exception {
    String sql = "UPDATE processo_chave SET nome=?, gestor_fk=?, path_arquivo=? WHERE id=?";
    PreparedStatement ps = conn.prepareStatement(sql);
    int i = 0;
    ps.setString(++i, e.getNome());
    ps.setLong(++i, e.getGestor().getId());
    ps.setString(++i, e.getAnexo().getPath());
    ps.setLong(++i, e.getId());
    ps.execute();
    ps.close();
    
  }

  @Override
  public void delete(Long id, Connection conn) throws Exception {
    String sql = "DELETE FROM processo_chave pc WHERE pc.id=?";
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setLong(1, id);
    ps.execute();
    ps.close();
    
  }

}
