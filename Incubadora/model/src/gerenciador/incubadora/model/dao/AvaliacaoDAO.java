package gerenciador.incubadora.model.dao;

import gerenciador.incubadora.model.base.BaseDAO;
import gerenciador.incubadora.model.entity.Avaliacao;
import gerenciador.incubadora.model.entity.CriterioAvaliacao;
import gerenciador.incubadora.model.entity.Eixo;
import gerenciador.incubadora.model.entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Joao
 */
public class AvaliacaoDAO {

    public Map<String, List<Avaliacao>> getAvaliacaoEmpreendimento(Connection conn, Long idEmpreendimento) throws Exception {

        Map<String, List<Avaliacao>> avaliacaoAvaliador = new HashMap<>();

//        String sql = "select eixo.nome eixo, u.id, u.nome avaliador, ca.id as criterio_fk, ca.nome criterio, nota.nota from nota join usuario u on nota.avaliador_fk=u.id join criterio_avaliacao ca on nota.criterio_avaliacao_fk=ca.id join eixo on eixo.id=ca.eixo_fk where empreendimento_fk=?";
        String sql = "select u.*, ca.id criterio_fk, ca.nome criterio_nome, ca.ativo criterio_ativo, ca.eixo_fk criterio_eixo_fk, nota.nota, e.id eixo_id, e.nome eixo_nome, e.peso eixo_peso from nota join usuario u on nota.avaliador_fk=u.id join criterio_avaliacao ca on nota.criterio_avaliacao_fk=ca.id join eixo e on e.id=ca.eixo_fk  where empreendimento_fk=? order by u.nome, criterio_nome";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setDouble(1, idEmpreendimento);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {           
            Usuario usuario = new Usuario();
            usuario.setId(rs.getLong("id"));
            usuario.setNome(rs.getString("nome"));
            usuario.setEmail(rs.getString("email"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setTelefone(rs.getString("telefone"));
            usuario.setTipoUsuario(rs.getString("tipo_usuario"));
            usuario.setSobrenome(rs.getString("sobrenome"));
            
            Avaliacao avaliacao = new Avaliacao();
            avaliacao.setUsuario(usuario);
            
            List<Avaliacao> avaliacaoList = new ArrayList<>();
                        
            CriterioAvaliacao criterio = new CriterioAvaliacao();
            criterio.setId(rs.getLong("criterio_fk"));
            criterio.setNome(rs.getString("criterio_nome"));
            criterio.setAtivo(rs.getBoolean("criterio_ativo"));
            Eixo eixo = new Eixo();
            eixo.setId(rs.getLong("eixo_id"));
            eixo.setNome(rs.getString("eixo_nome"));
            eixo.setPeso(rs.getDouble("eixo_peso"));
            criterio.setEixo(eixo);
            avaliacao.setCriterioAvaliacao(criterio);

            avaliacao.setNota(rs.getDouble("nota"));
            if (avaliacaoAvaliador.containsKey((rs.getString("nome")+" "+rs.getString("sobrenome"))) == true) {
                //avaliacaoList.add(avaliacao);                
                List<Avaliacao> aux = new ArrayList<>();
                aux = avaliacaoAvaliador.get((rs.getString("nome")+" "+rs.getString("sobrenome")));
                aux.add(avaliacao);
                avaliacaoAvaliador.put((rs.getString("nome")+" "+rs.getString("sobrenome")), aux);
            } else {
                avaliacaoList.add(avaliacao);
                avaliacaoAvaliador.put((rs.getString("nome")+" "+rs.getString("sobrenome")), avaliacaoList);
            }
        }
        rs.close();
        ps.close();

        return avaliacaoAvaliador;
    }

    public Map<String, Map<String, Double>> getNotaEmpreendimento(Long idEmpreendimento, Connection conn) throws Exception {
//        Map<Usuario, Map<String, Double>> notaAvaliador = new HashMap<>();
        Map<String, Map<String, Double>> notaAvaliado = new HashMap<>();
        String sql = "select u.*, ca.id criterio_fk, ca.nome criterio_nome, nota.nota from nota join usuario u on nota.avaliador_fk=u.id join criterio_avaliacao ca on nota.criterio_avaliacao_fk=ca.id where empreendimento_fk=? order by u.nome";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, idEmpreendimento);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(rs.getLong("id"));
            usuario.setNome(rs.getString("nome"));
            usuario.setEmail(rs.getString("email"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setTelefone(rs.getString("telefone"));
            usuario.setTipoUsuario(rs.getString("tipo_usuario"));
            usuario.setSobrenome(rs.getString("sobrenome"));

            Map<String, Double> avaliacao = new HashMap<>();

            if (notaAvaliado.containsKey(rs.getString("nome")) == true) {
                avaliacao.put(rs.getString("criterio_nome"), rs.getDouble("nota"));
            } else {
                avaliacao.put(rs.getString("criterio_nome"), rs.getDouble("nota"));
                notaAvaliado.put(rs.getString("nome"), avaliacao);
            }
        }
        rs.close();
        ps.close();

        return notaAvaliado;
    }
    
    public Map<String, Double> getNotaEixoAvaliador(Connection conn, Long idEmpreendimento, Long idAvaliador) throws SQLException{
        Map<String, Double> notaPorEixo = new HashMap<>();
        String sql = "select e.nome,(sum(n.nota)*100)/(count(e.id)*10) as nota from nota n left join criterio_avaliacao ca ON ca.id = n.criterio_avaliacao_fk left join eixo e ON e.id = ca.eixo_fk left join avaliador a on n.avaliador_fk=a.usuario_fk where n.empreendimento_fk=? and a.usuario_fk=? group by e.id order by e.id";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        
        int i = 0;
        ps.setLong(++i, idEmpreendimento);
        ps.setLong(++i, idAvaliador);
        
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            notaPorEixo.put(rs.getString("nome"), rs.getDouble("nota"));
        }
        rs.close();
        ps.close();
        
        return notaPorEixo;
    }

}
