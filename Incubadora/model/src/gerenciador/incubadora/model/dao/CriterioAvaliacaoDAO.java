package gerenciador.incubadora.model.dao;

import gerenciador.incubadora.model.base.BaseDAO;
import gerenciador.incubadora.model.entity.CriterioAvaliacao;
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
public class CriterioAvaliacaoDAO implements BaseDAO<CriterioAvaliacao> {

    public static final String CRITERION_EIXO_ID = "1";
    public static final String CRITERION_NOME_EQ = "2";

    @Override
    public void create(CriterioAvaliacao e, Connection conn) throws Exception {
        String sql = "INSERT INTO criterio_avaliacao(nome, ativo, eixo_fk) VALUES (?, ?, ?) RETURNING id;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, e.getNome());
        ps.setBoolean(++i, e.getAtivo());
        ps.setLong(++i, e.getEixo().getId());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            e.setId(rs.getLong("id"));
        }
        rs.close();
        ps.close();
    }

    @Override
    public CriterioAvaliacao readById(Long id, Connection conn) throws Exception {
        String sql = "SELECT ca.*, e.id as eixo_id, e.nome as eixo_nome from criterio_avaliacao ca LEFT JOIN eixo e ON e.id = ca.eixo_fk WHERE ca.id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        CriterioAvaliacao criterioAvaliacao = null;
        if (rs.next()) {
            criterioAvaliacao = new CriterioAvaliacao();
            criterioAvaliacao.setId(rs.getLong("id"));
            criterioAvaliacao.setNome(rs.getString("nome"));
            criterioAvaliacao.setAtivo(rs.getBoolean("ativo"));

            Eixo eixo = new Eixo();
            eixo.setId(rs.getLong("eixo_id"));
            eixo.setNome(rs.getString("eixo_nome"));

            criterioAvaliacao.setEixo(eixo);
        }
        rs.close();
        ps.close();

        return criterioAvaliacao;
    }

    @Override
    public List<CriterioAvaliacao> readByCriteria(Map<String, Object> criteria, Connection conn) throws Exception {
        List<CriterioAvaliacao> criterioAvaliacaoList = new ArrayList<CriterioAvaliacao>();
        String sql = "SELECT ca.*, e.id as eixo_id, e.nome as eixo_nome from criterio_avaliacao ca LEFT JOIN eixo e ON e.id = ca.eixo_fk WHERE 1=1";
        Statement s = conn.createStatement();

        if (criteria != null) {
            String criterionNomeEq = (String) criteria.get(CRITERION_NOME_EQ);
            if (criterionNomeEq != null && !criterionNomeEq.isEmpty()) {
                sql += " AND ca.nome ='" + criterionNomeEq + "'";
            }

            Long criterionEixoId = (Long) criteria.get(CRITERION_EIXO_ID);
            if (criterionEixoId != null) {
                sql += " AND e.id =" + criterionEixoId;
            }

        }

        ResultSet rs = s.executeQuery(sql);
        CriterioAvaliacao criterioAvaliacao = null;
        while (rs.next()) {
            criterioAvaliacao = new CriterioAvaliacao();
            criterioAvaliacao.setId(rs.getLong("id"));
            criterioAvaliacao.setNome(rs.getString("nome"));
            criterioAvaliacao.setAtivo(rs.getBoolean("ativo"));

            Eixo eixo = new Eixo();
            eixo.setId(rs.getLong("eixo_id"));
            eixo.setNome(rs.getString("eixo_nome"));

            criterioAvaliacao.setEixo(eixo);

            criterioAvaliacaoList.add(criterioAvaliacao);
        }

        rs.close();
        s.close();
        return criterioAvaliacaoList;
    }

    @Override
    public void update(CriterioAvaliacao e, Connection conn) throws Exception {
        String sql = "UPDATE criterio_avaliacao SET nome=?, ativo=?, eixo_fk=? WHERE id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, e.getNome());
        ps.setBoolean(++i, e.getAtivo());
        ps.setLong(++i, e.getEixo().getId());
        ps.setLong(++i, e.getId());
        ps.execute();
        ps.close();
    }

    @Override
    public void delete(Long id, Connection conn) throws Exception {
        String sql = "DELETE FROM criterio_avaliacao WHERE id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ps.execute();
        ps.close();
    }

}
