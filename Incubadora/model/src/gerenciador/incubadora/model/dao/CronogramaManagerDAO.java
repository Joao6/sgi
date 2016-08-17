/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.incubadora.model.dao;

import gerenciador.incubadora.model.base.dao.BaseCronogramaManagerDAO;
import gerenciador.incubadora.model.entity.CronogramaAnual;
import gerenciador.incubadora.model.entity.CronogramaManager;
import gerenciador.incubadora.model.entity.Gestor;
import gerenciador.incubadora.model.entity.PraticaChave;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

/**
 *
 * @author William
 */
public class CronogramaManagerDAO implements BaseCronogramaManagerDAO {

  @Override
  public void create(CronogramaManager e, Connection conn) throws Exception {

    //Grava o relacionamento entre cronograma e prática-chave
    String sqlCronogramaPratica = "INSERT INTO cronograma_pratica(cronograma_fk, pratica_fk) VALUES (?, ?);";
    PreparedStatement psCronogramaPratica = conn.prepareStatement(sqlCronogramaPratica);
    psCronogramaPratica.setLong(1, e.getId());
    for (PraticaChave pc : e.getPraticaList()) {
      psCronogramaPratica.setLong(2, pc.getId());
      psCronogramaPratica.execute();
    }
    psCronogramaPratica.close();

    //Grava o relacionamento entre cronograma e gestor
    String sqlCronogramaGestor = "INSERT INTO cronograma_gestor(cronograma_fk, gestor_fk) VALUES (?, ?);";
    PreparedStatement psCronogramaGestor = conn.prepareStatement(sqlCronogramaGestor);
    psCronogramaGestor.setLong(1, e.getId());
    for (Gestor i : e.getGestorList()) {
      psCronogramaGestor.setLong(2, i.getId());
      psCronogramaGestor.execute();
    }
    psCronogramaGestor.close();

  }

  @Override
  public CronogramaManager readById(Long id, Connection conn) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public List<CronogramaManager> readByCriteria(Map<String, Object> criteria, Connection conn) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void update(CronogramaManager e, Connection conn) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void delete(Long id, Connection conn) throws Exception {
    String sql = "DELETE FROM cronograma_gestor WHERE cronograma_fk=?";
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setLong(1, id);
    ps.execute();
  }

  @Override
  public boolean deleteGestor(Connection conn, Long idCronograma, Long idGestor) throws Exception {
    String sql = "DELETE FROM cronograma_gestor WHERE cronograma_fk=? AND gestor_fk=?;";
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setLong(1, idCronograma);
    ps.setLong(2, idGestor);
    return ps.execute();
  }

  @Override
  public boolean deletePraticaChave(Connection conn, CronogramaAnual cronogramaAnual) throws Exception {
    String sql = "DELETE FROM cronograma_pratica WHERE cronograma_fk=? AND pratica_fk=?;";
    PreparedStatement ps = conn.prepareStatement(sql);

    if (cronogramaAnual.getPraticaList() != null) {
      ps.setLong(1, cronogramaAnual.getId());
      ps.setLong(2, cronogramaAnual.getPraticaList().get(0).getId());
      ps.execute();
    }

    return true;
  }

  @Override
  public boolean insertPraticaChave(Connection conn, CronogramaAnual c) throws Exception {
    String sqlCronogramaPratica = "INSERT INTO cronograma_pratica(cronograma_fk, pratica_fk) VALUES (?, ?);";
    PreparedStatement psCronogramaPratica = conn.prepareStatement(sqlCronogramaPratica);
    if (c.getPraticaList() != null) {
      psCronogramaPratica.setLong(1, c.getId());
      psCronogramaPratica.setLong(2, c.getPraticaList().get(0).getId());
      psCronogramaPratica.execute();
    }
    psCronogramaPratica.close();
    return true;
  }

  @Override
  public boolean insertGestor(Connection conn, CronogramaAnual c) throws Exception {
    String sqlCronogramaGestor = "INSERT INTO cronograma_gestor(cronograma_fk, gestor_fk) VALUES (?, ?);";
    PreparedStatement psCronogramaGestor = conn.prepareStatement(sqlCronogramaGestor);
    if (c.getGestorList() != null) {
      psCronogramaGestor.setLong(1, c.getId());
      psCronogramaGestor.setLong(2, c.getGestorList().get(0).getId());
      psCronogramaGestor.execute();
    }
    psCronogramaGestor.close();
    return true;
  }

}
