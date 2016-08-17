package gerenciador.incubadora.model.dao;

import gerenciador.incubadora.model.base.BaseDAO;
import gerenciador.incubadora.model.entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UsuarioDAO implements BaseDAO<Usuario> {

    public static final String CRITERION_USUARIO_EQ = "1";
    public static final String CRITERION_SENHA_EQ = "2";
    public static final String CRITERION_EMAIL_EQ = "3";

    @Override
    public void create(Usuario e, Connection conn) throws Exception {
        String sql = "INSERT INTO usuario(nome,email,senha,telefone,tipo_usuario,sobrenome)VALUES (?, ?, ?, ?,?,?) RETURNING id;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, e.getNome());
        ps.setString(++i, e.getEmail());
        ps.setString(++i, e.getSenha());
        ps.setString(++i, e.getTelefone());
        ps.setString(++i, Usuario.TIPO_USUARIO_INCUBADORA);
        ps.setString(++i, e.getSobrenome());
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            e.setId(rs.getLong("id"));
        }
        rs.close();
        ps.close();
    }

    @Override
    public Usuario readById(Long id, Connection conn) throws Exception {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            usuario = new Usuario();
            usuario.setId(rs.getLong("id"));
            usuario.setNome(rs.getString("nome"));
            usuario.setSobrenome(rs.getString("sobrenome"));
            usuario.setEmail(rs.getString("email"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setTelefone(rs.getString("telefone"));
            usuario.setTipoUsuario(rs.getString("tipo_usuario"));
        }
        rs.close();
        ps.close();
        return usuario;
    }

    @Override
    public List<Usuario> readByCriteria(Map<String, Object> criteria, Connection conn) throws Exception {
        List<Usuario> usuarioList = new ArrayList<>();
        String sql = "SELECT * FROM usuario WHERE 1=1 ";

        String criterionUsuarioEq = (String) criteria.get(CRITERION_USUARIO_EQ);
        if (criterionUsuarioEq != null && !criterionUsuarioEq.isEmpty()) {
            sql += " AND email='" + criterionUsuarioEq + "'";
        }

        String criterionSenhaEq = (String) criteria.get(CRITERION_SENHA_EQ);
        if (criterionSenhaEq != null && !criterionSenhaEq.isEmpty()) {
            sql += " AND senha='" + criterionSenhaEq + "'";
        }

        String criterionEmailEq = (String) criteria.get(CRITERION_EMAIL_EQ);
        if (criterionEmailEq != null && !criterionEmailEq.isEmpty()) {
            sql += " AND email='" + criterionEmailEq + "'";
        }

        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(sql);
        while (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(rs.getLong("id"));
            usuario.setNome(rs.getString("nome"));
            usuario.setSobrenome(rs.getString("sobrenome"));
            usuario.setEmail(rs.getString("email"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setTelefone(rs.getString("telefone"));
            usuario.setTipoUsuario(rs.getString("tipo_usuario"));
            usuarioList.add(usuario);
        }

        rs.close();
        s.close();
        return usuarioList;
    }

    @Override
    public void update(Usuario e, Connection conn) throws Exception {
        String sql = "UPDATE usuario SET nome=?,email=?,senha=?,telefone=?,tipo_usuario=?, sobrenome=? WHERE id=? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, e.getNome());
        ps.setString(++i, e.getEmail());
        ps.setString(++i, e.getSenha());
        ps.setString(++i, e.getTelefone());
        ps.setString(++i, e.getTipoUsuario());
        ps.setString(++i, e.getSobrenome());
        ps.setLong(++i, e.getId());
        ps.execute();
        ps.close();
    }

    @Override
    public void delete(Long id, Connection conn) throws Exception {
        String sql = "DELETE FROM usuario WHERE id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ps.execute();
        ps.close();
    }

}
