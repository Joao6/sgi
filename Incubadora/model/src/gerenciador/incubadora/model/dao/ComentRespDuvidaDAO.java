package gerenciador.incubadora.model.dao;

import gerenciador.incubadora.model.base.BaseDAO;
import gerenciador.incubadora.model.entity.ComentarioRespostaDuvida;
import gerenciador.incubadora.model.entity.Duvida;
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
 * @author William
 */
public class ComentRespDuvidaDAO implements BaseDAO<ComentarioRespostaDuvida> {

   @Override
   public void create(ComentarioRespostaDuvida e, Connection conn) throws Exception {
      String sql = "INSERT INTO comentario_resp_duvida (comentario, data_hora, duvida_fk) VALUES(?,?,?) RETURNING id";
      PreparedStatement ps = conn.prepareStatement(sql);
      int i = 0;
      ps.setString(++i, e.getComentario());
      ps.setTimestamp(++i, new Timestamp(e.getDataHora().getTime()));
      ps.setLong(++i, e.getDuvida().getId());

      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
         e.setId(rs.getLong("id"));
      }
      rs.close();
      ps.close();

   }

   @Override
   public ComentarioRespostaDuvida readById(Long id, Connection conn) throws Exception {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public List<ComentarioRespostaDuvida> readByCriteria(Map<String, Object> criteria, Connection conn) throws Exception {
      List<ComentarioRespostaDuvida> comentRespDuvidaList = new ArrayList<>();
      String sql = "SELECT * FROM comentario_resp_duvida WHERE 1=1 ";
      
      if(criteria != null){
         //todo critérios
      }
      
      Statement s = conn.createStatement();
      ResultSet rs = s.executeQuery(sql);
      
      while(rs.next()){
         ComentarioRespostaDuvida crd = new ComentarioRespostaDuvida();
         crd.setId(rs.getLong("id"));
         crd.setComentario(rs.getString("comentario"));
         crd.setDataHora(rs.getDate("dataHora"));
        
         Usuario usuario = new Usuario();
         usuario.setId(rs.getLong("usuario_fk"));
         
         Duvida duvida = new Duvida();
         duvida.setId(rs.getLong("duvida_dk"));
         
         crd.setUsuario(usuario);
         crd.setDuvida(duvida);
         
         comentRespDuvidaList.add(crd);
      }
      rs.close();
      s.close();
      
      return comentRespDuvidaList;
   }

   @Override
   public void update(ComentarioRespostaDuvida e, Connection conn) throws Exception {
      String sql = "UPDATE comentario_resp_duvida SET (comentario, data_hora, usuario_fk, duvida_fk) VALUES (?,?,?,?)";
      PreparedStatement ps = conn.prepareStatement(sql);
      int i = 0;
      ps.setString(++i, e.getComentario());
      ps.setTimestamp(++i, new Timestamp(e.getDataHora().getTime()));
      ps.setLong(++i, e.getUsuario().getId());
      ps.setLong(++i, e.getDuvida().getId());
      ps.execute();
   }

   @Override
   public void delete(Long id, Connection conn) throws Exception {
      String sql = "DELETE FROM comentario_resp_duvida WHERE id = ?";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setLong(1, id);
      ps.execute();
   }

}
