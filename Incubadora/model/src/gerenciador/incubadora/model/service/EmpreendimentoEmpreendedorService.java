package gerenciador.incubadora.model.service;

import gerenciador.incubadora.model.ConnectionManager;
import gerenciador.incubadora.model.base.BaseEntity;
import gerenciador.incubadora.model.base.BaseService;
import gerenciador.incubadora.model.dao.EmpreendimentoEmpreendedorDAO;
import gerenciador.incubadora.model.entity.EmpreendimentoEmpreendedor;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Rafael
 */
public class EmpreendimentoEmpreendedorService implements BaseService {

    @Override
    public void create(BaseEntity e) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BaseEntity readById(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List readByCriteria(Map criteria) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        List<EmpreendimentoEmpreendedor> eeList = null;
        try {
            EmpreendimentoEmpreendedorDAO dao = new EmpreendimentoEmpreendedorDAO();
            eeList = dao.readByCriteria(criteria, conn);
            conn.close();
        } catch (Exception e) {
            conn.close();
            throw e;
        }
        return eeList;
    }

    @Override
    public void update(BaseEntity e) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map validateForCreate(Map fields) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map validateForUpdate(Map fields) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
