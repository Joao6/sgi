package gerenciador.incubadora.model.dao;

import gerenciador.incubadora.model.base.BaseDAO;
import gerenciador.incubadora.model.entity.ApresentacaoNegocio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Rafael
 */
public class ApresentacaoNegocioDAO implements BaseDAO<ApresentacaoNegocio> {

    @Override
    public void create(ApresentacaoNegocio e, Connection conn) throws Exception {
        String sql = "INSERT INTO apresentacao_negocio(empreendimento_fk, curriculo, disponibilidade, inovacao_produto, tempo_desenvolvimento, identificacao, mercado_alvo, vantagem_competitiva, "
                + " investimento, retorno, parcerias, estrutura_organizacional) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setLong(++i, e.getEmpreendimento().getId());
        ps.setString(++i, e.getMiniCurriculo());
        ps.setString(++i, e.getDisponibilidade());
        ps.setString(++i, e.getInovacaoProduto());
        ps.setString(++i, e.getTempoDesenvolvimento());
        ps.setString(++i, e.getIdentificacao());
        ps.setString(++i, e.getMercadoAlvo());
        ps.setString(++i, e.getVantagemCompetitiva());
        ps.setString(++i, e.getInvestimento());
        ps.setString(++i, e.getRetorno());
        ps.setString(++i, e.getParcerias());
        ps.setString(++i, e.getEstruturaOrganizacional());
        ps.execute();
        ps.close();

    }

    @Override
    public ApresentacaoNegocio readById(Long id, Connection conn) throws Exception {
        String sql = "SELECT * FROM apresentacao_negocio WHERE empreendimento_fk=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);

        ResultSet rs = ps.executeQuery();
        ApresentacaoNegocio apresentacaoNegocio = null;
        if (rs.next()) {
            apresentacaoNegocio = new ApresentacaoNegocio();
            apresentacaoNegocio.setMiniCurriculo(rs.getString("curriculo"));
            apresentacaoNegocio.setDisponibilidade(rs.getString("disponibilidade"));
            apresentacaoNegocio.setInovacaoProduto(rs.getString("inovacao_produto"));
            apresentacaoNegocio.setTempoDesenvolvimento(rs.getString("tempo_desenvolvimento"));
            apresentacaoNegocio.setIdentificacao(rs.getString("identificacao"));
            apresentacaoNegocio.setMercadoAlvo(rs.getString("mercado_alvo"));
            apresentacaoNegocio.setVantagemCompetitiva(rs.getString("vantagem_competitiva"));
            apresentacaoNegocio.setInvestimento(rs.getString("investimento"));
            apresentacaoNegocio.setRetorno(rs.getString("retorno"));
            apresentacaoNegocio.setParcerias(rs.getString("parcerias"));
            apresentacaoNegocio.setEstruturaOrganizacional(rs.getString("estrutura_organizacional"));
            EmpreendimentoDAO empreendimentoDAO = new EmpreendimentoDAO();
            apresentacaoNegocio.setEmpreendimento(empreendimentoDAO.readById(rs.getLong("empreendimento_fk"), conn));
        }

        rs.close();
        ps.close();

        return apresentacaoNegocio;
    }

    @Override
    public List<ApresentacaoNegocio> readByCriteria(Map<String, Object> criteria, Connection conn) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(ApresentacaoNegocio e, Connection conn) throws Exception {
        String sql = "UPDATE apresentacao_negocio SET curriculo=?, disponibilidade=?, inovacao_produto=?, tempo_desenvolvimento=?, identificacao=?, mercado_alvo=?, vantagem_competitiva=?, "
                + " investimento=?, retorno=?, parcerias=?, estrutura_organizacional=? WHERE empreendimento_fk=?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, e.getMiniCurriculo());
        ps.setString(++i, e.getDisponibilidade());
        ps.setString(++i, e.getInovacaoProduto());
        ps.setString(++i, e.getTempoDesenvolvimento());
        ps.setString(++i, e.getIdentificacao());
        ps.setString(++i, e.getMercadoAlvo());
        ps.setString(++i, e.getVantagemCompetitiva());
        ps.setString(++i, e.getInvestimento());
        ps.setString(++i, e.getRetorno());
        ps.setString(++i, e.getParcerias());
        ps.setString(++i, e.getEstruturaOrganizacional());
        ps.setLong(++i, e.getEmpreendimento().getId());
        ps.execute();
        ps.close();
    }

    @Override
    public void delete(Long id, Connection conn) throws Exception {
        String sql = "DELETE FROM apresentacao_negocio WHERE empreendimento_fk=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ps.execute();
        ps.close();
    }

}
