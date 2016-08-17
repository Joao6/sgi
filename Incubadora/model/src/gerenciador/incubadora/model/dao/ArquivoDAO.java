/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.incubadora.model.dao;

import gerenciador.incubadora.model.entity.ArquivoPratica;
import gerenciador.incubadora.model.entity.ArquivoProcesso;
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
public class ArquivoDAO {
   
   public static final String CRITERION_PATH_ILIKE = "1";

   public void create(ArquivoProcesso e, Connection conn) throws Exception {
      String sql = "INSERT INTO arquivo(path)VALUES (?) RETURNING id;";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setString(1, e.getPath());
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
         e.setId(rs.getLong("id"));
      }
      rs.close();
      ps.close();
   }

   public ArquivoPratica readPraticaById(Long id, Connection conn) throws Exception {
      ArquivoPratica ap = null;
      String sql = "SELECT * FROM arquivo_pratica WHERE id=?";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setLong(1, id);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
         ap = new ArquivoPratica(rs.getString("path_arquivo"));
      }
      rs.close();
      ps.close();
      return ap;
   }

   public List<ArquivoPratica> readByCriteria(Map<String, Object> criteria, Connection conn) throws Exception {
      List<ArquivoPratica> arquivoPraticaList = new ArrayList<>();
      String sql = "SELECT * FROM arquivo_pratica WHERE 1=1  ";
      
      if(criteria != null){
         String criterionPathIlike = (String)criteria.get(CRITERION_PATH_ILIKE);
         if(criterionPathIlike != null && !criterionPathIlike.isEmpty()){
            sql += " AND  path_arquivo ilike'" + criterionPathIlike + "'";
         }
      }
      
      PreparedStatement ps = conn.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
         ArquivoPratica ap = new ArquivoPratica(rs.getString("path_arquivo"));
         arquivoPraticaList.add(ap);
      }
      rs.close();
      ps.close();
      return arquivoPraticaList;
   }

   public void update(ArquivoProcesso e, Connection conn) throws Exception {
   }

   public void delete(Long id, Connection conn) throws Exception {
      String sql = "DELETE FROM arquivo_pratica WHERE id=?";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setLong(1, id);
      ps.execute();
   }

}
