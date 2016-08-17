package gerenciador.incubadora.model.service;

import gerenciador.incubadora.model.ConnectionManager;
import gerenciador.incubadora.model.ErrorMessage;
import gerenciador.incubadora.model.base.service.BaseAvaliadorService;
import gerenciador.incubadora.model.dao.AvaliadorDAO;
import gerenciador.incubadora.model.dao.UsuarioDAO;
import gerenciador.incubadora.model.entity.Avaliador;
import gerenciador.incubadora.model.entity.Usuario;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Rafael-pc
 */
public class AvaliadorService implements BaseAvaliadorService {

   @Override
   public void create(Avaliador e) throws Exception {
      Connection conn = ConnectionManager.getInstance().getConnection();
      try {
         AvaliadorDAO dao = new AvaliadorDAO();
         Map<String, Object> criteria = new HashMap<>();
         criteria.put(UsuarioDAO.CRITERION_EMAIL_EQ, e.getEmail());
         UsuarioDAO uDao = new UsuarioDAO();
         
         List<Usuario> usuarioList = uDao.readByCriteria(criteria, conn);
         if (usuarioList.isEmpty()) {
            dao.create(e, conn);
         }else if(usuarioList.size() == 1){
            e.setId(usuarioList.get(0).getId());
            dao.SetGestorHowAvaliador(e, conn);
         }

         conn.commit();
         conn.close();
      } catch (Exception ex) {
         conn.rollback();
         conn.close();
         throw ex;
      }
   }

   @Override
   public Avaliador readById(Long id) throws Exception {
      Connection conn = ConnectionManager.getInstance().getConnection();
      AvaliadorDAO dao = new AvaliadorDAO();
      Avaliador avaliador = dao.readById(id, conn);
      conn.close();
      return avaliador;
   }

   @Override
   public List<Avaliador> readByCriteria(Map<String, Object> criteria) throws Exception {
      Connection conn = ConnectionManager.getInstance().getConnection();
      AvaliadorDAO dao = new AvaliadorDAO();
      List<Avaliador> avaliadorList = dao.readByCriteria(criteria, conn);
      conn.close();
      return avaliadorList;
   }

   @Override
   public void update(Avaliador e) throws Exception {
      Connection conn = ConnectionManager.getInstance().getConnection();
      try {
         AvaliadorDAO dao = new AvaliadorDAO();
         dao.update(e, conn);
         conn.commit();
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
         AvaliadorDAO dao = new AvaliadorDAO();
         dao.delete(id, conn);
         conn.commit();
         conn.close();
      } catch (Exception ex) {
         conn.rollback();
         conn.close();
         throw ex;
      }
   }

   @Override
   public Map<String, String> validateForCreate(Map<String, Object> fields) throws Exception {
      Map<String, String> errors = new HashMap<>();
      String nome = (String) fields.get("nome");
      if (nome == null || nome.isEmpty()) {
         errors.put("nome", ErrorMessage.CAMPO_OBRIGATORIO);
      }

      String sobrenome = (String) fields.get("sobrenome");
      if (sobrenome == null || sobrenome.isEmpty()) {
         errors.put("sobrenome", ErrorMessage.CAMPO_OBRIGATORIO);
      }

      String email = (String) fields.get("email");
      if (email == null || email.isEmpty()) {
         errors.put("email", ErrorMessage.CAMPO_OBRIGATORIO);
      }

      String senha = (String) fields.get("senha");
      if (senha == null || senha.isEmpty()) {
         errors.put("senha", ErrorMessage.CAMPO_OBRIGATORIO);
      } else {
         if (senha.length() < ErrorMessage.TAMANHO_MINIMO_SENHA) {
            errors.put("senha", ErrorMessage.SENHA_CURTA);
         }
      }

      String cpf = (String) fields.get("cpf");
      if (cpf == null || cpf.isEmpty()) {
         errors.put("cpf", ErrorMessage.CAMPO_OBRIGATORIO);
      }

      return errors;
   }

   @Override
   public Map<String, String> validateForUpdate(Map<String, Object> fields) throws Exception {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

}
