/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.incubadora.model.dao;

import gerenciador.incubadora.model.base.BaseDAO;
import gerenciador.incubadora.model.entity.EstagioEvolucao;
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
public class EstagioEvolucaoDAO implements BaseDAO<EstagioEvolucao> {

  @Override
  public void create(EstagioEvolucao e, Connection conn) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public EstagioEvolucao readById(Long id, Connection conn) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public List<EstagioEvolucao> readByCriteria(Map<String, Object> criteria, Connection conn) throws Exception {
    String sql = "SELECT * FROM estagio_evolucao ev WHERE 1=1";
    PreparedStatement ps = conn.prepareStatement(sql);

    ResultSet rs = ps.executeQuery();
    List<EstagioEvolucao> estagioList = new ArrayList<>();
    while (rs.next()) {
      EstagioEvolucao estagioEvolucao = new EstagioEvolucao();
      estagioEvolucao.setId(rs.getLong("id"));
      estagioEvolucao.setNome(rs.getString("nome"));

      estagioList.add(estagioEvolucao);
    }
    rs.close();
    ps.close();
    return estagioList;
  }

  @Override
  public void update(EstagioEvolucao e, Connection conn) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void delete(Long id, Connection conn) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}
