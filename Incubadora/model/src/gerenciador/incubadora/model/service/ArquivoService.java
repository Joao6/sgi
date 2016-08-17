/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.incubadora.model.service;

import gerenciador.incubadora.model.ConnectionManager;
import gerenciador.incubadora.model.ServiceLocator;
import gerenciador.incubadora.model.dao.ArquivoDAO;
import gerenciador.incubadora.model.entity.ArquivoPratica;
import gerenciador.incubadora.model.entity.ArquivoProcesso;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author William
 */
public class ArquivoService {


  public void create(ArquivoProcesso e) throws Exception {
    Connection conn = ConnectionManager.getInstance().getConnection();
    try {
      ArquivoDAO dao = new ArquivoDAO();
      dao.create(e, conn);
      conn.commit();
      conn.close();
    } catch (Exception ex) {
      conn.rollback();
      conn.close();
      throw ex;
    }
  }


  public ArquivoPratica readArquivoPraticaById(Long id) throws Exception {
     Connection conn = ConnectionManager.getInstance().getConnection();
     ArquivoDAO dao = new ArquivoDAO();
     ArquivoPratica ap = dao.readPraticaById(id, conn);
     conn.close();
     return ap;
  }


  public List<ArquivoPratica> readByCriteria(Map<String, Object> criteria) throws Exception {
    Connection conn = ConnectionManager.getInstance().getConnection();
    List<ArquivoPratica> arquivoPratica = new ArquivoDAO().readByCriteria(criteria, conn);
    conn.close();
    return arquivoPratica;
  }


  public void delete(Long id) throws Exception {
     Connection conn = ConnectionManager.getInstance().getConnection();
     try {
        ArquivoDAO dao = new ArquivoDAO();
        dao.delete(id, conn);;
        conn.commit();
        conn.close();
     } catch (Exception e) {
        conn.rollback();
        conn.close();
        throw e;
     }
  }


  
}
