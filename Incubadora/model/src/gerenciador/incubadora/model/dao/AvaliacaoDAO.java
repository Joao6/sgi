package gerenciador.incubadora.model.dao;

import gerenciador.incubadora.model.base.BaseDAO;
import gerenciador.incubadora.model.entity.Avaliacao;
import gerenciador.incubadora.model.entity.CriterioAvaliacao;
import gerenciador.incubadora.model.entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Joao
 */
public class AvaliacaoDAO implements BaseDAO<Avaliacao> {

    @Override
    public void create(Avaliacao e, Connection conn) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Avaliacao readById(Long id, Connection conn) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Avaliacao> readByCriteria(Map<String, Object> criteria, Connection conn) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Avaliacao e, Connection conn) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Long id, Connection conn) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Map<Usuario, List<Avaliacao>> getAvaliacaoEmpreendimento(Connection conn, Long idEmpreendimento) throws Exception{
        
        Map<Usuario, List<Avaliacao>> avaliacaoAvaliador = new HashMap<>();
        
        String sql = "select eixo.nome eixo, u.id, u.nome avaliador, ca.id as criterio_fk, ca.nome criterio, nota.nota from nota join usuario u on nota.avaliador_fk=u.id join criterio_avaliacao ca on nota.criterio_avaliacao_fk=ca.id join eixo on eixo.id=ca.eixo_fk where empreendimento_fk=?";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setDouble(1, idEmpreendimento);
        
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = usuarioDAO.readById(rs.getLong("id"), conn);
            
            List<Avaliacao> avaliacaoList = new ArrayList<>();
            Avaliacao avaliacao = new Avaliacao();
            
            CriterioAvaliacaoDAO criterioDAO = new CriterioAvaliacaoDAO();
            CriterioAvaliacao criterio = criterioDAO.readById(rs.getLong("criterio_fk"), conn);
            avaliacao.setCriterioAvaliacao(criterio);
            
            avaliacao.setNota(rs.getDouble("nota"));
            avaliacaoList.add(avaliacao);
            avaliacaoAvaliador.put(usuario, avaliacaoList);
        }
        rs.close();
        ps.close();
        
        return avaliacaoAvaliador;
    }
    
}
