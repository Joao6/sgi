package gerenciador.incubadora.model.dao;

import gerenciador.incubadora.model.base.BaseDAO;
import gerenciador.incubadora.model.entity.Edital;
import gerenciador.incubadora.model.entity.Gestor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Rafael
 */
public class EditalDAO implements BaseDAO<Edital> {

    public static final String CRITERION_DATA_INICIO_GE = "1";
    public static final String CRITERION_DATA_INICIO_LE = "2";
    public static final String CRITERION_DATA_FIM_GE = "3";
    public static final String CRITERION_DATA_FIM_LE = "4";
    public static final String CRITERION_EDITAL_ABERTO = "5";

    @Override
    public void create(Edital e, Connection conn) throws Exception {
        String sql = "INSERT INTO edital(nome, resumo, data_inicio, data_fim, data_prorrogacao, gestor_fk, path_arquivo)  VALUES (?, ?, ?, ?, ?,?, ?) RETURNING id;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, e.getNome());
        ps.setString(++i, e.getResumo());
        ps.setTimestamp(++i, new Timestamp(e.getDataInicio().getTime()));
        ps.setTimestamp(++i, new Timestamp(e.getDataFim().getTime()));
        ps.setTimestamp(++i, null);
        ps.setLong(++i, e.getGestor().getId());
        ps.setString(++i, e.getPathArquivo());

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            e.setId(rs.getLong("id"));
        }
        rs.close();
        ps.close();
    }

    @Override
    public Edital readById(Long id, Connection conn) throws Exception {
        String sql = "SELECT  e.*, g.cpf as genero_cpf,u.id as usuario_id, u.nome as usuario_nome,u.email as usuario_email, u.senha as usuario_senha, u.telefone as usuario_telefone, u.tipo_usuario as usuario_tipo_usuario, u.sobrenome as usuario_sobrenome FROM edital e LEFT JOIN gestor g ON g.usuario_fk = e.gestor_fk LEFT JOIN usuario u on u.id = g.usuario_fk WHERE e.id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        Edital edital = null;
        if (rs.next()) {
            edital = new Edital();
            edital.setId(rs.getLong("id"));
            edital.setNome(rs.getString("nome"));
            edital.setResumo(rs.getString("resumo"));
            edital.setDataInicio(rs.getDate("data_inicio"));
            edital.setDataFim(rs.getDate("data_fim"));
            edital.setDataProrrogacao(rs.getDate("data_prorrogacao"));
            edital.setPathArquivo(rs.getString("path_arquivo"));

            Gestor gestor = new Gestor();
            gestor.setId(rs.getLong("usuario_id"));
            gestor.setCpf(rs.getString("genero_cpf"));
            gestor.setNome(rs.getString("usuario_nome"));
            gestor.setEmail(rs.getString("usuario_email"));
            gestor.setSenha(rs.getString("usuario_senha"));
            gestor.setTelefone(rs.getString("usuario_telefone"));
            gestor.setTipoUsuario(rs.getString("usuario_tipo_usuario"));
            gestor.setSobrenome(rs.getString("usuario_sobrenome"));

            edital.setGestor(gestor);
        }
        rs.close();
        ps.close();
        return edital;
    }

    @Override
    public List<Edital> readByCriteria(Map<String, Object> criteria, Connection conn) throws Exception {
        String sql = "SELECT e.*, g.cpf as genero_cpf,u.id as usuario_id, u.nome as usuario_nome, u.email as usuario_email, u.senha as usuario_senha, u.telefone as usuario_telefone, u.tipo_usuario as usuario_tipo_usuario, "
                + " u.sobrenome as usuario_sobrenome FROM edital e LEFT JOIN gestor g ON g.usuario_fk = e.gestor_fk LEFT JOIN usuario u on u.id = g.usuario_fk WHERE 1=1 ";
        List<Edital> editalList = new ArrayList<Edital>();

        Statement s = conn.createStatement();

        if (criteria != null) {
            Date dataInicioGe = (Date) criteria.get(CRITERION_DATA_INICIO_GE);
            if (dataInicioGe != null) {
                sql += " AND e.data_inicio >= " + dataInicioGe;
            }
            Date dataInicioLe = (Date) criteria.get(CRITERION_DATA_INICIO_LE);
            if (dataInicioLe != null) {
                sql += " AND e.data_inicio <= " + dataInicioLe;
            }
            Date dataFimLe = (Date) criteria.get(CRITERION_DATA_FIM_LE);
            if (dataFimLe != null) {
                sql += " AND e.data_fim <= " + dataFimLe;
            }
            Date dataFimGe = (Date) criteria.get(CRITERION_DATA_FIM_GE);
            if (dataFimGe != null) {
                sql += " AND e.data_fim >= " + dataFimGe;
            }
            String criterionEditalAberto = (String) criteria.get(CRITERION_EDITAL_ABERTO);
            if (criterionEditalAberto != null) {
                sql += " AND ((data_fim >= now()) or (data_prorrogacao is not null and data_prorrogacao >= now()))";
            }

        }

        ResultSet rs = s.executeQuery(sql);
        Edital edital = null;
        while (rs.next()) {
            edital = new Edital();
            edital.setId(rs.getLong("id"));
            edital.setNome(rs.getString("nome"));
            edital.setResumo(rs.getString("resumo"));
            edital.setDataInicio(rs.getDate("data_inicio"));
            edital.setDataFim(rs.getDate("data_fim"));
            edital.setDataProrrogacao(rs.getDate("data_prorrogacao"));
          //  edital.setPathArquivo(rs.getString("path_arquivo"));

            Gestor gestor = new Gestor();
            gestor.setId(rs.getLong("usuario_id"));
            gestor.setCpf(rs.getString("genero_cpf"));
            gestor.setNome(rs.getString("usuario_nome"));
            gestor.setEmail(rs.getString("usuario_email"));
            gestor.setSenha(rs.getString("usuario_senha"));
            gestor.setTelefone(rs.getString("usuario_telefone"));
            gestor.setTipoUsuario(rs.getString("usuario_tipo_usuario"));
            gestor.setSobrenome(rs.getString("usuario_sobrenome"));

            edital.setGestor(gestor);

            editalList.add(edital);

        }

        return editalList;
    }

    @Override
    public void update(Edital e, Connection conn) throws Exception {
        String sql = "UPDATE edital SET nome=?, resumo=?, data_inicio=?, data_fim=?, data_prorrogacao=?, gestor_fk=?, path_arquivo=? WHERE id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, e.getNome());
        ps.setString(++i, e.getResumo());
        ps.setTimestamp(++i, new Timestamp(e.getDataInicio().getTime()));
        ps.setTimestamp(++i, new Timestamp(e.getDataFim().getTime()));
        if (e.getDataProrrogacao() != null) {
            ps.setTimestamp(++i, new Timestamp(e.getDataProrrogacao().getTime()));
        } else {
            ps.setTimestamp(++i, null);
        }
        ps.setLong(++i, 1);
        ps.setString(++i, e.getPathArquivo());
        ps.setLong(++i, e.getId());
        ps.execute();
        ps.close();
    }

    @Override
    public void delete(Long id, Connection conn) throws Exception {
        String sql = "DELETE FROM edital WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ps.execute();
        ps.close();
    }

    public void updateDataProrrogacao(Edital e, Connection conn) throws Exception {
        String sql = "UPDATE edital SET data_prorrogacao=? WHERE id=?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setDate(1, new java.sql.Date(e.getDataProrrogacao().getTime()));
        ps.setLong(2, e.getId());

        ps.execute();
        ps.close();
    }

}
