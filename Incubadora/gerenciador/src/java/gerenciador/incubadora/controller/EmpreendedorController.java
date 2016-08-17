package gerenciador.incubadora.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import gerenciador.incubadora.model.ServiceLocator;
import gerenciador.incubadora.model.entity.Empreendedor;
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
public class EmpreendedorController {

   @RequestMapping(value = "/empreendedor/home", method = RequestMethod.GET)
   public ModelAndView goHome() {
      return new ModelAndView("usuario/empreendedor/home");

   }

   @RequestMapping(value = "/empreendedor", method = RequestMethod.GET)
   public ModelAndView read() {
      Map<String, Object> criteria = new HashMap<String, Object>();
      ModelAndView mv;
      try {
         List<Empreendedor> empreendedorList = ServiceLocator.getEmpreendedorService()
                 .readByCriteria(criteria);
         mv = new ModelAndView("usuario/empreendedor/list");
         mv.addObject("empreendedorList", empreendedorList);
      } catch (Exception ex) {
         mv = new ModelAndView("error");
         mv.addObject("e", ex);
      }
      return mv;
   }

   @RequestMapping(value = "/empreendedor/novo", method = RequestMethod.POST)
   public ModelAndView create(Empreendedor empreendedor, HttpServletResponse response) {
      ModelAndView mv;
      try {
         ServiceLocator.getEmpreendedorService().create(empreendedor);
         mv = new ModelAndView("redirect:/incubadora/empreendedor");
         response.setStatus(200);
      } catch (Exception ex) {
         mv = new ModelAndView("error");
         mv.addObject("e", ex);
         response.setStatus(500);
      }
      return mv;
   }

   //Serviços
   @RequestMapping(value = "/add/empreendedor", method = RequestMethod.POST)
   @ResponseBody
   public void addEmpreendedor(@RequestBody String empreendedor, HttpServletResponse response) {

      try {
         Gson g = new Gson();
         Type type = new TypeToken<Empreendedor>() {
         }.getType();

         Empreendedor e = g.fromJson(empreendedor, type);
         ServiceLocator.getEmpreendedorService().create(e);

         response.setStatus(200);
      } catch (Exception ex) {

         response.setStatus(500);
      }

   }

   @RequestMapping(value = "/empreendedor/info/api", method = RequestMethod.GET)
   @ResponseBody
   public String getInfo(HttpSession session, HttpServletResponse response) {
      String retorno = null;
      try {
         Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
         if (usuario != null) {
            Long id = usuario.getId();
            Empreendedor empreendedor = ServiceLocator.getEmpreendedorService().readById(id);
            Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

            retorno = g.toJson(empreendedor);

            response.setStatus(200);
         } else {
            response.setStatus(500);
         }

      } catch (Exception e) {
         response.setStatus(500);
      }
      return retorno;
   }

   @RequestMapping(value = "/empreendedor/update/info/api", method = RequestMethod.POST)
   @ResponseBody
   public void updateInfo(@RequestBody String newEmpreendedor, HttpServletResponse response) {
      try {
         Type type = new TypeToken<Empreendedor>() {
         }.getType();
         Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
         Empreendedor empreendedor = g.fromJson(newEmpreendedor, type);

         ServiceLocator.getEmpreendedorService().update(empreendedor);
         response.setStatus(200);

      } catch (Exception e) {
         e.printStackTrace();
         response.setStatus(500);
      }
   }

   @RequestMapping(value = "/empreendedor/novo", method = RequestMethod.GET)
   public ModelAndView create() {
      ModelAndView mv;
      try {
         mv = new ModelAndView("usuario/empreendedor/new");
      } catch (Exception ex) {
         mv = new ModelAndView("error");
         mv.addObject("e", ex);
      }
      return mv;
   }

   @RequestMapping(value = "/empreendedor/atualizar/{id}", method = RequestMethod.GET)
   public ModelAndView update(@PathVariable Long id) throws Exception {
      ModelAndView mv = new ModelAndView("usuario/empreendedor/new");
      Empreendedor empreendedor = ServiceLocator.getEmpreendedorService().readById(id);
      mv.addObject("empreendedor", empreendedor);
      return mv;
   }

   @RequestMapping(value = "/empreendedor/atualizar/{id}", method = RequestMethod.POST)
   public ModelAndView update(Empreendedor empreendedor) {
      ModelAndView mv = null;
      try {
         ServiceLocator.getEmpreendedorService().update(empreendedor);
         mv = new ModelAndView("redirect:/empreendimentos");
      } catch (Exception ex) {
         mv = new ModelAndView("error");
         mv.addObject("erro", ex);
      }
      return mv;
   }

   @RequestMapping(value = "/empreendedor/excluir/{id}")
   public ModelAndView delete(@PathVariable Long id) throws Exception {
      ServiceLocator.getEmpreendedorService().delete(id);
      return new ModelAndView("redirect:/incubadora/empreendedor");
   }

   @RequestMapping(value = "/empreendedor/remover/{idEmpreendedor}", method = RequestMethod.GET)
   @ResponseBody
   public void removeEmpreendedor(@RequestBody @PathVariable String idEmpreendedor, HttpServletResponse response) throws Exception {
      try {

         Long id = Long.parseLong(idEmpreendedor);
         ServiceLocator.getEmpreendedorService().delete(id);
         response.setStatus(200);
      } catch (Exception ex) {
         ex.printStackTrace();
         response.setStatus(500);
      }

   }

   @RequestMapping(value = "/empreendedor/plano-empreendedor", method = RequestMethod.POST)
   public ModelAndView readPlanoEmpreendedor() {
      ModelAndView mv;
      try {
         mv = new ModelAndView("planejamento/plano-empreendedor/list");
      } catch (Exception ex) {
         mv = new ModelAndView("error");
         mv.addObject("e", ex);
      }
      return mv;
   }

   @RequestMapping(value = "/empreendedor/plano-tecnologico", method = RequestMethod.GET)
   public ModelAndView readPlanoTecnologico() {
      ModelAndView mv;
      try {
         mv = new ModelAndView("planejamento/plano-tecnologico/list");
      } catch (Exception ex) {
         mv = new ModelAndView("error");
         mv.addObject("e", ex);
      }
      return mv;
   }

   @RequestMapping(value = "/empreendedor/plano-mercado", method = RequestMethod.GET)
   public ModelAndView readPlanoMercado() {
      ModelAndView mv;
      try {
         mv = new ModelAndView("planejamento/plano-mercado/list");
      } catch (Exception ex) {
         mv = new ModelAndView("error");
         mv.addObject("e", ex);
      }
      return mv;
   }

   @RequestMapping(value = "/empreendedor/plano-capital", method = RequestMethod.GET)
   public ModelAndView readPlanoCapital() {
      ModelAndView mv;
      try {
         mv = new ModelAndView("planejamento/plano-capital/list");
      } catch (Exception ex) {
         mv = new ModelAndView("error");
         mv.addObject("e", ex);
      }
      return mv;
   }

   @RequestMapping(value = "/empreendedor/plano-gestao", method = RequestMethod.GET)
   public ModelAndView readPlanoGestao() {
      ModelAndView mv;
      try {
         mv = new ModelAndView("planejamento/plano-gestao/list");
      } catch (Exception ex) {
         mv = new ModelAndView("error");
         mv.addObject("e", ex);
      }
      return mv;
   }
}
