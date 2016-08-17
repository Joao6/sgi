/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.incubadora.model.service;

import gerenciador.incubadora.model.ConnectionManager;
import gerenciador.incubadora.model.base.service.BaseEvolucaoService;
import gerenciador.incubadora.model.dao.EstagioEvolucaoDAO;
import gerenciador.incubadora.model.entity.EstagioEvolucao;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author William
 */
public class EstagioEvolucaoService implements BaseEvolucaoService {

  @Override
  public void create(EstagioEvolucao e) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public EstagioEvolucao readById(Long id) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public List<EstagioEvolucao> readByCriteria(Map<String, Object> criteria) throws Exception {
    Connection conn = ConnectionManager.getInstance().getConnection();
    EstagioEvolucaoDAO dao = new EstagioEvolucaoDAO();
    List<EstagioEvolucao> estagioList = dao.readByCriteria(criteria, conn);
    conn.close();
    return estagioList;
  }

  @Override
  public void update(EstagioEvolucao e) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void delete(Long id) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

    @Override
    public Map<String, String> validateForCreate(Map<String, Object> fields) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, String> validateForUpdate(Map<String, Object> fields) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
