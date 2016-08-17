package gerenciador.incubadora.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import gerenciador.incubadora.model.ServiceLocator;
import gerenciador.incubadora.model.dao.AvaliadorDAO;
import gerenciador.incubadora.model.dao.EmpreendimentoDAO;
import gerenciador.incubadora.model.entity.Avaliador;
import gerenciador.incubadora.model.entity.Empreendimento;
import gerenciador.incubadora.model.entity.Usuario;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Edgard
 */
@Controller
public class AvaliadorController {

    @RequestMapping(value = "/avaliador/home", method = RequestMethod.GET)
    public ModelAndView goHome() {
        ModelAndView mv;
        try {
            mv = new ModelAndView("usuario/avaliador/home");
        } catch (Exception e) {
            mv = new ModelAndView("error");
            mv.addObject("e", e);

        }
        return mv;
    }

    @RequestMapping(value = "/incubadora/avaliadores", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView mv = null;

        try {
            List<Avaliador> avaliadorList = ServiceLocator.getAvaliadorService().readByCriteria(null);
            mv = new ModelAndView("/usuario/avaliador/list");
            mv.addObject("avaliadorActive", "active");
            mv.addObject("avaliadorList", avaliadorList);
        } catch (Exception ex) {
            mv = new ModelAndView("error");
            mv.addObject("e", ex);
        }

        return mv;
    }

    @RequestMapping(value = "/incubadora/all/avaliadores", method = RequestMethod.GET)
    @ResponseBody
    public String getAvaliadores(HttpServletResponse response) {
        String avaliadores = null;

        try {
            List<Avaliador> avaliadorList = ServiceLocator.getAvaliadorService().readByCriteria(null);
            Gson g = new Gson();
            avaliadores = g.toJson(avaliadorList);
            response.setStatus(200);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(500);
        }

        return avaliadores;
    }

    @RequestMapping(value = "/avaliador/novo", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView mv = new ModelAndView("/usuario/avaliador/new");
        mv.addObject("avaliadorActive", "active");

        return mv;
    }

    @RequestMapping(value = "/avaliador/novo", method = RequestMethod.POST)
    public ModelAndView create(String nome, String sobrenome, String email, String senha, String cpf) {
        ModelAndView mv = null;
        try {
            Map<String, Object> fields = new HashMap<String, Object>();
            fields.put("nome", nome);
            fields.put("sobrenome", sobrenome);
            fields.put("email", email);
            fields.put("senha", senha);
            fields.put("cpf", cpf);

            Map<String, String> errors = ServiceLocator.getAvaliadorService().validateForCreate(fields);
            if (errors.isEmpty()) {
                Avaliador avaliador = new Avaliador();
                avaliador.setCpf(cpf);
                avaliador.setNome(nome);
                avaliador.setSenha(senha);
                avaliador.setEmail(email);
                avaliador.setNome(nome);
                avaliador.setSobrenome(sobrenome);
                avaliador.setTipoUsuario(Usuario.TIPO_USUARIO_AVALIADOR);
                ServiceLocator.getAvaliadorService().create(avaliador);

                mv = new ModelAndView("redirect:/incubadora/avaliadores");
                mv.addObject("avaliadorActive", "active");
            } else {
                mv = new ModelAndView("/usuario/avaliador/new");
                mv.addObject("errors", errors);
            }

        } catch (Exception ex) {
            mv = new ModelAndView("error");
            mv.addObject("e", ex);
        }
        return mv;
    }

    @RequestMapping(value = "/add/avaliador", method = RequestMethod.POST)
    @ResponseBody
    public void addAvaliador(@RequestBody String avaliador, HttpServletResponse response) {

        try {
            Type type = new TypeToken<Avaliador>() {
            }.getType();
            Gson g = new Gson();
            Avaliador a = g.fromJson(avaliador, type);

            ServiceLocator.getAvaliadorService().create(a);
            response.setStatus(200);

        } catch (Exception ex) {
            response.setStatus(500);
            ex.printStackTrace();
        }
    }

    @RequestMapping(value = "/avaliador/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getAvaliadorById(@RequestBody @PathVariable Long id, HttpServletResponse response) {
        String avaliadorJSON = null;
        try {
            Avaliador avaliador = ServiceLocator.getAvaliadorService().readById(id);
            Gson g = new Gson();
            avaliadorJSON = g.toJson(avaliador);
            response.setStatus(200);
        } catch (Exception e) {
            response.setStatus(500);
        }
        return avaliadorJSON;
    }

    @RequestMapping(value = "/avaliador/{id}/atualizar", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable Long id) {
        ModelAndView mv;

        try {
            Avaliador avaliador = ServiceLocator.getAvaliadorService().readById(id);
            mv = new ModelAndView("usuario/avaliador/new");
            mv.addObject("avaliador", avaliador);
        } catch (Exception ex) {
            mv = new ModelAndView("error");
            mv.addObject("e", ex);
        }

        return mv;
    }

    @RequestMapping(value = "/avaliador/atualizar", method = RequestMethod.POST)
    @ResponseBody
    public void update(@RequestBody String avaliador, HttpServletResponse response) {

        try {
            Type type = new TypeToken<Avaliador>() {
            }.getType();

            Gson g = new Gson();

            Avaliador av = (Avaliador) g.fromJson(avaliador, type);

            av.setTipoUsuario(Usuario.TIPO_USUARIO_AVALIADOR);
            ServiceLocator.getAvaliadorService().update(av);
            response.setStatus(200);
        } catch (Exception ex) {
            response.setStatus(500);
            ex.printStackTrace();
        }

    }

    @RequestMapping(value = "/avaliador/{id}/excluir", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable Long id) {
        ModelAndView mv = null;

        try {
            ServiceLocator.getAvaliadorService().delete(id);
            mv = new ModelAndView("redirect:/incubadora/avaliadores");
        } catch (Exception ex) {
            mv = new ModelAndView("error");
            mv.addObject("e", ex);
        }

        return mv;
    }

    @RequestMapping(value = "/avaliador/remover/{idAvaliador}", method = RequestMethod.GET)
    @ResponseBody
    public void removerAvaliador(@RequestBody @PathVariable String idAvaliador, HttpServletResponse response) {
        try {
            Long id = Long.parseLong(idAvaliador);
            ServiceLocator.getAvaliadorService().delete(id);
            response.setStatus(200);

        } catch (Exception ex) {
            response.setStatus(500);
        }
    }

    @RequestMapping(value = "/avaliador/check/cpf", method = RequestMethod.POST)
    @ResponseBody
    public void checkCPF(@RequestBody String cpf, HttpServletResponse response) {
        try {
            Map<String, Object> criteria = new HashMap<String, Object>();
            criteria.put(AvaliadorDAO.CRITERION_CPF_EQ, cpf);

            List<Avaliador> avaliadorList = ServiceLocator.getAvaliadorService().readByCriteria(criteria);
            if (avaliadorList != null && !avaliadorList.isEmpty()) {
                response.setStatus(404);
            } else {
                response.setStatus(200);
            }

        } catch (Exception e) {
            response.setStatus(500);
        }
    }

    @RequestMapping(value = "/avaliador/empreendimentos", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getEmpreendimentos(HttpSession session) {
        ModelAndView mv;
        try {
            mv = new ModelAndView("empreendimento/avaliador/list");
            Usuario avaliador = (Usuario) session.getAttribute("usuarioLogado");

            Map<String, Object> criteria = new LinkedHashMap<String, Object>();
            criteria.put(EmpreendimentoDAO.CRITERION_AVALIADOR_ID, avaliador.getId());
            List<Empreendimento> empreendimentoList = ServiceLocator.getEmpreendimentoService().readByCriteria(criteria);
                        
            
            mv.addObject("avaliador", avaliador);
            mv.addObject("empreendimentoList", empreendimentoList);
            
        } catch (Exception e) {
            mv = new ModelAndView("error");
        }
        return mv;
    }

    @RequestMapping(value = "/avaliador/email", method = RequestMethod.POST)
    @ResponseBody
    public String checkEmail(@RequestBody String email, HttpServletResponse response) {
        String result;
        Gson g = new Gson();
        Map<String, Object> criteria = new HashMap<String, Object>();
        try {
            List<Avaliador> avaliadorList = null;
            criteria.put(AvaliadorDAO.CRITERION_EMAIL_ILIKE, email);

            if (!email.equals("")) {
                avaliadorList = ServiceLocator.getAvaliadorService().readByCriteria(criteria);
            }

            if (avaliadorList != null && !avaliadorList.isEmpty()) {
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

    @RequestMapping(value = "/incubadora/avaliador/detalhes", method = RequestMethod.GET)
    public ModelAndView getInfoAvaliador(HttpSession session) {
        ModelAndView mv;

        try {
            mv = new ModelAndView("usuario/avaliador/info");
            Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
            Long id = usuario.getId();
            mv.addObject("usuarioLogado", usuario);
            mv.addObject("candidatoActive", "active");
            Avaliador avaliador = ServiceLocator.getAvaliadorService().readById(id);
            mv.addObject("avaliador", avaliador);
        } catch (Exception e) {
            mv = new ModelAndView("/error");
            mv.addObject("e", e);
        }
        return mv;
    }

}
