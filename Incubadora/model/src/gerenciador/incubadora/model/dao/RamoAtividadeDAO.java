package gerenciador.incubadora.model.dao;

import gerenciador.incubadora.model.base.BaseDAO;
import gerenciador.incubadora.model.entity.RamoAtividade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RamoAtividadeDAO implements BaseDAO<RamoAtividade> {

  @Override
  public void create(RamoAtividade e, Connection conn) throws Exception {
    String sql = "INSERT INTO ramo_atividade (nome) VALUES (?) RETURNING id;";
    PreparedStatement ps = conn.prepareStatement(sql);
    int i = 0;
    ps.setString(++i, e.getNome());
    ResultSet rs = ps.executeQuery();
    if (rs.next()) {
      e.setId(rs.getLong("id"));
    }
    rs.close();
    ps.close();
  }

  @Override
  public RamoAtividade readById(Long id, Connection conn) throws Exception {
    RamoAtividade ramoAtividade = null;
    String sql = "SELECT * FROM ramo_atividade WHERE id=?;";
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setLong(1, id);
    ResultSet rs = ps.executeQuery();
    if (rs.next()) {
      ramoAtividade = new RamoAtividade();
      ramoAtividade.setId(rs.getLong("id"));
      ramoAtividade.setNome(rs.getString("nome"));
    }
    rs.close();
    ps.close();
    return ramoAtividade;
  }

  @Override
  public List<RamoAtividade> readByCriteria(Map<String, Object> criteria, Connection conn) throws Exception {
    List<RamoAtividade> ramoAtividadeList = new ArrayList<RamoAtividade>();
    String sql = "SELECT * FROM ramo_atividade WHERE 1=1";

    Statement s = conn.createStatement();
    ResultSet rs = s.executeQuery(sql);
    while (rs.next()) {
      RamoAtividade ramoAtividade = new RamoAtividade();
      ramoAtividade.setId(rs.getLong("id"));
      ramoAtividade.setNome(rs.getString("nome"));
      ramoAtividadeList.add(ramoAtividade);
    }

    rs.close();
    s.close();
    return ramoAtividadeList;
  }

  @Override
  public void update(RamoAtividade e, Connection conn) throws Exception {
    String sql = "UPDATE ramo_atividade SET nome =? WHERE id=?;";
    PreparedStatement ps = conn.prepareStatement(sql);
    int i = 0;
    ps.setString(++i, e.getNome());
    ps.setLong(++i, e.getId());
    ps.execute();
    ps.close();
  }

  @Override
  public void delete(Long id, Connection conn) throws Exception {
    String sql = "DELETE FROM ramo_atividade WHERE id=?;";
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setLong(1, id);
    ps.execute();
    ps.close();
  }

}
