package gerenciador.incubadora.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import gerenciador.incubadora.model.ServiceLocator;
import gerenciador.incubadora.model.dao.CronogramaAnualDAO;
import gerenciador.incubadora.model.dao.EmpreendimentoDAO;
import gerenciador.incubadora.model.dao.EmpreendimentoEmpreendedorDAO;
import gerenciador.incubadora.model.dao.GestorDAO;
import gerenciador.incubadora.model.entity.CronogramaAnual;
import gerenciador.incubadora.model.entity.Empreendedor;
import gerenciador.incubadora.model.entity.Empreendimento;
import gerenciador.incubadora.model.entity.EmpreendimentoEmpreendedor;
import gerenciador.incubadora.model.entity.Gestor;
import gerenciador.incubadora.model.entity.PraticaChave;
import gerenciador.incubadora.model.entity.ProcessoChave;
import gerenciador.incubadora.model.entity.Usuario;
import java.lang.reflect.Type;
import java.util.HashMap;
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
 * @author Aliane Leal
 */
@Controller
public class IncubadoraController {

    @RequestMapping(value = "/incubadora/home", method = RequestMethod.GET)
    public ModelAndView goHome() {
        ModelAndView mv = new ModelAndView("usuario/incubadora/home");
        return mv;
    }

    @RequestMapping(value = "/incubadora/novo", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView mv;
        try {
            mv = new ModelAndView("usuario/incubadora/new");
        } catch (Exception e) {
            mv = new ModelAndView("error");
            mv.addObject("e", e);
        }
        return mv;
    }

    @RequestMapping(value = "/incubadora/novo", method = RequestMethod.POST)
    public ModelAndView create(Gestor incubadora) {
        ModelAndView mv;
        try {
            ServiceLocator.getGestorService().create(incubadora);
            mv = new ModelAndView("redirect:/incubadora");
        } catch (Exception ex) {
            mv = new ModelAndView("error");
            mv.addObject("e", ex);
        }
        return mv;
    }

    @RequestMapping(value = "/incubadora/add/gestor", method = RequestMethod.POST)
    @ResponseBody
    public void addGestor(@RequestBody String gestor, HttpServletResponse response) {
        try {

            Gson g = new Gson();
            Type type = new TypeToken<Gestor>() {
            }.getType();

            Gestor gestorIncubadora = g.fromJson(gestor, type);
            ServiceLocator.getGestorService().create(gestorIncubadora);
            response.setStatus(200);
        } catch (Exception ex) {
            response.setStatus(500);
            ex.printStackTrace();
        }
    }

    @RequestMapping(value = "/incubadora/edit/gestor/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getGestorById(@RequestBody @PathVariable Long id, HttpServletResponse response) {
        String gestorToEdit = null;
        try {
            Gestor gestor = ServiceLocator.getGestorService().readById(id);
            if (gestor != null) {
                Gson g = new Gson();
                gestorToEdit = g.toJson(gestor);
                response.setStatus(200);
            } else {
                response.setStatus(406);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.setStatus(500);
        }
        return gestorToEdit;
    }

    @RequestMapping(value = "/incubadora/edit/gestor", method = RequestMethod.POST)
    @ResponseBody
    public void editarGestor(@RequestBody String gestor, HttpServletResponse response) {
        try {
            Type type = new TypeToken<Gestor>() {
            }.getType();
            Gson g = new Gson();
            Gestor newGestor = g.fromJson(gestor, type);
            if (newGestor != null) {
                ServiceLocator.getGestorService().update(newGestor);
                response.setStatus(200);
            } else {
                response.setStatus(500);
            }

        } catch (Exception e) {
            response.setStatus(500);
        }

    }

    @RequestMapping(value = "/incubadora", method = RequestMethod.GET)
    public ModelAndView read() {
        Map<String, Object> criteria = new HashMap<String, Object>();
        ModelAndView mv;
        try {
            List<Gestor> gestorList = ServiceLocator.getGestorService().readByCriteria(criteria);
            mv = new ModelAndView("usuario/incubadora/list");
            mv.addObject("gestorList", gestorList);
        } catch (Exception ex) {
            mv = new ModelAndView("error");
            mv.addObject("e", ex);
        }
        return mv;
    }

    @RequestMapping(value = "/incubadora/gestores", method = RequestMethod.GET)
    @ResponseBody
    public String getGestores(HttpServletResponse response) {
        Map<String, Object> criteria = new HashMap<String, Object>();
        String gestores = null;
        try {
            List<Gestor> gestorList = ServiceLocator.getGestorService().readByCriteria(criteria);
            Gson g = new Gson();
            gestores = g.toJson(gestorList);

            response.setStatus(200);
        } catch (Exception ex) {
            response.setStatus(500);
            ex.printStackTrace();
        }
        return gestores;
    }

    @RequestMapping(value = "/incubadora/gestor/email", method = RequestMethod.POST)
    @ResponseBody
    public String checkEmail(@RequestBody String email, HttpServletResponse response) {
        String result;
        Gson g = new Gson();
        Map<String, Object> criteria = new HashMap<String, Object>();
        try {
            List<Gestor> gestorList = null;
            criteria.put(GestorDAO.CRITERION_EMAIL_ILIKE, email);

            if (!email.equals("")) {
                gestorList = ServiceLocator.getGestorService().readByCriteria(criteria);
            }

            if (gestorList != null && !gestorList.isEmpty()) {
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

    @RequestMapping(value = "/incubadora/gestor/check/cpf", method = RequestMethod.POST)
    @ResponseBody
    public void checkCPF(@RequestBody String cpf, HttpServletResponse response) {
        try {
            Map<String, Object> criteria = new HashMap<String, Object>();
            criteria.put(GestorDAO.CRITERION_CPF_EQ, cpf);

            List<Gestor> gestorList = ServiceLocator.getGestorService().readByCriteria(criteria);
            if (gestorList != null && !gestorList.isEmpty()) {
                response.setStatus(404);
            } else {
                response.setStatus(200);
            }

        } catch (Exception e) {
            response.setStatus(500);
        }
    }

    @RequestMapping(value = "/gestor/{id}/atualizar", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable Long id) throws Exception {
        ModelAndView mv = new ModelAndView("usuario/incubadora/new");
        Gestor gestor = ServiceLocator.getGestorService().readById(id);
        mv.addObject("gestor", gestor);
        return mv;
    }

    @RequestMapping(value = "/gestor/{id}/atualizar", method = RequestMethod.POST)
    public ModelAndView update(Gestor incubadora) {
        ModelAndView mv;
        try {
            ServiceLocator.getGestorService().update(incubadora);
            mv = new ModelAndView("redirect:/incubadora");
        } catch (Exception ex) {
            mv = new ModelAndView("error");
            mv.addObject("e", ex);
        }
        return mv;
    }

    @RequestMapping(value = "/incubadora/excluir/{id}")
    public ModelAndView delete(@PathVariable Long id) throws Exception {
        ServiceLocator.getGestorService().delete(id);
        return new ModelAndView("redirect:/incubadora");
    }

    @RequestMapping(value = "/incubadora/remove/gestor/{id}")
    @ResponseBody
    public void removeGestor(@RequestBody @PathVariable Long id) throws Exception {
        try {
            ServiceLocator.getGestorService().delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/incubadora/empreendedor", method = RequestMethod.GET)
    public ModelAndView readEmpreendedor() {
        Map<String, Object> criteria = new HashMap<String, Object>();

        ModelAndView mv;
        try {

            List<Empreendedor> empreendedorList = ServiceLocator.getEmpreendedorService().readByCriteria(criteria);
            mv = new ModelAndView("usuario/empreendedor/list");
            mv.addObject("empreendedorList", empreendedorList);
            mv.addObject("empreendedorActive", "active");
        } catch (Exception ex) {
            mv = new ModelAndView("error");
            mv.addObject("e", ex);
        }
        return mv;
    }

    @RequestMapping(value = "/incubadora/all/empreendedores", method = RequestMethod.GET)
    @ResponseBody
    public String getAllEmpreendedores(HttpServletResponse response) {
        Map<String, Object> criteria = new HashMap<String, Object>();
        String empreendedores = null;
        try {

            List<Empreendedor> empreendedorList = ServiceLocator.getEmpreendedorService().readByCriteria(criteria);
            Gson g = new Gson();
            empreendedores = g.toJson(empreendedorList);

            response.setStatus(200);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(500);
        }
        return empreendedores;
    }

    @RequestMapping(value = "/incubadora/empreendimento", method = RequestMethod.GET)
    public ModelAndView readEmpreendimento(HttpSession session) {
        Map<String, Object> criteria = new HashMap<String, Object>();
        ModelAndView mv;
        try {
            Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
            if (usuario.getTipoUsuario().equals(Usuario.TIPO_USUARIO_AVALIADOR)) {
                criteria.put(EmpreendimentoDAO.CRITERION_AVALIADOR_ID, usuario.getId());
            }

            List<Empreendimento> empreendimentoList = ServiceLocator
                    .getEmpreendimentoService().readByCriteria(criteria);

            mv = new ModelAndView("empreendimento/gestao/list");
            mv.addObject("empreendimentoList", empreendimentoList);
            mv.addObject("usuarioLogado", (Usuario) session.getAttribute("usuarioLogado"));
        } catch (Exception ex) {
            ex.printStackTrace();
            mv = new ModelAndView("error");
            mv.addObject("e", ex);
        }
        return mv;
    }

    @RequestMapping(value = "/incubadora/empreendimento/reprovar", method = RequestMethod.POST)
    public ModelAndView sendEmailReprovado(Long idEmpreendimento, String motivo, HttpServletResponse response) throws Exception {
        ModelAndView mv;
        Map<String, Object> criteria = new HashMap<String, Object>();
        criteria.put(EmpreendimentoEmpreendedorDAO.CRITERION_EMPREENDIMENTO_ID, idEmpreendimento);
        try{
        List<EmpreendimentoEmpreendedor> eeList = ServiceLocator.getEmpreendimentoEmpreendedorService().readByCriteria(criteria);
        String assunto = "Empreendimento Reprovado";
        
        for(EmpreendimentoEmpreendedor aux : eeList){
            String destino = aux.getEmpreendedor().getEmail(); 
            String texto = "Olá, "+aux.getEmpreendedor().getNome()+"."
                    + "<br/>Viemos por meio deste email, notificar que o empreendimento "+aux.getEmpreendimento().getNome()+""
                    + " ao qual você faz parte, infelizmente não foi aprovado para se juntar à Incubadora da FAI."
                    + "<br/>O motivo pelo qual o empreendimento não foi aprovado segue abaixo."
                    + "<br/><br/><br/>"
                    + "Motivo: "
                    + "<br/>" + motivo;
            ServiceLocator.getEmailService().sendEmail(destino, assunto, texto);
        }
        Empreendimento empreendimento = ServiceLocator.getEmpreendimentoService().readById(idEmpreendimento);
        empreendimento.setStatus("Reprovado");
        ServiceLocator.getEmpreendimentoService().update(empreendimento);
        response.setStatus(200);
        mv = new ModelAndView("redirect:/incubadora/empreendimento");
        }catch(Exception e){
            e.printStackTrace();
            response.setStatus(500);
            mv = new ModelAndView("error");
            mv.addObject("e", e);
        }                                
        return mv;
    }

    @RequestMapping(value = "/incubadora/processo-chave", method = RequestMethod.GET)
    public ModelAndView readProcesso() {
        ModelAndView mv;
        try {
            List<ProcessoChave> processoList = ServiceLocator.getProcessoChaveService()
                    .readByCriteria(new HashMap<String, Object>());
            mv = new ModelAndView("processo-chave/list");
            mv.addObject("processoList", processoList);
            mv.addObject("processoChaveActive", "active");
        } catch (Exception ex) {
            mv = new ModelAndView("error");
            mv.addObject("e", ex);
        }
        return mv;
    }

    @RequestMapping(value = "/incubadora/pratica-chave", method = RequestMethod.GET)
    public ModelAndView readPratica() {
        ModelAndView mv;
        try {
            List<PraticaChave> praticaList = ServiceLocator.getPraticaChaveService()
                    .readByCriteria(new HashMap<String, Object>());
            mv = new ModelAndView("pratica-chave/list");
            mv.addObject("praticaList", praticaList);
        } catch (Exception e) {
            mv = new ModelAndView("error");
            mv.addObject("e", e);
        }
        return mv;
    }

    @RequestMapping(value = "/incubadora/cronograma-anual", method = RequestMethod.GET)
    public ModelAndView readCronograma() {
        ModelAndView mv;
        try {
            Map<String, Object> criteria = new HashMap<String, Object>();
            criteria.put(CronogramaAnualDAO.CRITERION_ORDERBY_DATE_ASC, true);
            List<CronogramaAnual> praticaList = ServiceLocator.getCronogramaAnualService()
                    .readByCriteria(criteria);
            mv = new ModelAndView("cronograma-anual/list");
        } catch (Exception e) {
            mv = new ModelAndView("error");
            mv.addObject("e", e);
        }
        return mv;
    }
}
