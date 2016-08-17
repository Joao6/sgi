package gerenciador.incubadora.model.service;

import gerenciador.incubadora.model.ConnectionManager;
import gerenciador.incubadora.model.base.service.BaseUsuarioService;
import gerenciador.incubadora.model.dao.UsuarioDAO;
import gerenciador.incubadora.model.entity.Usuario;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsuarioService implements BaseUsuarioService {

    private static final String ENCODE_UTF_8 = "UTF-8";
    private static final String ENCODE_ISO_8859_1 = "ISO-8859-1";

    @Override
    public Usuario login(String usuario, String senha) throws Exception {

        Usuario usuarioLogado = null;
        Map<String, Object> criteria = new HashMap<>();
        criteria.put(UsuarioDAO.CRITERION_USUARIO_EQ, usuario);
        criteria.put(UsuarioDAO.CRITERION_SENHA_EQ, senha);

        List<Usuario> usuarioList = readByCriteria(criteria);

        if (usuarioList != null && usuarioList.size() == 1) {
            Usuario aux = usuarioList.get(0);
            if (aux.getEmail().equals(usuario)) {
                if (aux.getSenha().equals(senha)) {
                    usuarioLogado = aux;
                }
            }
        }
        return usuarioLogado;
    }

    @Override
    public void create(Usuario e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            this.getCodedEntity(e);
            UsuarioDAO dao = new UsuarioDAO();
            Map<String, Object> criteria = new HashMap<>();
            criteria.put(UsuarioDAO.CRITERION_EMAIL_EQ, e.getEmail());

            UsuarioService us = new UsuarioService();
            List<Usuario> uList = us.readByCriteria(criteria);
            if (uList == null || uList.isEmpty()) {
                dao.create(e, conn);
                conn.commit();
            }
            conn.close();
        } catch (Exception ex) {
            conn.rollback();
            conn.close();
            throw ex;
        }
    }

    @Override
    public Usuario readById(Long id) throws Exception {
        Usuario usuario = null;
        Connection conn = ConnectionManager.getInstance().getConnection();
        UsuarioDAO dao = new UsuarioDAO();
        usuario = dao.readById(id, conn);
        conn.close();
        return usuario;
    }

    @Override
    public List<Usuario> readByCriteria(Map<String, Object> criteria) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        UsuarioDAO dao = new UsuarioDAO();
        List<Usuario> usuarioList = dao.readByCriteria(criteria, conn);
        conn.close();
        return usuarioList;
    }

    @Override
    public void update(Usuario e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            this.getCodedEntity(e);
            UsuarioDAO dao = new UsuarioDAO();
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
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.delete(id, conn);
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            conn.rollback();
            conn.close();
            throw ex;
        }
    }

    @Override
    public void getCodedEntity(Usuario usuario) throws Exception {
        String nomeUsuario = usuario.getNome();
        int endIndex = nomeUsuario.indexOf("");
        if (endIndex != -1) {
            nomeUsuario = nomeUsuario.subSequence(0, endIndex).toString();
        }
        //ENCODE
        nomeUsuario = new String(nomeUsuario.getBytes(ENCODE_ISO_8859_1), ENCODE_UTF_8);
        String nome = new String(usuario.getNome().getBytes(ENCODE_ISO_8859_1), ENCODE_UTF_8);
    //UPDATE 
        //usuario.setNomeUsuario(nomeUsuario);
        usuario.setNome(nome);
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
