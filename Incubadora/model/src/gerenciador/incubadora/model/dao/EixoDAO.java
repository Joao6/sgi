package gerenciador.incubadora.model.dao;

import gerenciador.incubadora.model.base.BaseDAO;
import gerenciador.incubadora.model.entity.Eixo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Rafael
 */
public class EixoDAO implements BaseDAO<Eixo> {

    public static final String CRITERION_NOME_EQ = "1";

    @Override
    public void create(Eixo e, Connection conn) throws Exception {
        String sql = "INSERT INTO eixo(nome, peso) VALUES (?, ?) RETURNING id;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, e.getNome());
        ps.setDouble(2, e.getPeso());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            e.setId(rs.getLong("id"));
        }
        rs.close();
        ps.close();
    }

    @Override
    public Eixo readById(Long id, Connection conn) throws Exception {
        String sql = "SELECT * from eixo WHERE id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        Eixo eixo = null;
        if (rs.next()) {
            eixo = new Eixo();
            eixo.setId(id);
            eixo.setNome(rs.getString("nome"));
            eixo.setPeso(rs.getDouble("peso"));
        }
        rs.close();
        ps.close();

        return eixo;
    }

    @Override
    public List<Eixo> readByCriteria(Map<String, Object> criteria, Connection conn) throws Exception {
        List<Eixo> eixoList = new ArrayList<Eixo>();
        String sql = "SELECT * from eixo WHERE 1=1";
        Statement s = conn.createStatement();

        if (criteria != null) {
            String criterionNomeEq = (String) criteria.get(CRITERION_NOME_EQ);
            if (criterionNomeEq != null && !criterionNomeEq.isEmpty()) {
                sql += " AND nome ='" + criterionNomeEq + "'";
            }

        }

        ResultSet rs = s.executeQuery(sql);
        Eixo eixo = null;
        while (rs.next()) {
            eixo = new Eixo();
            eixo.setId(rs.getLong("id"));
            eixo.setNome(rs.getString("nome"));
            eixo.setPeso(rs.getDouble("peso"));
            eixoList.add(eixo);
        }
        rs.close();
        s.close();

        return eixoList;
    }

    @Override
    public void update(Eixo e, Connection conn) throws Exception {
        String sql = "UPDATE eixo SET nome=?, peso=? WHERE id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, e.getNome());
        ps.setDouble(2, e.getPeso());
        ps.setLong(3, e.getId());
        ps.execute();
        ps.close();
    }

    @Override
    public void delete(Long id, Connection conn) throws Exception {
        String sql = "DELETE FROM eixo WHERE id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ps.execute();
        ps.close();
    }

}
