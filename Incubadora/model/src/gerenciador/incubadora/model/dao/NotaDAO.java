package gerenciador.incubadora.model.dao;

import gerenciador.incubadora.model.base.BaseDAO;
import gerenciador.incubadora.model.entity.Avaliador;
import gerenciador.incubadora.model.entity.CriterioAvaliacao;
import gerenciador.incubadora.model.entity.Empreendimento;
import gerenciador.incubadora.model.entity.Nota;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Rafael
 */
public class NotaDAO implements BaseDAO<Nota> {

    public static final String CRITERION_AVALIADOR_ID = "1";
    public static final String CRITERION_EMPREENDIMENTO_ID = "2";
    public static final String CRITERION_CRITERIO_AVALIACAO_ID = "3";

    @Override
    public void create(Nota e, Connection conn) throws Exception {
        String sql = "INSERT INTO nota(avaliador_fk, empreendimento_fk, nota, datahora, criterio_avaliacao_fk) VALUES (?, ?, ?, ?, ?) RETURNING id;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setLong(++i, e.getAvaliador().getId());
        ps.setLong(++i, e.getEmpreendimento().getId());
        ps.setDouble(++i, e.getNota());
        ps.setTimestamp(++i, new Timestamp(e.getDataHora().getTime()));
        ps.setLong(++i, e.getCriterioAvaliacao().getId());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            e.setId(rs.getLong("id"));
        }
        rs.close();
        ps.close();
    }

    @Override
    public Nota readById(Long id, Connection conn) throws Exception {
        String sql = "SELECT * from nota WHERE id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        Nota nota = null;
        if (rs.next()) {
            nota = new Nota();
            nota.setId(id);
            nota.setDataHora(rs.getTimestamp("datahora"));
            nota.setNota(rs.getDouble("nota"));

            AvaliadorDAO avaliadorDAO = new AvaliadorDAO();
            Avaliador avaliador = avaliadorDAO.readById(rs.getLong("avaliador_fk"), conn);
            nota.setAvaliador(avaliador);

            EmpreendimentoDAO empreendimentoDAO = new EmpreendimentoDAO();
            Empreendimento empreendimento = empreendimentoDAO.readById(rs.getLong("empreendimento_fk"), conn);
            nota.setEmpreendimento(empreendimento);

            CriterioAvaliacaoDAO criterioAvaliacaoDAO = new CriterioAvaliacaoDAO();
            CriterioAvaliacao criterioAvaliacao = criterioAvaliacaoDAO.readById(rs.getLong("criterio_avaliacao_fk"), conn);
            nota.setCriterioAvaliacao(criterioAvaliacao);
        }
        rs.close();
        ps.close();

        return nota;
    }

    @Override
    public List<Nota> readByCriteria(Map<String, Object> criteria, Connection conn) throws Exception {
        List<Nota> notaList = new ArrayList<Nota>();
        String sql = "SELECT * from nota WHERE 1=1";
        Statement s = conn.createStatement();

        if (criteria != null) {
            Long criterionAvaliadorId = (Long) criteria.get(CRITERION_AVALIADOR_ID);
            if (criterionAvaliadorId != null) {
                sql += " AND avaliador_fk ='" + criterionAvaliadorId + "'";
            }
            Long criterionEmpreendimentoId = (Long) criteria.get(CRITERION_EMPREENDIMENTO_ID);
            if (criterionEmpreendimentoId != null) {
                sql += " AND empreendimento_fk ='" + criterionEmpreendimentoId + "'";
            }
            Long criterionCriterioAvaliacaoId = (Long) criteria.get(CRITERION_CRITERIO_AVALIACAO_ID);
            if (criterionCriterioAvaliacaoId != null) {
                sql += " AND criterio_avaliacao_fk ='" + criterionCriterioAvaliacaoId + "'";
            }
        }

        ResultSet rs = s.executeQuery(sql);
        Nota nota = null;
        while (rs.next()) {
            nota = new Nota();
            nota.setId(rs.getLong("id"));
            nota.setDataHora(rs.getTimestamp("datahora"));
            nota.setNota(rs.getDouble("nota"));

            AvaliadorDAO avaliadorDAO = new AvaliadorDAO();
            Avaliador avaliador = avaliadorDAO.readById(rs.getLong("avaliador_fk"), conn);
            nota.setAvaliador(avaliador);

            EmpreendimentoDAO empreendimentoDAO = new EmpreendimentoDAO();
            Empreendimento empreendimento = empreendimentoDAO.readById(rs.getLong("empreendimento_fk"), conn);
            nota.setEmpreendimento(empreendimento);

            CriterioAvaliacaoDAO criterioAvaliacaoDAO = new CriterioAvaliacaoDAO();
            CriterioAvaliacao criterioAvaliacao = criterioAvaliacaoDAO.readById(rs.getLong("criterio_avaliacao_fk"), conn);
            nota.setCriterioAvaliacao(criterioAvaliacao);

            notaList.add(nota);
        }
        rs.close();
        s.close();

        return notaList;
    }

    @Override
    public void update(Nota e, Connection conn) throws Exception {
        String sql = "UPDATE nota SET avaliador_fk=?, empreendimento_fk=?, nota=?, datahora=?, criterio_avaliacao_fk=? WHERE id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setLong(++i, e.getAvaliador().getId());
        ps.setLong(++i, e.getEmpreendimento().getId());
        ps.setDouble(++i, e.getNota());
        ps.setTimestamp(++i, new Timestamp(e.getDataHora().getTime()));
        ps.setLong(++i, e.getCriterioAvaliacao().getId());
        ps.setLong(++i, e.getId());
        ps.execute();
        ps.close();
    }

    @Override
    public void delete(Long id, Connection conn) throws Exception {
        String sql = "DELETE FROM nota WHERE id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ps.execute();
        ps.close();
    }
    
    public void updateNotaEmpreendimento(Nota e, Connection conn) throws Exception{
        
        String sql = "DELETE FROM nota WHERE nota.empreendimento_fk=? AND nota.avaliador_fk=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setLong(++i, e.getEmpreendimento().getId());
        ps.setLong(++i, e.getAvaliador().getId());
        ps.execute();
        ps.close();
        
        this.create(e, conn);
        
    }

    public Map<String, Double> getAvaliacao(Long idEmpreendimento, Connection conn) throws Exception {
        
        Map<String, Double> avaliacao = new HashMap<String, Double>();
 
        String sql = "select e.nome,(sum(n.nota)*100)/(count(e.id)*10) as nota from nota n left join criterio_avaliacao ca ON ca.id = n.criterio_avaliacao_fk "
                + "left join eixo e ON e.id = ca.eixo_fk where n.empreendimento_fk=? group by e.id order by e.id";
        

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, idEmpreendimento);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            avaliacao.put(rs.getString("nome"), rs.getDouble("nota"));
        }
        rs.close();
        ps.close();
        return avaliacao;
    }       
    
    public Map<String, Double> getNotaAvaliador(Long idAvaliador, Long idEmpreendimento, Connection conn) throws Exception{
        Map<String, Double> notaAvaliador = new HashMap<String, Double>();
        
        String sql = "select ca.nome, n.nota from nota n join criterio_avaliacao ca on n.criterio_avaliacao_fk=ca.id and n.avaliador_fk=? and n.empreendimento_fk=?";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, idAvaliador);
        ps.setLong(2, idEmpreendimento);
        
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            notaAvaliador.put(rs.getString("nome"), rs.getDouble("nota"));
        }
        rs.close();
        ps.close();
        return notaAvaliador;
    }
    

}
