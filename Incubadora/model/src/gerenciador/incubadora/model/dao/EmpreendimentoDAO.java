package gerenciador.incubadora.model.dao;

import gerenciador.incubadora.model.base.BaseDAO;
import gerenciador.incubadora.model.entity.ApresentacaoNegocio;
import gerenciador.incubadora.model.entity.Avaliador;
import gerenciador.incubadora.model.entity.CriterioAvaliacao;
import gerenciador.incubadora.model.entity.Edital;
import gerenciador.incubadora.model.entity.Eixo;
import gerenciador.incubadora.model.entity.Empreendedor;
import gerenciador.incubadora.model.entity.Empreendimento;
import gerenciador.incubadora.model.entity.Nota;
import gerenciador.incubadora.model.entity.RamoAtividade;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmpreendimentoDAO implements BaseDAO<Empreendimento> {

    public static final String CRITERION_EMPREENDEDOR_ID = "0";
    public static final String CRITERION_AVALIADOR_ID = "1";
    public static final String CRITERION_NOT_NOTA = "2";
    public static final String CRITERION_STATUS_ILIKE = "3";

    @Override
    public void create(Empreendimento e, Connection conn) throws Exception {
        String sql = "INSERT INTO empreendimento(nome, razao_social, cnpj, inscricao_estadual, inscricao_municipal, "
                + " email, fax, missao, visao, valores, data_ingresso, data_abertura, data_prev_graduacao, path_logo, ramo_atividade_fk, status, telefone, edital_fk)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id;";
        PreparedStatement ps = conn.prepareStatement(sql);

        //Data Default
        if (e.getDataAbertura() == null) {
            Date d = Date.valueOf("0001-01-01");
            e.setDataAbertura(d);
        }
        if (e.getDataIngresso() == null) {
            Date d2 = Date.valueOf("0001-01-01");
            e.setDataIngresso(d2);
        }

        if (e.getDataPrevisaoGraduacao() == null) {
            Date d3 = Date.valueOf("0001-01-01");
            e.setDataPrevisaoGraduacao(d3);
        }

        int i = 0;
        ps.setString(++i, e.getNome());
        ps.setString(++i, e.getRazaoSocial());
        ps.setString(++i, e.getCnpj());
        ps.setString(++i, e.getInscricaoEstadual());
        ps.setString(++i, e.getInscricaoMunicipal());
        ps.setString(++i, e.getEmail());
        ps.setString(++i, e.getFax());
        ps.setString(++i, e.getMissao());
        ps.setString(++i, e.getVisao());
        ps.setString(++i, e.getValores());
        ps.setDate(++i, new Date(e.getDataIngresso().getTime()));
        ps.setDate(++i, new Date(e.getDataAbertura().getTime()));
        ps.setDate(++i, new Date(e.getDataPrevisaoGraduacao().getTime()));
        ps.setString(++i, e.getLogo());
        ps.setLong(++i, e.getRamoAtividade().getId());
        ps.setString(++i, e.getStatus());
        ps.setString(++i, e.getTelefone());
        ps.setLong(++i, e.getEdital().getId());

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            e.setId(rs.getLong("id"));
        }
        rs.close();
        ps.close();

        String sqlEmp = "INSERT INTO empreendimento_empreendedor(empreendimento_fk, empreendedor_fk) VALUES (?, ?);";
        PreparedStatement psEmp = conn.prepareStatement(sqlEmp);
        for (Empreendedor emp : e.getEmpreendedorList()) {
            psEmp.setLong(1, e.getId());
            psEmp.setLong(2, emp.getId());
            psEmp.execute();
        }

        psEmp.close();

    }

    @Override
    public Empreendimento readById(Long id, Connection conn) throws Exception {

        String sql = "select e.*, ra.nome as ra_nome, ra.id as ra_id, em.usuario_fk as empreendedor_id, an.*"
                + " from empreendimento e left join ramo_atividade ra on e.ramo_atividade_fk=ra.id left join empreendimento_empreendedor e_emp on e_emp.empreendimento_fk=e.id "
                + " left join empreendedor em on em.usuario_fk = e_emp.empreendedor_fk "
                + " left join apresentacao_negocio an ON an.empreendimento_fk = e.id where e.id=?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        Empreendimento empreendimento = null;
        while (rs.next()) {
            if (empreendimento == null) {
                empreendimento = new Empreendimento();
                empreendimento.setId(rs.getLong("id"));
                empreendimento.setNome(rs.getString("nome"));
                empreendimento.setRazaoSocial(rs.getString("razao_social"));
                empreendimento.setCnpj(rs.getString("cnpj"));
                empreendimento.setDataIngresso(rs.getDate("data_ingresso"));
                empreendimento.setDataAbertura(rs.getDate("data_abertura"));
                empreendimento.setDataPrevisaoGraduacao(rs.getDate("data_prev_graduacao"));
                empreendimento.setMissao(rs.getString("missao"));
                empreendimento.setVisao(rs.getString("visao"));
                empreendimento.setValores(rs.getString("valores"));
                empreendimento.setLogo(rs.getString("path_logo"));
                empreendimento.setInscricaoEstadual(rs.getString("inscricao_estadual"));
                empreendimento.setInscricaoMunicipal(rs.getString("inscricao_municipal"));
                empreendimento.setEmail(rs.getString("email"));
                empreendimento.setFax(rs.getString("fax"));
                empreendimento.setStatus(rs.getString("status"));
                empreendimento.setTelefone(rs.getString("telefone"));
                empreendimento.setContratacaoAceita(rs.getBoolean("contratacao_aceita"));
                empreendimento.setLocalApresentacao(rs.getString("local_apresentacao"));
                java.sql.Timestamp date = rs.getTimestamp("data_hora_apresentacao");
                if (date != null) {
                    empreendimento.setDataHoraApresentacao(new java.util.Date(date.getTime()));
                }
                EditalDAO editalDAO = new EditalDAO();
                Edital edital = editalDAO.readById(rs.getLong("edital_fk"), conn);
                empreendimento.setEdital(edital);

                //Ramo Atividade
                RamoAtividade ramoAtividade = new RamoAtividade();
                ramoAtividade.setId(rs.getLong("ra_id"));
                ramoAtividade.setNome(rs.getString("ra_nome"));
                empreendimento.setRamoAtividade(ramoAtividade);

                //ApresentacaoNegocio
                ApresentacaoNegocio apresentacaoNegocio = new ApresentacaoNegocio();
                apresentacaoNegocio.setMiniCurriculo(rs.getString("curriculo"));
                apresentacaoNegocio.setDisponibilidade(rs.getString("disponibilidade"));
                apresentacaoNegocio.setInovacaoProduto(rs.getString("inovacao_produto"));
                apresentacaoNegocio.setTempoDesenvolvimento(rs.getString("tempo_desenvolvimento"));
                apresentacaoNegocio.setIdentificacao(rs.getString("identificacao"));
                apresentacaoNegocio.setMercadoAlvo(rs.getString("mercado_alvo"));
                apresentacaoNegocio.setVantagemCompetitiva(rs.getString("vantagem_competitiva"));
                apresentacaoNegocio.setInvestimento(rs.getString("investimento"));
                apresentacaoNegocio.setRetorno(rs.getString("retorno"));
                apresentacaoNegocio.setParcerias(rs.getString("parcerias"));
                apresentacaoNegocio.setEstruturaOrganizacional(rs.getString("estrutura_organizacional"));

                empreendimento.setApresentacaoNegocio(apresentacaoNegocio);
            }

            EmpreendedorDAO daoEmpreendedor = new EmpreendedorDAO();
            Empreendedor empreendedor = daoEmpreendedor.readById(rs.getLong("empreendedor_id"), conn);
            empreendimento.getEmpreendedorList().add(empreendedor);

        }
        rs.close();
        ps.close();

        String sqlNota = "Select n.*, a.cpf as avaliador_cpf, u.nome as avaliador_nome, u.email as avaliador_email, u.senha as avaliador_senha, u.telefone as avaliador_telefone, "
                + " u.tipo_usuario as avaliador_tipo_usuario,u.sobrenome as avaliador_sobrenome, ca.nome as criterio_nome, ca.ativo as criterio_ativo, "
                + " e.id as eixo_id, e.nome as eixo_nome from nota n LEFT JOIN avaliador a ON a.usuario_fk = n.avaliador_fk LEFT JOIN usuario u ON u.id = a.usuario_fk "
                + " LEFT JOIN criterio_avaliacao ca ON ca.id = n.criterio_avaliacao_fk LEFT JOIN eixo e ON e.id = ca.eixo_fk WHERE n.empreendimento_fk =?;";

        PreparedStatement psNota = conn.prepareStatement(sqlNota);
        psNota.setLong(1, id);
        ResultSet rsAvaliador = psNota.executeQuery();
        List<Nota> notaList = new ArrayList<Nota>();
        while (rsAvaliador.next()) {
            Nota nota = new Nota();
            nota.setId(rsAvaliador.getLong("id"));
            nota.setNota(rsAvaliador.getDouble("nota"));
            nota.setDataHora(rsAvaliador.getTimestamp("datahora"));

            Eixo eixo = new Eixo();
            eixo.setId(rsAvaliador.getLong("eixo_id"));
            eixo.setNome(rsAvaliador.getString("eixo_nome"));

            CriterioAvaliacao criterioAvaliacao = new CriterioAvaliacao();
            criterioAvaliacao.setId(rsAvaliador.getLong("criterio_avaliacao_fk"));
            criterioAvaliacao.setAtivo(rsAvaliador.getBoolean("criterio_ativo"));
            criterioAvaliacao.setNome(rsAvaliador.getString("criterio_nome"));
            criterioAvaliacao.setEixo(eixo);

            nota.setCriterioAvaliacao(criterioAvaliacao);

            Avaliador avaliador = new Avaliador();
            avaliador.setId(rsAvaliador.getLong("avaliador_fk"));
            avaliador.setNome(rsAvaliador.getString("avaliador_nome"));
            avaliador.setEmail(rsAvaliador.getString("avaliador_email"));
            avaliador.setCpf(rsAvaliador.getString("avaliador_cpf"));
            avaliador.setSenha(rsAvaliador.getString("avaliador_senha"));
            avaliador.setSobrenome(rsAvaliador.getString("avaliador_sobrenome"));
            avaliador.setTelefone(rsAvaliador.getString("avaliador_telefone"));
            avaliador.setTipoUsuario(rsAvaliador.getString("avaliador_tipo_usuario"));

            nota.setAvaliador(avaliador);
            notaList.add(nota);
        }
        empreendimento.setNotaList(notaList);
        rsAvaliador.close();
        psNota.close();

        String sqlAvaliador = "Select a.*, u.* from avaliador_empreendimento ae LEFT JOIN avaliador a ON a.usuario_fk = ae.avaliador_fk LEFT JOIN usuario u ON u.id = a.usuario_fk where ae.empreendimento_fk = ?";
        PreparedStatement psAvaliador = conn.prepareStatement(sqlAvaliador);
        psAvaliador.setLong(1, id);
        rsAvaliador = psAvaliador.executeQuery();
        List<Avaliador> avaliadorList = new ArrayList<Avaliador>();
        while (rsAvaliador.next()) {
            Avaliador avaliador = new Avaliador();
            avaliador.setId(rsAvaliador.getLong("id"));
            avaliador.setNome(rsAvaliador.getString("nome"));
            avaliador.setEmail(rsAvaliador.getString("email"));
            avaliador.setCpf(rsAvaliador.getString("cpf"));
            avaliador.setSenha(rsAvaliador.getString("senha"));
            avaliador.setSobrenome(rsAvaliador.getString("sobrenome"));
            avaliador.setTelefone(rsAvaliador.getString("telefone"));
            avaliador.setTipoUsuario(rsAvaliador.getString("tipo_usuario"));

            avaliadorList.add(avaliador);
        }
        empreendimento.setAvaliadorList(avaliadorList);

        return empreendimento;
    }

    @Override
    public List<Empreendimento> readByCriteria(Map<String, Object> criteria, Connection conn) throws Exception {
        List<Empreendimento> empreendimentoList = new ArrayList<>();
        String sql = "select e.*, ra.nome as ra_nome, ra.id as ra_id, em.usuario_fk as empreendedor_id, an.* from empreendimento e "
                + " left join ramo_atividade ra on e.ramo_atividade_fk=ra.id "
                + " left join empreendimento_empreendedor e_emp on e_emp.empreendimento_fk=e.id "
                + " left join empreendedor em on em.usuario_fk = e_emp.empreendedor_fk "
                + " left join apresentacao_negocio an ON an.empreendimento_fk = e.id  where 1=1 ";

        if (criteria != null) {
            Long criterionEmpreendedorId = (Long) criteria.get(CRITERION_EMPREENDEDOR_ID);
            if (criterionEmpreendedorId != null) {
                sql += " and em.usuario_fk=" + criterionEmpreendedorId;
            }
            Long criterionAvaliadorId = (Long) criteria.get(CRITERION_AVALIADOR_ID);
            if (criterionAvaliadorId != null) {
                sql += " AND e.id in (Select ae.empreendimento_fk from avaliador_empreendimento ae where ae.avaliador_fk=" + criterionAvaliadorId + ")";
            }

            Boolean criterionNotNotas = (Boolean) criteria.get(CRITERION_NOT_NOTA);
            if (criterionNotNotas != null && criterionNotNotas) {
                sql += " intersect select e.*, ra.nome as ra_nome, ra.id as ra_id, em.usuario_fk as empreendedor_id, an.* from empreendimento e left join ramo_atividade ra on e.ramo_atividade_fk=ra.id "
                        + "  left join empreendimento_empreendedor e_emp on e_emp.empreendimento_fk=e.id left join empreendedor em on em.usuario_fk = e_emp.empreendedor_fk "
                        + "  left join apresentacao_negocio an ON an.empreendimento_fk = e.id "
                        + "  left join nota n on n.empreendimento_fk=e.id where 1=1 AND e.id != n.empreendimento_fk";
            }

            String criterionStatusIlike = (String) criteria.get(CRITERION_STATUS_ILIKE);
            if (criterionStatusIlike != null && !criterionStatusIlike.isEmpty()) {
                sql += " AND status ilike '" + criterionStatusIlike + "'";
            }

        }

//      sql += " order by e.id desc";
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(sql);

        RamoAtividade ramoAtividade;
        Empreendimento empreendimento = null;

        String sqlAvaliadores = "select u.id, u.nome, u.sobrenome from avaliador_empreendimento ae "
                + " inner join usuario u on u.id=ae.avaliador_fk"
                + " inner join avaliador av on av.usuario_fk=u.id"
                + " inner join empreendimento emp on emp.id=ae.empreendimento_fk"
                + " and ae.empreendimento_fk=?";
        while (rs.next()) {
            Long idEmpreendimento = rs.getLong("id");
            int index = -1;
            boolean existe = false;
            for (Empreendimento aux : empreendimentoList) {
                if (aux.getId().equals(idEmpreendimento)) {
                    index = empreendimentoList.indexOf(aux);
                    existe = true;
                    break;
                }
            }
            if (!existe) {
                empreendimento = new Empreendimento();
                empreendimento.setId(idEmpreendimento);
                empreendimento.setNome(rs.getString("nome"));
                empreendimento.setRazaoSocial(rs.getString("razao_social"));
                empreendimento.setCnpj(rs.getString("cnpj"));
                empreendimento.setDataIngresso(rs.getDate("data_ingresso"));
                empreendimento.setDataAbertura(rs.getDate("data_abertura"));
                empreendimento.setDataPrevisaoGraduacao(rs.getDate("data_prev_graduacao"));
                empreendimento.setMissao(rs.getString("missao"));
                empreendimento.setVisao(rs.getString("visao"));
                empreendimento.setValores(rs.getString("valores"));
                empreendimento.setLogo(rs.getString("path_logo"));
                empreendimento.setInscricaoEstadual(rs.getString("inscricao_estadual"));
                empreendimento.setInscricaoMunicipal(rs.getString("inscricao_municipal"));
                empreendimento.setEmail(rs.getString("email"));
                empreendimento.setFax(rs.getString("fax"));
                empreendimento.setStatus(rs.getString("status"));
                empreendimento.setTelefone(rs.getString("telefone"));
                empreendimento.setContratacaoAceita(rs.getBoolean("contratacao_aceita"));
                empreendimento.setLocalApresentacao(rs.getString("local_apresentacao"));

                java.sql.Timestamp date = rs.getTimestamp("data_hora_apresentacao");
                if (date != null) {
                    empreendimento.setDataHoraApresentacao(new java.util.Date(date.getTime()));
                }

                EditalDAO editalDAO = new EditalDAO();
                Edital edital = editalDAO.readById(rs.getLong("edital_fk"), conn);
                empreendimento.setEdital(edital);

                //Ramo Atividade
                ramoAtividade = new RamoAtividade();
                ramoAtividade.setId(rs.getLong("ra_id"));
                ramoAtividade.setNome(rs.getString("ra_nome"));
                empreendimento.setRamoAtividade(ramoAtividade);

                EmpreendedorDAO daoEmpreendedor = new EmpreendedorDAO();
                Empreendedor empreendedor = daoEmpreendedor.readById(rs.getLong("empreendedor_id"), conn);
                empreendimento.getEmpreendedorList().add(empreendedor);

                //ApresentacaoNegocio
                ApresentacaoNegocio apresentacaoNegocio = new ApresentacaoNegocio();
                apresentacaoNegocio.setMiniCurriculo(rs.getString("curriculo"));
                apresentacaoNegocio.setDisponibilidade(rs.getString("disponibilidade"));
                apresentacaoNegocio.setInovacaoProduto(rs.getString("inovacao_produto"));
                apresentacaoNegocio.setTempoDesenvolvimento(rs.getString("tempo_desenvolvimento"));
                apresentacaoNegocio.setIdentificacao(rs.getString("identificacao"));
                apresentacaoNegocio.setMercadoAlvo(rs.getString("mercado_alvo"));
                apresentacaoNegocio.setVantagemCompetitiva(rs.getString("vantagem_competitiva"));
                apresentacaoNegocio.setInvestimento(rs.getString("investimento"));
                apresentacaoNegocio.setRetorno(rs.getString("retorno"));
                apresentacaoNegocio.setParcerias(rs.getString("parcerias"));
                apresentacaoNegocio.setEstruturaOrganizacional(rs.getString("estrutura_organizacional"));

                // Avaliadores
                PreparedStatement psAvaliadores = conn.prepareStatement(sqlAvaliadores);
                psAvaliadores.setLong(1, empreendimento.getId());
                ResultSet rsAvaliadores = psAvaliadores.executeQuery();

                List<Avaliador> avaliadorList = new ArrayList<>();
                while (rsAvaliadores.next()) {
                    Avaliador avaliador = new Avaliador();
                    avaliador.setId(rsAvaliadores.getLong("id"));
                    avaliador.setNome(rsAvaliadores.getString("nome"));
                    avaliador.setSobrenome(rsAvaliadores.getString("sobrenome"));
                    avaliadorList.add(avaliador);
                }
                rsAvaliadores.close();
                psAvaliadores.close();

                empreendimento.setAvaliadorList(avaliadorList);
                empreendimento.setApresentacaoNegocio(apresentacaoNegocio);

                empreendimentoList.add(empreendimento);
            } else {
                EmpreendedorDAO daoEmpreendedor = new EmpreendedorDAO();
                Empreendedor empreendedor = daoEmpreendedor.readById(rs.getLong("empreendedor_id"), conn);
                empreendimentoList.get(index).getEmpreendedorList().add(empreendedor);
            }

        }
        rs.close();
        s.close();

        return empreendimentoList;
    }

    @Override
    public void update(Empreendimento e, Connection conn) throws Exception {
        String sql = "UPDATE empreendimento SET nome=?, razao_social=?, cnpj=?, inscricao_estadual=?, inscricao_municipal=?, email=?, fax=?, missao=?, visao=?, valores=?, data_ingresso=?, "
                + " data_abertura=?, data_prev_graduacao=?, path_logo=?, ramo_atividade_fk=?,  status=?, telefone=?, edital_fk=? WHERE id=?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        //Data Default
        if (e.getDataAbertura() == null) {
            Date d1 = Date.valueOf("0001-01-01");
            e.setDataAbertura(d1);
        }
        if (e.getDataIngresso() == null) {
            Date d2 = Date.valueOf("0001-01-01");
            e.setDataIngresso(d2);
        }

        if (e.getDataPrevisaoGraduacao() == null) {
            Date d3 = Date.valueOf("0001-01-01");
            e.setDataPrevisaoGraduacao(d3);
        }

        int i = 0;
        ps.setString(++i, e.getNome());
        ps.setString(++i, e.getRazaoSocial());
        ps.setString(++i, e.getCnpj());
        ps.setString(++i, e.getInscricaoEstadual());
        ps.setString(++i, e.getInscricaoMunicipal());
        ps.setString(++i, e.getEmail());
        ps.setString(++i, e.getFax());
        ps.setString(++i, e.getMissao());
        ps.setString(++i, e.getVisao());
        ps.setString(++i, e.getValores());
        ps.setDate(++i, new Date(e.getDataIngresso().getTime()));
        ps.setDate(++i, new Date(e.getDataAbertura().getTime()));
        ps.setDate(++i, new Date(e.getDataPrevisaoGraduacao().getTime()));
        ps.setString(++i, e.getLogo());
        ps.setLong(++i, e.getRamoAtividade().getId());
        ps.setString(++i, e.getStatus());
        ps.setString(++i, e.getTelefone());
        ps.setLong(++i, e.getEdital().getId());
        ps.setLong(++i, e.getId());
        ps.execute();
        ps.close();

        String sqlDeleteEmp = "DELETE FROM empreendimento_empreendedor WHERE empreendimento_fk=?;";
        PreparedStatement psDeleteEmp = conn.prepareStatement(sqlDeleteEmp);
        psDeleteEmp.setLong(1, e.getId());
        psDeleteEmp.execute();
        psDeleteEmp.close();

        String sqlEmp = "INSERT INTO empreendimento_empreendedor(empreendimento_fk, empreendedor_fk) VALUES (?, ?);";
        if (e.getEmpreendedorList().size() > 0) {
            PreparedStatement psEmp = conn.prepareStatement(sqlEmp);
            for (Empreendedor emp : e.getEmpreendedorList()) {
                psEmp.setLong(1, e.getId());
                psEmp.setLong(2, emp.getId());
                psEmp.execute();
            }
            psEmp.close();
        }

        String sqlDeleteAvaliador = "DELETE FROM avaliador_empreendimento WHERE empreendimento_fk=?;";
        PreparedStatement psDeleteAvaliador = conn.prepareStatement(sqlDeleteAvaliador);
        psDeleteAvaliador.setLong(1, e.getId());
        psDeleteAvaliador.execute();
        psDeleteAvaliador.close();

        if (e.getAvaliadorList().size() > 0) {
            String sqlAvaliador = "INSERT INTO avaliador_empreendimento(avaliador_fk, empreendimento_fk) VALUES (?, ?);";
            PreparedStatement psAvaliador = conn.prepareStatement(sqlAvaliador);
            for (Avaliador avaliador : e.getAvaliadorList()) {
                psAvaliador.setLong(1, avaliador.getId());
                psAvaliador.setLong(2, e.getId());
                psAvaliador.execute();
            }
            psAvaliador.close();
        }

        String sqlDelApNegocio = "DELETE FROM apresentacao_negocio WHERE empreendimento_fk=?";
        PreparedStatement psDelApNegocio = conn.prepareStatement(sqlDelApNegocio);
        psDelApNegocio.setLong(1, e.getId());
        psDelApNegocio.execute();

        String sqlApNegocio = "INSERT INTO apresentacao_negocio(empreendimento_fk, curriculo, disponibilidade, "
                + "inovacao_produto, tempo_desenvolvimento, identificacao, mercado_alvo, vantagem_competitiva, "
                + "investimento, retorno, parcerias, estrutura_organizacional) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement psApNegocio = conn.prepareStatement(sqlApNegocio);

        int l = 0;

        psApNegocio.setLong(++l, e.getId());
        psApNegocio.setString(++l, e.getApresentacaoNegocio().getMiniCurriculo());
        psApNegocio.setString(++l, e.getApresentacaoNegocio().getDisponibilidade());
        psApNegocio.setString(++l, e.getApresentacaoNegocio().getInovacaoProduto());
        psApNegocio.setString(++l, e.getApresentacaoNegocio().getTempoDesenvolvimento());
        psApNegocio.setString(++l, e.getApresentacaoNegocio().getIdentificacao());
        psApNegocio.setString(++l, e.getApresentacaoNegocio().getMercadoAlvo());
        psApNegocio.setString(++l, e.getApresentacaoNegocio().getVantagemCompetitiva());
        psApNegocio.setString(++l, e.getApresentacaoNegocio().getInvestimento());
        psApNegocio.setString(++l, e.getApresentacaoNegocio().getRetorno());
        psApNegocio.setString(++l, e.getApresentacaoNegocio().getParcerias());
        psApNegocio.setString(++l, e.getApresentacaoNegocio().getEstruturaOrganizacional());

        psApNegocio.execute();

    }

    @Override
    public void delete(Long id, Connection conn) throws Exception {
        String sql = "DELETE FROM empreendimento WHERE id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ps.execute();
        ps.close();
    }

    public boolean addEmpreendedor(Empreendimento empreendimento, Connection conn) throws Exception {
        boolean sucess = false;
        String sql = "INSERT INTO empreendimento_empreendedor( empreendimento_fk, empreendedor_fk)VALUES (?, ?);";
        PreparedStatement ps = conn.prepareStatement(sql);

        if (empreendimento.getId() != null
                && empreendimento.getEmpreendedorList() != null
                && empreendimento.getEmpreendedorList().get(0) != null) {

            ps.setLong(1, empreendimento.getId());
            ps.setLong(2, empreendimento.getEmpreendedorList().get(0).getId());
            sucess = ps.execute();
        }
        ps.close();
        return sucess;
    }

    public boolean removeEmpreendedor(Empreendimento empreendimento, Connection conn) throws Exception {
        boolean sucess = false;
        String sql = "DELETE FROM empreendimento_empreendedor WHERE empreendedor_fk=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        if (empreendimento != null
                && empreendimento.getEmpreendedorList() != null
                && empreendimento.getEmpreendedorList().get(0) != null) {
            ps.setLong(1, empreendimento.getEmpreendedorList().get(0).getId());
            sucess = ps.execute();
        }
        ps.close();
        return sucess;
    }

    public void updateStatusEmpreendimento(Long id, String status, Connection conn) throws Exception {
        String sql = "UPDATE empreendimento set status=? WHERE id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, status);
        ps.setLong(2, id);
        ps.execute();
        ps.close();
    }

    public void updateContratacao(Long id, Boolean contratacao, Connection conn) throws Exception {
        String sql = "UPDATE empreendimento set contratacao_aceita=? WHERE id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setBoolean(1, contratacao);
        ps.setLong(2, id);
        ps.execute();
        ps.close();
    }

    public void agendarApresentacao(Long id, Date data, String localApresentacao, Connection conn) throws Exception {
        String sql = "UPDATE empreendimento set data_hora_apresentacao=?, local_apresentacao=? WHERE id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setTimestamp(1, new Timestamp(data.getTime()));
        ps.setString(2, localApresentacao);
        ps.setLong(3, id);
        ps.execute();
        ps.close();
    }

}
