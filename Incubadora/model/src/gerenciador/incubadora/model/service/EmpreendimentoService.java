package gerenciador.incubadora.model.service;

import gerenciador.incubadora.model.ConnectionManager;
import gerenciador.incubadora.model.ErrorMessage;
import gerenciador.incubadora.model.ServiceLocator;
import gerenciador.incubadora.model.base.service.BaseEmpreendimentoService;
import gerenciador.incubadora.model.dao.EmpreendimentoDAO;
import gerenciador.incubadora.model.dao.EmpreendimentoEmpreendedorDAO;
import gerenciador.incubadora.model.entity.Empreendimento;
import gerenciador.incubadora.model.entity.EmpreendimentoEmpreendedor;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmpreendimentoService implements BaseEmpreendimentoService {

    @Override
    public void create(Empreendimento e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            EmpreendimentoDAO dao = new EmpreendimentoDAO();
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
    public Empreendimento readById(Long id) throws Exception {
        Empreendimento empreendimento;
        Connection conn = ConnectionManager.getInstance().getConnection();
        EmpreendimentoDAO dao = new EmpreendimentoDAO();
        empreendimento = dao.readById(id, conn);

        Date dataDefault = Date.valueOf("0001-01-01");
        if (empreendimento.getDataAbertura().equals(dataDefault)) {
            empreendimento.setDataAbertura(null);
        }
        if (empreendimento.getDataIngresso().equals(dataDefault)) {
            empreendimento.setDataIngresso(null);
        }
        if (empreendimento.getDataPrevisaoGraduacao().equals(dataDefault)) {
            empreendimento.setDataPrevisaoGraduacao(null);
        }

        conn.close();
        return empreendimento;
    }

    @Override
    public List<Empreendimento> readByCriteria(Map<String, Object> criteria) throws Exception {
        List<Empreendimento> empreendimentoList;
        Connection conn = ConnectionManager.getInstance().getConnection();
        EmpreendimentoDAO dao = new EmpreendimentoDAO();
        empreendimentoList = dao.readByCriteria(criteria, conn);

        Date dataDefault = Date.valueOf("0001-01-01");
        for (Empreendimento emp : empreendimentoList) {
            if (emp.getDataAbertura().equals(dataDefault)) {
                emp.setDataAbertura(null);
            }
            if (emp.getDataIngresso().equals(dataDefault)) {
                emp.setDataIngresso(null);
            }

            if (emp.getDataPrevisaoGraduacao().equals(dataDefault)) {
                emp.setDataPrevisaoGraduacao(null);
            }
        }

        conn.close();
        return empreendimentoList;
    }

    @Override
    public void update(Empreendimento e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            EmpreendimentoDAO dao = new EmpreendimentoDAO();
            if (e.getLogo() == null) {//Se não houver nenhuma logo, uma imagem default é selecionada
                String logo = Empreendimento.EMPREENDIMENTO_PATH_LOGO_DEFAULT;
                e.setLogo(logo);
            }
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
            EmpreendimentoDAO dao = new EmpreendimentoDAO();
            Empreendimento empreendimento = dao.readById(id, conn);
            String path = empreendimento.getLogo();
//            File file = new File(path);
            dao.delete(id, conn);
//            if (file.delete()) {
//                System.out.println("Imagem excluida com sucesso!");
//            } else {
//                System.out.println("Imagem não foi excluida!");
//            }
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            conn.rollback();
            conn.close();
            throw ex;
        }
    }

    @Override
    public void uplaod(byte[] bytes, Empreendimento empreendimento) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            EmpreendimentoDAO dao = new EmpreendimentoDAO();
            File logo = new File(empreendimento.getLogo());

            Empreendimento emp = dao.readById(empreendimento.getId(), conn);
            String path = logo.getAbsolutePath();
            emp.setLogo(path);

            dao.update(emp, conn);
            conn.commit();
            conn.close();

            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(logo));
            logo.exists();
            stream.write(bytes);
            stream.close();

        } catch (Exception ex) {
            conn.rollback();
            conn.close();
            throw ex;
        }
    }

    @Override
    public InputStream download(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        EmpreendimentoDAO dao = new EmpreendimentoDAO();
        Empreendimento empreendimento = dao.readById(id, conn);
        conn.close();
        InputStream is = null;
        if (!empreendimento.getLogo().isEmpty()) {
            is = new FileInputStream(new File(empreendimento.getLogo()));
        }
        return is;
    }

    public boolean addEmpreendedor(Empreendimento empreendimento) throws Exception {
        boolean sucess = false;
        Connection conn = ConnectionManager.getInstance().getConnection();
        EmpreendimentoDAO dao = new EmpreendimentoDAO();
        sucess = dao.addEmpreendedor(empreendimento, conn);
        conn.commit();
        conn.close();
        return sucess;
    }

    public boolean removeEmpreendedor(Empreendimento empreendimento) throws Exception {

        Connection conn = ConnectionManager.getInstance().getConnection();
        EmpreendimentoDAO dao = new EmpreendimentoDAO();
        boolean sucess = dao.removeEmpreendedor(empreendimento, conn);
        conn.commit();
        conn.close();
        return sucess;
    }

    public void updateStatusEmpreendimento(Long id, String status) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            EmpreendimentoDAO dao = new EmpreendimentoDAO();
            dao.updateStatusEmpreendimento(id, status, conn);
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            conn.rollback();
            conn.close();
            throw ex;
        }

    }

    public void sendEmailStatus(Empreendimento e) throws Exception {
        try {
            //envia um email notificando a mudança de status
            Map<String, Object> criteria = new HashMap<String, Object>();
            criteria.put(EmpreendimentoEmpreendedorDAO.CRITERION_EMPREENDIMENTO_ID, e.getId());
            List<EmpreendimentoEmpreendedor> eeList = ServiceLocator.getEmpreendimentoEmpreendedorService().readByCriteria(criteria);
            String status = e.getStatus();
            String assunto = "Status do Empreendimento Alterado";
            for (EmpreendimentoEmpreendedor aux : eeList) {
                String destino = aux.getEmpreendedor().getEmail();
                String texto = "Olá, " + aux.getEmpreendedor().getNome() + "."
                        + "Viemos por meio deste email, notificar que o empreendimento '" + aux.getEmpreendimento().getNome() + "'"
                        + " ao qual você faz parte, no Sistema Gerenciador da INTEF, teve seu status "
                        + "alterado durante o processo de seleção."
                        + " Acompanhe mais de perto o processo de seleção do seu empreendimento"
                        + " através do nosso sitema."
                        + " O status atual de seu empreendimento é: '" + status + "'."
                        + " Atenciosamente, Gestão da Incubadora.";

                if (status.equals("Apresentação Agendada")) {
                    texto += " A apresentação está agendada para " + e.getDataHoraApresentacao() + " - " + e.getLocalApresentacao() + "."
                            + " Favor acessar o sistema para maiores informações!";
                }
                ServiceLocator.getEmailService().sendEmail(destino, assunto, texto);
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void updateContratacao(Long id, Boolean contratacao) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            EmpreendimentoDAO dao = new EmpreendimentoDAO();

            dao.updateContratacao(id, contratacao, conn);
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            conn.rollback();
            conn.close();
            throw ex;
        }

    }

    public void agendarApresentacao(Long id, Date data, String localApresentacao) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            EmpreendimentoDAO dao = new EmpreendimentoDAO();
            updateStatusEmpreendimento(id, Empreendimento.EMPREENDIMENTO_STATUS_AP_AGENDADA);
            dao.agendarApresentacao(id, data, localApresentacao, conn);
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            conn.rollback();
            conn.close();
            throw ex;
        }

    }

    @Override
    public Map<String, String> validateForCreate(Map<String, Object> fields) throws Exception {
        Map<String, String> errors = new HashMap<>();
        String nome = (String) fields.get("nome");
        if (nome == null || nome.isEmpty()) {
            errors.put("nome", ErrorMessage.CAMPO_OBRIGATORIO);
        }

        Long ramoAtividadeFk = (Long) fields.get("ramoAtividadeFK");
        if (ramoAtividadeFk == null) {
            errors.put("ramoAtividade", ErrorMessage.CAMPO_OBRIGATORIO);
        } else {
            if (ServiceLocator.getRamoAtividade().readById(ramoAtividadeFk) == null) {
                errors.put("ramoAtividade", ErrorMessage.RAMO_ATIVIDADE_INVALIDO);
            }
        }

        Long editalFk = (Long) fields.get("editalFK");
        if (ramoAtividadeFk == null) {
            errors.put("edital", ErrorMessage.CAMPO_OBRIGATORIO);
        } else {
            if (ServiceLocator.getEditalService().readById(editalFk) == null) {
                errors.put("edital", ErrorMessage.EDITAL_INVALIDO);
            }
        }

        return errors;
    }

    @Override
    public Map<String, String> validateForUpdate(Map<String, Object> fields) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, String> validateDataHoraApresentacao(Map<String, Object> fields) throws Exception {
        Map<String, String> errors = new HashMap<>();

        String dia = (String) fields.get("dia");
        if (dia == null || dia.isEmpty()) {
            errors.put("dia", ErrorMessage.CAMPO_OBRIGATORIO);
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                java.util.Date date = formatter.parse(dia);
            } catch (Exception e) {
                errors.put("dia", ErrorMessage.DATA_INVALIDA);
            }
        }

        String hora = (String) fields.get("hora");
        if (hora == null || hora.isEmpty()) {
            errors.put("hora", ErrorMessage.CAMPO_OBRIGATORIO);
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            try {
                java.util.Date date = formatter.parse(hora);
            } catch (Exception e) {
                errors.put("hora", ErrorMessage.HORA_INVALIDA);
            }
        }

        String local = (String) fields.get("local");
        if (local == null || local.isEmpty()) {
            errors.put("lcoal", ErrorMessage.CAMPO_OBRIGATORIO);
        }

        return errors;
    }

}
