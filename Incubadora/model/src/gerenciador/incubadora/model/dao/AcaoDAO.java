package gerenciador.incubadora.model.dao;

import gerenciador.incubadora.model.base.BaseDAO;
import gerenciador.incubadora.model.entity.Categoria;
import gerenciador.incubadora.model.entity.Empreendimento;
import gerenciador.incubadora.model.entity.Orientacao;
import gerenciador.incubadora.model.entity.Acao;
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
public class AcaoDAO implements BaseDAO<Acao> {

    private static final String ATRIBUTOS_READ_BY_ID = "*";
    private static final String ATRIBUTOS_READ_BY_CRITERIA = "*";

    @Override
    public void create(Acao e, Connection conn) throws Exception {
        String sql = "INSERT INTO acao(nome, responsavel, data_inicio,data_fim, categoria_fk, empreendimento_fk)VALUES (?, ?, ?, ?, ?, ?) RETURNING id;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, e.getNome());
        ps.setString(++i, e.getResponsavel());
        ps.setDate(++i, e.getDataInicio());
        ps.setDate(++i, e.getDataFim());
        ps.setLong(++i, e.getCategoria().getId());
        ps.setLong(++i, e.getEmpreendimento().getId());

        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            e.setId(rs.getLong("id"));
        }
        rs.close();
        ps.close();
    }

    @Override
    public Acao readById(Long id, Connection conn) throws Exception {
        String sql = "SELECT " + ATRIBUTOS_READ_BY_ID + " FROM acao_read_by_id_view WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();

        Acao planejamento = new Acao();
        if (rs.next()) {
            planejamento.setId(rs.getLong("id"));
            planejamento.setNome(rs.getString("nome"));
            planejamento.setResponsavel(rs.getString("responsavel"));
            planejamento.setDataInicio(rs.getDate("data_inicio"));
            planejamento.setDataFim(rs.getDate("data_fim"));

            //Categoria 
            Categoria categoria = new Categoria();
            categoria.setId(rs.getLong("ca_id"));
            categoria.setNome(rs.getString("ca_nome"));

            //Empreendimento
            Empreendimento empreendimento = new Empreendimento();
            empreendimento.setId(rs.getLong("empreendimento_fk"));

            planejamento.setCategoria(categoria);

        }
        rs.close();
        ps.close();

        return planejamento;
    }

    @Override
    public List<Acao> readByCriteria(Map<String, Object> criteria, Connection conn) throws Exception {
        String sql = "SELECT " + ATRIBUTOS_READ_BY_CRITERIA + " FROM acao_read_by_id_view WHERE 1=1 ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<Acao> acaoList = new ArrayList<>();
        while (rs.next()) {
            Acao planejamento = new Acao();
            planejamento.setId(rs.getLong("id"));
            planejamento.setNome(rs.getString("nome"));
            planejamento.setResponsavel(rs.getString("responsavel"));
            planejamento.setDataInicio(rs.getDate("data_inicio"));
            planejamento.setDataFim(rs.getDate("data_fim"));

            //orientacao
            Orientacao orientacao = new Orientacao();
            orientacao.setId(rs.getLong("o_id"));
            orientacao.setDescricao(rs.getString("descricao"));

            //Categoria
            Categoria categoria = new Categoria();
            categoria.setId(rs.getLong("ca_id"));
            categoria.setNome(rs.getString("ca_nome"));

            planejamento.setCategoria(categoria);

            acaoList.add(planejamento);
        }
        rs.close();
        ps.close();
        return acaoList;
    }

    @Override
    public void update(Acao e, Connection conn) throws Exception {
        String sql = "UPDATE acao SET nome=?, responsavel=?, data_inicio=?, "
                + "data_fim=?, categoria_fk=? WHERE id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, e.getNome());
        ps.setString(++i, e.getResponsavel());
        ps.setDate(++i, e.getDataInicio());
        ps.setDate(++i, e.getDataFim());
        ps.setLong(++i, e.getCategoria().getId());
        ps.setLong(++i, e.getId());

        ps.execute();
        ps.close();
    }

    @Override
    public void delete(Long id, Connection conn) throws Exception {
        String sql = "DELETE FROM acao WHERE id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ps.execute();
        ps.close();
    }

}
