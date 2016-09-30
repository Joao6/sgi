package gerenciador.incubadora.model.service;

import gerenciador.incubadora.model.ConnectionManager;
import gerenciador.incubadora.model.base.service.BaseNotaService;
import gerenciador.incubadora.model.dao.NotaDAO;
import gerenciador.incubadora.model.entity.Avaliador;
import gerenciador.incubadora.model.entity.Nota;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Rafael
 */
public class NotaService implements BaseNotaService {

    @Override
    public void create(Nota e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            NotaDAO dao = new NotaDAO();
            dao.create(e, conn);
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            conn.rollback();
            conn.close();
            throw ex;
        }
    }

    @Override
    public Nota readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        NotaDAO dao = new NotaDAO();
        Nota nota = dao.readById(id, conn);
        conn.close();
        return nota;
    }

    @Override
    public List<Nota> readByCriteria(Map<String, Object> criteria) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        NotaDAO dao = new NotaDAO();
        List<Nota> notaList = dao.readByCriteria(criteria, conn);
        conn.close();
        return notaList;
    }

    @Override
    public void update(Nota e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            NotaDAO dao = new NotaDAO();
            dao.update(e, conn);
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            conn.rollback();
            conn.close();
            throw ex;
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            NotaDAO dao = new NotaDAO();
            dao.delete(id, conn);
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            conn.rollback();
            conn.close();
            throw ex;
        }
    }
    
    //ultimo mapa, original de long, double
    public Map<Long, String> validateForCreateNota(Map<Long, Double> fields) {
        Map<Long, String> errors = new HashMap<>();

        for (Long key : fields.keySet()) {
            Double nota = fields.get(key);
            if (nota == null) {
                errors.put(key, "Campo obrigatorio!");
            }
            
            if(nota < 0 || nota > 10){
                errors.put(key, "Só é possível pontuar o empreendimento com notas entre 0 e 10!");
            }
        }

        return errors;
    }

    @Override
    public Map<String, String> validateForUpdate(Map<String, Object> fields) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, String> validateForCreate(Map<String, Object> fields) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, Double> getAvaliacao(Long idEmpreendimento) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        NotaDAO dao = new NotaDAO();
        Map<String, Double> avaliacao = dao.getAvaliacao(idEmpreendimento, conn);
        conn.close();
        return avaliacao;
    }   

    @Override
    public Map<String, Double> getNotaAvaliador(Long idAvaliador, Long idEmpreendimento) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        NotaDAO dao = new NotaDAO();
        Map<String, Double> notaAvaliador = dao.getNotaAvaliador(idAvaliador, idEmpreendimento, conn);
        conn.close();
        return notaAvaliador;
    }
   
}
