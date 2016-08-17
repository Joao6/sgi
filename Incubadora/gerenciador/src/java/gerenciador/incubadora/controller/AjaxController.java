package gerenciador.incubadora.controller;

import com.google.gson.Gson;
import gerenciador.incubadora.model.ServiceLocator;
import gerenciador.incubadora.model.dao.CronogramaAnualDAO;
import gerenciador.incubadora.model.dao.GestorDAO;
import gerenciador.incubadora.model.dao.OrientacaoDAO;
import gerenciador.incubadora.model.dao.PraticaChaveDAO;
import gerenciador.incubadora.model.entity.CronogramaAnual;
import gerenciador.incubadora.model.entity.Empreendedor;
import gerenciador.incubadora.model.entity.Empreendimento;
import gerenciador.incubadora.model.entity.Gestor;
import gerenciador.incubadora.model.entity.Orientacao;
import gerenciador.incubadora.model.entity.PraticaChave;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author William
 */
@Controller
public class AjaxController {

   @ResponseBody
   @RequestMapping(value = "/cronograma-anual/{idCronograma}/manager/{nome}/inserir", method = RequestMethod.GET)
   public ModelAndView insertGestor(@RequestBody @PathVariable Long idCronograma,
           @PathVariable String nome, HttpServletResponse response) {
      ModelAndView mv;
      try {
         Map<String, Object> criteria = new HashMap<String, Object>();
         criteria.put(GestorDAO.CRITERION_MENAGER_NAMES_ILIKE, nome);

         List<Gestor> gestorList = ServiceLocator.getGestorService().readByCriteria(criteria);
         CronogramaAnual cronograma = ServiceLocator.getCronogramaAnualService().readById(idCronograma);
         cronograma.setGestorList(gestorList);

         ServiceLocator.getCronogramaManagerService().insertGestor(cronograma);
         mv = new ModelAndView("redirect:/incubadora/cronograma-anual");

      } catch (Exception e) {
         response.setStatus(500);
         mv = new ModelAndView("error");
         mv.addObject("e", e.getMessage());

      }
      return mv;
   }

   @ResponseBody
   @RequestMapping(value = "/cronograma-anual/{idCronograma}/manager/{nome}/excluir",
           method = RequestMethod.GET)
   public ModelAndView removeGestor(@RequestBody @PathVariable Long idCronograma,
           @PathVariable String nome, HttpServletResponse response) {
      ModelAndView mv;
      try {
         Map<String, Object> criteria = new HashMap<String, Object>();
         criteria.put(GestorDAO.CRITERION_MENAGER_NAMES_ILIKE, nome);
         List<Gestor> manager = ServiceLocator.getGestorService().readByCriteria(criteria);
         Long idGestor = manager.get(0).getId();
         ServiceLocator.getCronogramaManagerService().deleteGestor(idCronograma, idGestor);
         response.setStatus(200);
         mv = new ModelAndView("redirect:/cronograma-anual/novo");
      } catch (Exception e) {
         response.setStatus(500);
         mv = new ModelAndView("error");
         mv.addObject("e", e);
      }
      return mv;
   }

   @ResponseBody
   @RequestMapping(value = "/cronograma-anual/{idCronograma}/manager-pratica/{nome}/inserir")
   public ModelAndView insertPratica(@RequestBody @PathVariable Long idCronograma,
           @PathVariable String nome, HttpServletResponse response) {
      ModelAndView mv = null;
      try {
         Map<String, Object> criteria = new HashMap<String, Object>();
         criteria.put(PraticaChaveDAO.CRITERION_PRATICA_NAMES_ILIKE, nome);
         List<PraticaChave> praticaList = ServiceLocator.getPraticaChaveService().readByCriteria(criteria);
         CronogramaAnual cronograma = ServiceLocator.getCronogramaAnualService().readById(idCronograma);
         cronograma.setPraticaList(praticaList);

         ServiceLocator.getCronogramaManagerService().insertPraticaChave(cronograma);
         response.setStatus(200);
      } catch (Exception e) {
         response.setStatus(500);
         mv = new ModelAndView("error");
         mv.addObject("e", e.getMessage());
      }
      return mv;
   }

   @ResponseBody
   @RequestMapping(value = "/cronograma-anual/{idCronograma}/manager-pratica/{nome}/excluir")
   public ModelAndView removePratica(@RequestBody @PathVariable Long idCronograma,
           @PathVariable String nome, HttpServletResponse response) {
      try {
         Map<String, Object> criteria = new HashMap<String, Object>();
         criteria.put(PraticaChaveDAO.CRITERION_PRATICA_NAMES_ILIKE, nome);
         List<PraticaChave> praticaList = ServiceLocator.getPraticaChaveService().readByCriteria(criteria);
         CronogramaAnual cronograma = new CronogramaAnual();
         cronograma.setId(idCronograma);
         cronograma.setPraticaList(praticaList);

         ServiceLocator.getCronogramaManagerService().deletePraticaChave(cronograma);
         response.setStatus(200);
      } catch (Exception e) {
         response.setStatus(500);
      }
      return null;
   }

   @RequestMapping(value = "/empreendimento/{idEmpreendimento}/empreendedor/{idEmpreendedor}/add", method = RequestMethod.GET)
   @ResponseBody
   public String addEmpreendedor(@PathVariable Long idEmpreendimento,
           @PathVariable Long idEmpreendedor, HttpServletResponse response) throws Exception {
      String sucess;
      try {

         Empreendimento empreendimento = new Empreendimento();
         empreendimento.setId(idEmpreendimento);

         Empreendedor empreendedor = new Empreendedor();
         empreendedor.setId(idEmpreendedor);

         List<Empreendedor> empreendedorList = new ArrayList<Empreendedor>();
         empreendedorList.add(empreendedor);

         empreendimento.setEmpreendedorList(empreendedorList);

         ServiceLocator.getEmpreendimentoService().addEmpreendedor(empreendimento);

         sucess = "sucess";
         response.setStatus(200);
      } catch (Exception ex) {
         sucess = null;
         response.setStatus(500);
      }
      return sucess;
   }

   @ResponseBody
   @RequestMapping(value = "/remover/empreendedor/{idEmpreendedor}", method = RequestMethod.GET)
   public void removeEmpreendedor(@PathVariable Long idEmpreendedor) {
      try {
         Empreendimento empreendimento = new Empreendimento();
         Empreendedor empreendedor = new Empreendedor();
         List<Empreendedor> empList = new ArrayList<Empreendedor>();

         empreendedor.setId(idEmpreendedor);
         empList.add(empreendedor);

         empreendimento.setEmpreendedorList(empList);

         ServiceLocator.getEmpreendimentoService().removeEmpreendedor(empreendimento);
      } catch (Exception e) {
         System.out.println("erro: " + e.getMessage());
      }
   }

   @RequestMapping(value = "/empreendedor/{idEmpreendedor}", method = RequestMethod.POST)
   @ResponseBody
   public String getName(@PathVariable Long idEmpreendedor, HttpServletResponse response) {
      String nome;
      try {
         Empreendedor emp = ServiceLocator.getEmpreendedorService().readById(idEmpreendedor);
         if (emp != null) {
            nome = emp.getNome();
         } else {
            nome = null;
         }
         response.setStatus(200);
      } catch (Exception ex) {
         response.setStatus(500);
         nome = null;
      }
      return nome;
   }

   @RequestMapping(value = "/orientacoes/{idCategoria}", method = RequestMethod.GET)
   @ResponseBody
   public String getOrientacao(@PathVariable Long idCategoria, HttpServletResponse response) {
      String orientacao = null;
      try {
         Map<String, Object> criteria = new HashMap<String, Object>();
         criteria.put(OrientacaoDAO.CRITERION_CATEGORIA_ID, idCategoria);
         List<Orientacao> orientacoes = ServiceLocator.getOrientacaoService().readByCriteria(criteria);

         if (orientacoes != null && !orientacoes.isEmpty()) {
            orientacao = orientacoes.get(0).getDescricao();
         }
         response.setStatus(200);
      } catch (Exception e) {
         orientacao = null;
         response.setStatus(500);
      }
      return orientacao;
   }

 
   @RequestMapping(value = "/cronograma-anual/atividades", method = RequestMethod.GET)
   @ResponseBody
   public String getAtividades(HttpServletResponse response) {

      String atividades = null;
      try {
         Map<String, Object> criteria = new HashMap<String, Object>();
         criteria.put(CronogramaAnualDAO.CRITERION_ORDERBY_DATE_ASC, true);
         List<CronogramaAnual> atividadeList = ServiceLocator.getCronogramaAnualService()
                 .readByCriteria(criteria);

         Gson g = new Gson();
         atividades = g.toJson(atividadeList);
         response.setStatus(200);
      } catch (Exception e) {
         response.setStatus(500);
      }
      return atividades;
   }

}
