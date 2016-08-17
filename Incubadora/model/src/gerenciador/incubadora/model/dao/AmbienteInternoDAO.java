package gerenciador.incubadora.model.dao;

import gerenciador.incubadora.model.base.BaseDAO;
import gerenciador.incubadora.model.entity.AmbienteInterno;
import gerenciador.incubadora.model.entity.Empreendimento;
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
public class AmbienteInternoDAO implements BaseDAO<AmbienteInterno> {

    @Override
    public void create(AmbienteInterno e, Connection conn) throws Exception {
        String sql = "INSERT INTO ambiente_interno(capital_forte, gestao_forte, empreendedor_forte, mercado_forte, "
                + "tecnologico_forte, capital_fraco, gestao_fraco, empreendedor_fraco, "
                + "mercado_fraco, tecnologico_fraco, empreendimento_fk) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, e.getCapitalForte());
        ps.setString(++i, e.getGestaoForte());
        ps.setString(++i, e.getEmpreendedorForte());
        ps.setString(++i, e.getMercadoForte());
        ps.setString(++i, e.getTecnologicoForte());
        ps.setString(++i, e.getCapitalFraco());
        ps.setString(++i, e.getGestaoFraco());
        ps.setString(++i, e.getEmpreendedorFraco());
        ps.setString(++i, e.getMercadoFraco());
        ps.setString(++i, e.getTecnologicoFraco());
        ps.setLong(++i, e.getEmpreendimento().getId());
        ps.execute();
        ps.close();

    }

    @Override
    public AmbienteInterno readById(Long id, Connection conn) throws Exception {
        String sql = "SELECT * FROM ambiente_interno WHERE id= ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        AmbienteInterno ambienteInterno = null;
        if (rs.next()) {
            ambienteInterno.setId(rs.getLong("id"));
            ambienteInterno.setCapitalForte(rs.getString("capital_forte"));
            ambienteInterno.setGestaoForte(rs.getString("gestao_forte"));
            ambienteInterno.setEmpreendedorForte(rs.getString("empreendedor_forte"));
            ambienteInterno.setMercadoForte(rs.getString("mercado_forte"));
            ambienteInterno.setTecnologicoForte(rs.getString("tecnologico_forte"));

            ambienteInterno.setCapitalFraco(rs.getString("capital_fraco"));
            ambienteInterno.setGestaoFraco(rs.getString("gestao_fraco"));
            ambienteInterno.setEmpreendedorFraco(rs.getString("empreendedor_fraco"));
            ambienteInterno.setMercadoFraco(rs.getString("mercado_fraco"));
            ambienteInterno.setTecnologicoFraco(rs.getString("tecnologico_fraco"));

            EmpreendimentoDAO empreendimentoDAO = new EmpreendimentoDAO();
            ambienteInterno.setEmpreendimento(empreendimentoDAO.readById(rs.getLong("empreendimento_fk"), conn));
        }
        rs.close();
        ps.close();
        return ambienteInterno;

    }

    @Override
    public List<AmbienteInterno> readByCriteria(Map<String, Object> criteria, Connection conn) throws Exception {
        String sql = "SELECT e.nome, ai.* FROM ambiente_interno ai LEFT JOIN empreendimento e ON ai.empreendimento_fk = e.id WHERE 1=1";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<AmbienteInterno> internoList = new ArrayList<>();
        while (rs.next()) {
            AmbienteInterno ambienteInterno = new AmbienteInterno();
            ambienteInterno.setId(rs.getLong("id"));
            ambienteInterno.setCapitalForte(rs.getString("capital_forte"));
            ambienteInterno.setGestaoForte(rs.getString("gestao_forte"));
            ambienteInterno.setEmpreendedorForte(rs.getString("empreendedor_forte"));
            ambienteInterno.setMercadoForte(rs.getString("mercado_forte"));
            ambienteInterno.setTecnologicoForte(rs.getString("tecnologico_forte"));

            ambienteInterno.setCapitalFraco(rs.getString("capital_fraco"));
            ambienteInterno.setGestaoFraco(rs.getString("gestao_fraco"));
            ambienteInterno.setEmpreendedorFraco(rs.getString("empreendedor_fraco"));
            ambienteInterno.setMercadoFraco(rs.getString("mercado_fraco"));
            ambienteInterno.setTecnologicoFraco(rs.getString("tecnologico_fraco"));
     
            EmpreendimentoDAO empreendimentoDAO = new EmpreendimentoDAO();
            ambienteInterno.setEmpreendimento(empreendimentoDAO.readById(rs.getLong("empreendimento_fk"), conn));
            
            internoList.add(ambienteInterno);
            
        }
        rs.close();
        ps.close();
        return internoList;
    }

    @Override
    public void update(AmbienteInterno e, Connection conn) throws Exception {
        String sql = "UPDATE ambiente_interno SET capital_forte=?, gestao_forte=?, "
                + "empreendedor_forte=?, mercado_forte=?, tecnologico_forte=?, "
                + "capital_fraco=?, gestao_fraco=?, empreendedor_fraco=?, mercado_fraco=?,"
                + " tecnologico_fraco=? WHERE empreedimento_fk = ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, e.getCapitalForte());
        ps.setString(++i, e.getGestaoForte());
        ps.setString(++i, e.getEmpreendedorForte());
        ps.setString(++i, e.getMercadoForte());
        ps.setString(++i, e.getTecnologicoForte());
        ps.setString(++i, e.getCapitalFraco());
        ps.setString(++i, e.getGestaoFraco());
        ps.setString(++i, e.getEmpreendedorFraco());
        ps.setString(++i, e.getMercadoFraco());
        ps.setString(++i, e.getTecnologicoFraco());
        ps.setLong(++i, e.getEmpreendimento().getId());
        ps.execute();
        ps.close();
    }

    @Override
    public void delete(Long id, Connection conn) throws Exception {
        String sql = "DELETE FROM ambiente_interno WHERE empreendimento_fk = ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ps.execute();
        ps.close();
    }

}
