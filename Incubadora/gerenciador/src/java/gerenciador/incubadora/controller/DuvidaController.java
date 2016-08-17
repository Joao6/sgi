package gerenciador.incubadora.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import gerenciador.incubadora.model.ServiceLocator;
import gerenciador.incubadora.model.dao.DuvidaDAO;
import gerenciador.incubadora.model.entity.Duvida;
import gerenciador.incubadora.model.entity.Empreendedor;
import gerenciador.incubadora.model.entity.Gestor;
import gerenciador.incubadora.model.entity.RespostaDuvida;
import gerenciador.incubadora.model.entity.Usuario;
import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
 * @author Edgard
 */
@Controller
public class DuvidaController {

   @RequestMapping(value = "/gestor/duvidas", method = RequestMethod.GET)
   public ModelAndView getGestorDuvidas() {
      ModelAndView mv = new ModelAndView("/duvidas/gestor/list");
      try {
         Map<String, Object> criteria = new HashMap<String, Object>();

         criteria.put(DuvidaDAO.CRITERION_DATA_HORA_ORDER_BY_ASC, true);
         List<Duvida> duvidaList = ServiceLocator.getDuvidaService().readByCriteria(criteria);

         mv.addObject("duvidaList", duvidaList);

      } catch (Exception ex) {
         mv = new ModelAndView("/error");
         mv.addObject("e", ex);
      }
      return mv;
   }

   @RequestMapping(value = "/empreendedor/duvidas", method = RequestMethod.GET)
   public ModelAndView getEmpreendedorDuvidas(HttpSession session) {
      ModelAndView mv = new ModelAndView("/duvidas/empreendedor/list");
      try {

         Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

         Empreendedor empreendedor = ServiceLocator.getEmpreendedorService().readById(usuario.getId());

         Map<String, Object> criteria = new HashMap<String, Object>();

         if (empreendedor != null) {
            criteria.put(DuvidaDAO.CRITERION_EMPREENDEDOR_EQ, usuario.getId());
         }
         criteria.put(DuvidaDAO.CRITERION_DATA_HORA_ORDER_BY_ASC, true);
         List<Duvida> duvidaList = ServiceLocator.getDuvidaService().readByCriteria(criteria);

         mv.addObject("duvidaList", duvidaList);
         mv.addObject("duvidasActive", "active");
      } catch (Exception ex) {
         mv = new ModelAndView("/error");
         mv.addObject("e", ex);
      }
      return mv;
   }

   @RequestMapping(value = "/gestor/duvidas/all", method = RequestMethod.GET)
   @ResponseBody
   public String getDuvidas(HttpSession session, HttpServletResponse response) {
      String duvidas = null;
      try {

         Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

         Gestor gestor = ServiceLocator.getGestorService().readById(usuario.getId());

         Map<String, Object> criteria = new HashMap<String, Object>();
         criteria.put(DuvidaDAO.CRITERION_DATA_HORA_ORDER_BY_ASC, true);

         if (gestor != null) {

            List<Duvida> duvidaList = ServiceLocator.getDuvidaService().readByCriteria(criteria);

            Gson g = new Gson();

            duvidas = g.toJson(duvidaList);

            response.setStatus(200);
         } else {
            response.setStatus(400);
         }

      } catch (Exception ex) {
         response.setStatus(500);
      }
      return duvidas;
   }

   @RequestMapping(value = "/duvidas/news", method = RequestMethod.GET)
   @ResponseBody
   public String getAllDuvidas(HttpServletResponse response) {
      String duvidas = null;
      try {

         Map<String, Object> criteria = new HashMap<String, Object>();
         criteria.put(DuvidaDAO.CRITERION_DATA_HORA_ORDER_BY_ASC, true);
         List<Duvida> duvidaList = ServiceLocator.getDuvidaService().readByCriteria(criteria);
         int countNewDuvidas = 0;

         for (Duvida d : duvidaList) {
            if (d.getRespostaDuvidaList().isEmpty()) {
               countNewDuvidas += 1;
            }
         }
         criteria = new HashMap<String, Object>();
         criteria.put("quantidade", countNewDuvidas);
         Gson g = new Gson();

         duvidas = g.toJson(criteria);

         response.setStatus(200);
      } catch (Exception ex) {
         response.setStatus(500);
         ex.printStackTrace();
      }
      return duvidas;
   }

   @RequestMapping(value = "/add/duvida", method = RequestMethod.POST)
   @ResponseBody
   public void create(@RequestBody String pergunta, HttpSession session, HttpServletResponse response) {

      try {
         Map<String, Object> fields = new HashMap<String, Object>();
         fields.put("pergunta", pergunta);

         Map<String, String> errors = ServiceLocator.getDuvidaService().validateForCreate(fields);
         if (errors.isEmpty()) {
            Duvida duvida = new Duvida();
            duvida.setDuvida(pergunta);
            Empreendedor empreendedor = new Empreendedor();
            empreendedor.setId(((Usuario) session.getAttribute("usuarioLogado")).getId());
            duvida.setEmpreendedor(empreendedor);
            duvida.setDataHora(new Date());
            ServiceLocator.getDuvidaService().create(duvida);
            response.setStatus(200);

         } else {
            response.setStatus(500);
         }

      } catch (Exception exception) {
         exception.printStackTrace();
      }

   }

   @RequestMapping(value = "/responder/duvida", method = RequestMethod.POST)
   @ResponseBody
   public void responder(@RequestBody String respostaDuvida, HttpSession session, HttpServletResponse response) {

      try {

         Type type = new TypeToken<RespostaDuvida>() {
         }.getType();
         Gson g = new Gson();
         RespostaDuvida resposta = g.fromJson(respostaDuvida, type);

         Calendar c = new GregorianCalendar();
         resposta.setDataHora(c.getTime());

         ServiceLocator.getRespostaDuvidaService().create(resposta);

         response.setStatus(200);

      } catch (Exception e) {
         e.printStackTrace();
         response.setStatus(500);
      }

   }

   @RequestMapping(value = "/remover/duvida/{idDuvida}", method = RequestMethod.GET)
   @ResponseBody
   public void removerDuvidas(@RequestBody @PathVariable String idDuvida, HttpServletResponse response) {

      try {
         Long id = Long.parseLong(idDuvida);
         ServiceLocator.getDuvidaService().delete(id);
         response.setStatus(200);
      } catch (Exception e) {
         response.setStatus(500);
      }
   }

   @RequestMapping(value = "/remover/resposta", method = RequestMethod.POST)
   @ResponseBody
   public void removerResposta(@RequestBody String idResposta, HttpServletResponse response) {
      try {
         Long id = Long.parseLong(idResposta);
         ServiceLocator.getRespostaDuvidaService().delete(id);

         RespostaDuvida rd = ServiceLocator.getRespostaDuvidaService().readById(id);
         if (rd == null) {
            response.setStatus(200);
         } else {
            response.setStatus(400);
         }

      } catch (Exception e) {
         e.printStackTrace();
         response.setStatus(500);
      }

   }

   @RequestMapping(value = "/atualizar/resposta", method = RequestMethod.POST)
   @ResponseBody
   public void updateResposta(@RequestBody String resposta, HttpServletResponse response) {
      try {
         Type type = new TypeToken<RespostaDuvida>() {
         }.getType();
         Gson g = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
         RespostaDuvida rd = g.fromJson(resposta, type);

         ServiceLocator.getRespostaDuvidaService().update(rd);
         response.setStatus(200);

      } catch (Exception e) {
         e.printStackTrace();
         response.setStatus(500);
      }
   }
   
   @RequestMapping(value = "/duvida/connection", method = RequestMethod.GET)
   @ResponseBody
   public String getConnection(HttpServletResponse response){
      response.setStatus(200);
      return "/websocket";
   }
}
