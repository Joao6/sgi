package gerenciador.incubadora.model.dao;

import gerenciador.incubadora.model.base.BaseDAO;
import gerenciador.incubadora.model.entity.Empreendimento;
import gerenciador.incubadora.model.entity.EmpreendimentoEmpreendedor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Rafael
 */
public class EmpreendimentoEmpreendedorDAO implements BaseDAO<EmpreendimentoEmpreendedor> {

    public final static String CRITERION_EMPREENDIMENTO_ID = "0";
    public final static String CRITERION_EMPREENDEDOR_ID = "1";

    @Override
    public void create(EmpreendimentoEmpreendedor e, Connection conn) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EmpreendimentoEmpreendedor readById(Long id, Connection conn) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EmpreendimentoEmpreendedor> readByCriteria(Map<String, Object> criteria, Connection conn) throws Exception {
        String sql = "select * from empreendedor e"
                + " left join empreendimento_empreendedor ee "
                + " on ee.empreendedor_fk=e.usuario_fk";

        Long criterionEmpreendimentoId = (Long) criteria.get(CRITERION_EMPREENDEDOR_ID);
        if (criterionEmpreendimentoId != null) {
            sql += " left join empreendimento emp "
                    + " on ee.empreendimento_fk=" + criterionEmpreendimentoId;
        }

        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(sql);
        List<EmpreendimentoEmpreendedor> eeList = new ArrayList<>();
        while (rs.next()) {
            Empreendimento empreendimento = new Empreendimento();
            //Empreendedor empreendedor = new Empreendedor();
            empreendimento.setId(rs.getLong("id"));
            empreendimento.setNome(rs.getString("nome"));
            
            EmpreendimentoEmpreendedor ee = new EmpreendimentoEmpreendedor();
            ee.setEmpreendimento(empreendimento);
            
            eeList.add(ee);
        }

        return eeList;
    }

    @Override
    public void update(EmpreendimentoEmpreendedor e, Connection conn) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Long id, Connection conn) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
