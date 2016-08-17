/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.incubadora.model.service;

import gerenciador.incubadora.model.ConnectionManager;
import gerenciador.incubadora.model.ErrorMessage;
import gerenciador.incubadora.model.base.service.BaseEmpreendedorService;
import gerenciador.incubadora.model.dao.EmpreendedorDAO;
import gerenciador.incubadora.model.dao.UsuarioDAO;
import gerenciador.incubadora.model.entity.Empreendedor;
import gerenciador.incubadora.model.entity.Usuario;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author William
 */
public class EmpreendedorService implements BaseEmpreendedorService {

    private static final String ENCODE_UTF_8 = "UTF-8";
    private static final String ENCODE_ISO_8859_1 = "ISO-8859-1";

    @Override
    public void create(Empreendedor e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            EmpreendedorDAO dao = new EmpreendedorDAO();   
            Map<String, Object> criteria = new HashMap<>();
            criteria.put(UsuarioDAO.CRITERION_EMAIL_EQ, e.getEmail());

            UsuarioService us = new UsuarioService();
            List<Usuario> uList = us.readByCriteria(criteria);
            if (uList == null || uList.isEmpty()) {
                dao.create(e, conn);
                conn.commit();
            }
            conn.close();
        } catch (Exception ex) {
            conn.rollback();
            conn.close();
            throw ex;
        }
    }

    @Override
    public Empreendedor readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        EmpreendedorDAO dao = new EmpreendedorDAO();
        Empreendedor empreendedor = dao.readById(id, conn);
        conn.close();
        return empreendedor;
    }

    @Override
    public List<Empreendedor> readByCriteria(Map<String, Object> criteria) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        EmpreendedorDAO dao = new EmpreendedorDAO();
        List<Empreendedor> empreendedorList = dao.readByCriteria(criteria, conn);
        conn.close();
        return empreendedorList;
    }

    @Override
    public void update(Empreendedor e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            EmpreendedorDAO dao = new EmpreendedorDAO();         
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
            EmpreendedorDAO dao = new EmpreendedorDAO();
            dao.delete(id, conn);
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

        String sobrenome = (String) fields.get("sobrenome");
        if (sobrenome == null || sobrenome.isEmpty()) {
            errors.put("sobrenome", ErrorMessage.CAMPO_OBRIGATORIO);
        }

        String rg = (String) fields.get("rg");
        if (rg == null || rg.isEmpty()) {
            errors.put("rg", ErrorMessage.CAMPO_OBRIGATORIO);
        }
        
        String cpf = (String) fields.get("cpf");
        if(cpf == null || cpf.isEmpty()){
            errors.put("cpf", ErrorMessage.CAMPO_OBRIGATORIO);
        }    
        
        String dataNascimento = (String) fields.get("dataNascimento");
        if(dataNascimento == null || dataNascimento.isEmpty()){
            errors.put("dataNascimento", ErrorMessage.CAMPO_OBRIGATORIO);
        }else{
            try {
                DateService.parseDate(dataNascimento, "dd/MM/yyyy");
            } catch (Exception e) {
                errors.put("dataNascimento", ErrorMessage.DATA_INVALIDA);
            }
        }
        
        String escolaridade = (String) fields.get("escolaridade");
        if(escolaridade == null || escolaridade.isEmpty()){
            errors.put("escolaridade", ErrorMessage.CAMPO_OBRIGATORIO);
        }

        String uf = (String) fields.get("uf");
        if(uf == null || uf.isEmpty()){
            errors.put("uf", ErrorMessage.CAMPO_OBRIGATORIO);
        }
        
        String cidade = (String) fields.get("cidade");
        if(cidade == null || cidade.isEmpty()){
            errors.put("cidade", ErrorMessage.CAMPO_OBRIGATORIO);
        }
        
        String bairro = (String) fields.get("bairro");
        if(bairro == null || bairro.isEmpty()){
            errors.put("bairro", ErrorMessage.CAMPO_OBRIGATORIO);
        }
        
        String rua = (String) fields.get("rua");
        if(rua == null || rua.isEmpty()){
            errors.put("rua", ErrorMessage.CAMPO_OBRIGATORIO);
        }
        
        String numero = (String) fields.get("numero");
        if(numero == null || numero.isEmpty()){
            errors.put("numero", ErrorMessage.CAMPO_OBRIGATORIO);
        }else{
            try {
                Integer.parseInt(numero);
            } catch (NumberFormatException numberFormatException) {
                errors.put("numero", ErrorMessage.DEVE_SER_NUMERO);
            }
        }
        
        String email = (String) fields.get("email");
        if(email == null || email.isEmpty()){
            errors.put("email", ErrorMessage.CAMPO_OBRIGATORIO);
        }
        
        String telefone = (String) fields.get("telefone");
        if(telefone == null || telefone.isEmpty()){
            errors.put("telefone", ErrorMessage.CAMPO_OBRIGATORIO);
        }
        
        String senha = (String) fields.get("senha");
        if(senha == null || senha.isEmpty()){
            errors.put("senha", ErrorMessage.CAMPO_OBRIGATORIO);
        }else{
            if(senha.length() < ErrorMessage.TAMANHO_MINIMO_SENHA){
                errors.put("senha", ErrorMessage.SENHA_CURTA);
            }
        }
        

        return errors;
    }

    @Override
    public Map<String, String> validateForUpdate(Map<String, Object> fields) throws Exception {
        Map<String, String> errors = validateForCreate(fields);
        errors.remove("senha");
        
        return errors;
    }

}
