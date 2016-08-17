package gerenciador.incubadora.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import gerenciador.incubadora.model.ServiceLocator;
import gerenciador.incubadora.model.entity.CronogramaAnual;
import gerenciador.incubadora.model.entity.Gestor;
import gerenciador.incubadora.model.entity.PraticaChave;
import gerenciador.util.GSONConverter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CronogramaAnualController {

   @RequestMapping(value = "/cronograma-anual/novo", method = RequestMethod.GET)
   public ModelAndView create() {
      ModelAndView mv;
      try {
         //Status
         List<String> statusList = new ArrayList<String>();
         statusList.add(CronogramaAnual.STATUS_REALIZADO);
         statusList.add(CronogramaAnual.STATUS_NAO_REALIZADO);
         //Usuários
         List<Gestor> gestorList = ServiceLocator.getGestorService().readByCriteria(new HashMap<String, Object>());
         //Prática-Chave
         List<PraticaChave> praticaList = ServiceLocator.getPraticaChaveService().readByCriteria(new HashMap<String, Object>());

         mv = new ModelAndView("cronograma-anual/new");
         mv.addObject("statusList", statusList);
         mv.addObject("gestorList", gestorList);
         mv.addObject("praticaList", praticaList);
      } catch (Exception e) {
         mv = new ModelAndView("error");
         mv.addObject("e", e);
      }
      return mv;
   }

   @RequestMapping(value = "/cronograma-anual/novo", method = RequestMethod.POST)
   @ResponseBody
   public void create(@RequestBody String atividade, HttpServletResponse response) {

      try {
         Type type = new TypeToken<CronogramaAnual>() {
         }.getType();

         CronogramaAnual ca = (CronogramaAnual) GSONConverter.convert(atividade, type);

         ServiceLocator.getCronogramaAnualService().create(ca);
         response.setStatus(200);

      } catch (Exception e) {
         response.setStatus(500);
         e.printStackTrace();
      }

   }

   @RequestMapping(value = "/cronograma-anual/atividade/{id}/alterar", method = RequestMethod.GET)
   public ModelAndView update(@PathVariable Long id) {
      ModelAndView mv;
      try {
         CronogramaAnual atividade = ServiceLocator.getCronogramaAnualService().readById(id);

         mv = new ModelAndView("cronograma-anual/new");
         mv.addObject("atividade", atividade);
      } catch (Exception e) {
         mv = new ModelAndView("error");
         mv.addObject("e", e);
      }
      return mv;
   }

   @RequestMapping(value = "/cronograma-anual/alterar", method = RequestMethod.POST)
   @ResponseBody
   public void update(@RequestBody String cronogramaNew, HttpServletResponse response) {
      try {
         Type type = new TypeToken<CronogramaAnual>(){}.getType();
         Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
         CronogramaAnual cronograma = g.fromJson(cronogramaNew, type);
         ServiceLocator.getCronogramaAnualService().update(cronograma);
         response.setStatus(200);
      } catch (Exception e) {
         response.setStatus(500);
        e.printStackTrace();
      }
   }

   @RequestMapping(value = "/remover/atividade/{id}", method = RequestMethod.POST)
   @ResponseBody
   public void removerAtividade(@RequestBody @PathVariable Long id, HttpServletResponse response) {

      try {
         ServiceLocator.getCronogramaAnualService().delete(id);
         response.setStatus(200);
      } catch (Exception e) {
         response.setStatus(500);

      }

   }

   @RequestMapping(value = "/cronograma-anual/atividade/{id}", method = RequestMethod.GET)
   @ResponseBody
   public String getAtividadeById(@RequestBody @PathVariable Long id, HttpServletResponse response){
      String atividadeJson = null;
      try {
         CronogramaAnual atividade = ServiceLocator.getCronogramaAnualService().readById(id);
         Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
         atividadeJson = g.toJson(atividade);
         response.setStatus(200);
      } catch (Exception e) {
         response.setStatus(500);
      }
      return atividadeJson;
   }
   
   @RequestMapping(value = "/cronograma-anual/{id}/excluir", method = RequestMethod.GET)
   public ModelAndView delete(@PathVariable Long id) {
      ModelAndView mv;
      try {
         ServiceLocator.getCronogramaAnualService().delete(id);
         mv = new ModelAndView("redirect:/incubadora/cronograma-anual");
      } catch (Exception e) {
         mv = new ModelAndView("error");
         mv.addObject("e", e);
      }
      return mv;
   }

   @RequestMapping(value = "/cronograma-anual/{id}/detalhes", method = RequestMethod.GET)
   public ModelAndView readDetail(@PathVariable Long id) {
      ModelAndView mv;
      try {
         CronogramaAnual cronograma = ServiceLocator.getCronogramaAnualService().readById(id);
         mv = new ModelAndView("cronograma-anual/detail");
         mv.addObject("cronograma", cronograma);
      } catch (Exception ex) {
         mv = new ModelAndView("error");
         mv.addObject("e", ex);
      }
      return mv;
   }

}
