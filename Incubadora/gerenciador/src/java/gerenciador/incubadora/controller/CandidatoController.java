package gerenciador.incubadora.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import gerenciador.incubadora.model.ServiceLocator;
import gerenciador.incubadora.model.dao.EmpreendedorDAO;
import gerenciador.incubadora.model.entity.Empreendedor;
import gerenciador.incubadora.model.entity.Endereco;
import gerenciador.incubadora.model.entity.Usuario;
import gerenciador.incubadora.model.service.DateService;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CandidatoController {

    @RequestMapping(value = "/incubadora/candidatos", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView mv = null;
        try {
            mv = new ModelAndView("/usuario/candidato/list");
            mv.addObject("candidatoActive", "active");
            Map<String, Object> criteria = new HashMap<String, Object>();
            List<Empreendedor> empreendedorList = ServiceLocator.getEmpreendedorService().readByCriteria(criteria);
            mv.addObject("empreendedorList", empreendedorList);

        } catch (Exception e) {
            mv = new ModelAndView("/error");
            mv.addObject("e", e);

        }

        return mv;
    }

    @RequestMapping(value = "/incubadora/candidato/novo", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView mv = new ModelAndView("/usuario/candidato/new");
        return mv;
    }

    @RequestMapping(value = "/candidato/novo/api", method = RequestMethod.POST)
    @ResponseBody
    public String create(@RequestBody String candidato, HttpServletResponse response) {
        String erros = null;
        try {

            Type type = new TypeToken<Empreendedor>() {
            }.getType();
            Gson g = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();

            Empreendedor candidatoNew = g.fromJson(candidato, type);
            Map<String, Object> fields = new HashMap<String, Object>();
            if (candidatoNew != null) {
                // TODO validação de backend
                //  Map<String, String> errors = ServiceLocator.getEmpreendedorService().validateForCreate(fields);
                
                //criptografando a senha em MD5
//                candidatoNew.setSenha(convertPasswordToMD5(candidatoNew.getSenha()));
                ServiceLocator.getEmpreendedorService().create(candidatoNew);
                response.setStatus(200);
            }
        } catch (Exception e) {
            response.setStatus(500);
            System.out.println(e.getCause());
        }
        return erros;
    }

    /* Serviços */

    @RequestMapping(value = "/candidato/email", method = RequestMethod.POST)
    @ResponseBody
    public String checkEmail(@RequestBody String email, HttpServletResponse response) {
        String result;
        Gson g = new Gson();
        Map<String, Object> criteria = new HashMap<String, Object>();
        try {
            List<Empreendedor> empreendedorList = null;
            criteria.put(EmpreendedorDAO.CRITERION_EMAIL_ILIKE, email);

            if (!email.equals("")) {
                empreendedorList = ServiceLocator.getEmpreendedorService().readByCriteria(criteria);
            }

            if (empreendedorList != null && !empreendedorList.isEmpty()) {
                criteria = new HashMap<String, Object>();
                criteria.put("result", "exist");
                result = g.toJson(criteria);
            } else {
                criteria = new HashMap<String, Object>();
                criteria.put("result", "not");
                result = g.toJson(criteria);
            }
            response.setStatus(200);

        } catch (Exception e) {
            response.setStatus(500);
            e.printStackTrace();
            criteria.put("result", "null");
            result = g.toJson(criteria);
        }
        return result;
    }

    @RequestMapping(value = "/incubadora/candidato/detalhes", method = RequestMethod.GET)
    public ModelAndView getInfoCandidato(HttpSession session) {
        ModelAndView mv;

        try {
            mv = new ModelAndView("usuario/candidato/info");
            Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
            Long id = usuario.getId();
            mv.addObject("usuarioLogado", usuario);
            mv.addObject("candidatoActive", "active");
            Empreendedor empreendedor = ServiceLocator.getEmpreendedorService().readById(id);
            mv.addObject("empreendedor", empreendedor);
        } catch (Exception e) {
            mv = new ModelAndView("/error");
            mv.addObject("e", e);
        }
        return mv;
    }

    @RequestMapping(value = "/incubadora/candidato/{id}/detalhes", method = RequestMethod.POST)
    public ModelAndView update(Long id, String nome, String sobrenome, String rg, String cpf,
            String dataNascimento, String escolaridade, String formacaoProfissional, String ocupacao,
            String uf, String cidade, String bairro, String rua, String numero, String email, String telefone, String fax, String senha) {
        ModelAndView mv;
        try {
            Map<String, Object> fields = new HashMap<String, Object>();
            fields.put("nome", nome);
            fields.put("sobrenome", sobrenome);
            fields.put("rg", rg);
            fields.put("cpf", cpf);
            fields.put("dataNascimento", dataNascimento);
            fields.put("escolaridade", escolaridade);
            fields.put("formacaoProfissional", formacaoProfissional);
            fields.put("ocupacao", ocupacao);
            fields.put("uf", uf);
            fields.put("cidade", cidade);
            fields.put("bairro", bairro);
            fields.put("rua", rua);
            fields.put("numero", numero);
            fields.put("email", email);
            fields.put("telefone", telefone);
            fields.put("fax", fax);
            fields.put("senha", senha);
            Map<String, String> errors = ServiceLocator.getEmpreendedorService().validateForUpdate(fields);
            if (errors.isEmpty()) {
                Empreendedor empreendedor = new Empreendedor();
                empreendedor.setNome(nome);
                empreendedor.setSobrenome(sobrenome);
                empreendedor.setRg(rg);
                empreendedor.setCpf(cpf);
                empreendedor.setDataNascimento(DateService.parseDate(dataNascimento, "dd/MM/yyyy"));
                empreendedor.setEscolaridade(escolaridade);
                empreendedor.setFormacaoProfissional(formacaoProfissional);
                empreendedor.setOcupacao(ocupacao);
                empreendedor.setId(id);

                Endereco endereco = new Endereco();
                endereco.setUf(uf);
                endereco.setCidade(cidade);
                endereco.setBairro(bairro);
                endereco.setRua(rua);
                endereco.setNumero(numero);
                empreendedor.setEndereco(endereco);

                empreendedor.setEmail(email);
                empreendedor.setSenha(senha);
                empreendedor.setTelefone(telefone);
                empreendedor.setFax(fax);

                ServiceLocator.getEmpreendedorService().update(empreendedor);

                mv = new ModelAndView("redirect:/incubadora/candidato/{id}/detalhes");
            } else {
                mv = new ModelAndView("usuario/candidato/info");
                mv.addObject("errors", errors);
                Endereco endereco = new Endereco();
                endereco.setUf(uf);
                endereco.setCidade(cidade);
                endereco.setBairro(bairro);
                endereco.setRua(rua);
                endereco.setNumero(numero);
                fields.put("endereco", endereco);
                try {
                    fields.put("dataNascimento", DateService.parseDate(dataNascimento, "dd/MM/yyyy"));
                } catch (ParseException parseException) {
                }
                mv.addObject("empreendedor", fields);

            }
        } catch (Exception e) {
            mv = new ModelAndView("/error");
            mv.addObject("e", e);
        }

        return mv;
    }
    
    public static String convertPasswordToMD5(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
 
        BigInteger hash = new BigInteger(1, md.digest(password.getBytes()));
 
        return String.format("%32x", hash);
    }
}
