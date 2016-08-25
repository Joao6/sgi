package gerenciador.incubadora.model.dao;

import gerenciador.incubadora.model.base.BaseDAO;
import gerenciador.incubadora.model.entity.Empreendedor;
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
        String sql = "select e.*, u.*, emp.id emp_id, emp.nome emp_nome, emp.email emp_email, emp.razao_social emp_razao_social, emp.cnpj emp_cnpj, emp.inscricao_estadual emp_inscricao_estadual, emp.fax emp_fax, emp.missao emp_missao, emp.valores emp_valores from empreendedor e left join usuario u on e.usuario_fk=u.id left join empreendimento_empreendedor ee on e.usuario_fk=ee.empreendedor_fk";

        Long criterionEmpreendimentoId = (Long) criteria.get(CRITERION_EMPREENDIMENTO_ID);
        if (criterionEmpreendimentoId != null) {
            sql += "  right join empreendimento emp on ee.empreendimento_fk=emp.id where emp.id=" + criterionEmpreendimentoId;
        }

        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(sql);
        List<EmpreendimentoEmpreendedor> eeList = new ArrayList<>();
        while (rs.next()) {
            Empreendimento empreendimento = new Empreendimento();
            //Empreendedor empreendedor = new Empreendedor();
            empreendimento.setId(rs.getLong("emp_id"));
            empreendimento.setNome(rs.getString("emp_nome"));
            
            Empreendedor empreendedor = new Empreendedor();
            empreendedor.setId(rs.getLong("id"));
            empreendedor.setNome(rs.getString("nome"));
            empreendedor.setSobrenome(rs.getString("sobrenome"));
            empreendedor.setEmail(rs.getString("email"));
            
            
            EmpreendimentoEmpreendedor ee = new EmpreendimentoEmpreendedor();
            ee.setEmpreendedor(empreendedor);
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
