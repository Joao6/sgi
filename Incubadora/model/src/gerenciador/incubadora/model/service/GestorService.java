package gerenciador.incubadora.model.service;

import gerenciador.incubadora.model.ConnectionManager;
import gerenciador.incubadora.model.base.service.BaseGestorService;
import gerenciador.incubadora.model.dao.GestorDAO;
import gerenciador.incubadora.model.dao.UsuarioDAO;
import gerenciador.incubadora.model.entity.Gestor;
import gerenciador.incubadora.model.entity.Usuario;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestorService implements BaseGestorService {

   private static final String ENCODE_UTF_8 = "UTF-8";
   private static final String ENCODE_ISO_8859_1 = "ISO-8859-1";

   @Override
   public void create(Gestor e) throws Exception {
      Connection conn = ConnectionManager.getInstance().getConnection();
      try {
         UsuarioDAO uDao = new UsuarioDAO();

         Map<String, Object> criteria = new HashMap<>();
         criteria.put(UsuarioDAO.CRITERION_EMAIL_EQ, e.getEmail());

         UsuarioService us = new UsuarioService();
         List<Usuario> uList = us.readByCriteria(criteria);
         if (uList == null || uList.isEmpty()) {
            uDao.create(e, conn);
            conn.commit();

            GestorDAO iDao = new GestorDAO();
            iDao.create(e, conn);
            conn.commit();
         }else{
            throw new Exception("Email já existe");
         }
                  

         conn.close();
      } catch (Exception ex) {
         conn.rollback();
         conn.close();
         throw ex;
      }
   }

   @Override
   public Gestor readById(Long id) throws Exception {
      Connection conn = ConnectionManager.getInstance().getConnection();
      GestorDAO dao = new GestorDAO();
      Gestor incubadora = dao.readById(id, conn);
      conn.close();
      return incubadora;
   }

   @Override
   public List<Gestor> readByCriteria(Map<String, Object> criteria) throws Exception {
      Connection conn = ConnectionManager.getInstance().getConnection();
      GestorDAO dao = new GestorDAO();
      List<Gestor> incubadoraList = dao.readByCriteria(criteria, conn);
      conn.close();
      return incubadoraList;
   }

   @Override
   public void update(Gestor e) throws Exception {
      Connection conn = ConnectionManager.getInstance().getConnection();
      try {
         GestorDAO dao = new GestorDAO();

         Map<String, Object> criteria = new HashMap<>();
         criteria.put(UsuarioDAO.CRITERION_EMAIL_EQ, e.getEmail());

         UsuarioService us = new UsuarioService();
         List<Usuario> uList = us.readByCriteria(criteria);
         if (uList == null || uList.isEmpty()) {
            dao.update(e, conn);
            conn.commit();
         } 
         else {
            Long id = uList.get(0).getId();
            if (id.equals(e.getId())) {
               dao.update(e, conn);
               conn.commit();
            } else {
               throw new Exception();
            }
         }

         conn.close();
      } catch (Exception ex) {
         conn.rollback();
         conn.close();
         throw ex;
      }
   }

   @Override
   public void delete(Long id) throws Exception {
      Connection conn = ConnectionManager.getInstance().getConnection();
      try {
         UsuarioDAO uDao = new UsuarioDAO();
         uDao.delete(id, conn);
         conn.commit();
         conn.close();
      } catch (Exception ex) {
         conn.rollback();
         conn.close();
         throw ex;
      }
   }

   @Override
   public void getCodedEntity(Gestor incubadora) throws Exception {
      String nomeUsuario = incubadora.getNome();
      int endIndex = nomeUsuario.indexOf(" ");
      if (endIndex != -1) {
         nomeUsuario = nomeUsuario.subSequence(0, endIndex).toString();
      }
      //Encode to UTF-8
      String nome = new String(incubadora.getNome().getBytes(ENCODE_ISO_8859_1), ENCODE_UTF_8);
      nomeUsuario = new String(nomeUsuario.getBytes(ENCODE_ISO_8859_1), ENCODE_UTF_8);
      String email = new String(incubadora.getEmail().getBytes(ENCODE_ISO_8859_1), ENCODE_UTF_8);
      String senha = new String(incubadora.getSenha().getBytes(ENCODE_ISO_8859_1), ENCODE_UTF_8);
      //Update Data
      incubadora.setNome(nome);
      //incubadora.setNomeUsuario(nomeUsuario);
      incubadora.setEmail(email);
      incubadora.setSenha(senha);

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
