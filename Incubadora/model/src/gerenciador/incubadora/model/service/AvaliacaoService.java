package gerenciador.incubadora.model.service;

import gerenciador.incubadora.model.ConnectionManager;
import gerenciador.incubadora.model.base.service.BaseAvaliacaoService;
import gerenciador.incubadora.model.dao.AvaliacaoDAO;
import gerenciador.incubadora.model.entity.Avaliacao;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Joao
 */
public class AvaliacaoService implements BaseAvaliacaoService {

    @Override
    public Map<String, List<Avaliacao>> getAvaliacaoEmpreendimento(Long idEmpreendimento) throws Exception {

        Map<String, List<Avaliacao>> avaliacaoAvaliador = new HashMap<>();

        Connection conn = ConnectionManager.getInstance().getConnection();

        AvaliacaoDAO dao = new AvaliacaoDAO();
        avaliacaoAvaliador = dao.getAvaliacaoEmpreendimento(conn, idEmpreendimento);
        conn.close();

        return avaliacaoAvaliador;
    }

    @Override
    public Map<String, Double> getNotaEixoAvaliador(Long idEmpreendimento, Long idAvaliador) throws Exception {
        Map<String, Double> notaPorEixo = null;
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            AvaliacaoDAO dao = new AvaliacaoDAO();
            notaPorEixo = dao.getNotaEixoAvaliador(conn, idEmpreendimento, idAvaliador);
            conn.close();
        } catch (Exception e) {
            conn.close();
        }

        return notaPorEixo;
    }

}
