/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.incubadora.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gerenciador.incubadora.model.ServiceLocator;
import gerenciador.incubadora.model.dao.EditalDAO;
import gerenciador.incubadora.model.entity.Edital;
import gerenciador.incubadora.model.entity.Gestor;
import gerenciador.incubadora.model.entity.Usuario;
import gerenciador.incubadora.model.service.DateService;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Edgard
 */
@Controller
public class EditalController {

   @RequestMapping(value = "/incubadora/edital/novo", method = RequestMethod.GET)
   public ModelAndView create() {
      ModelAndView mv = new ModelAndView("edital/new");
      mv.addObject("editalAcitive", "active");
      return mv;
   }

   @RequestMapping(value = "/incubadora/edital/novo", method = RequestMethod.POST)
   public ModelAndView create(Edital edital, String dataInicial, String dataFinal, @RequestParam("path") MultipartFile file, HttpSession session) {
      ModelAndView mv;
      try {
         edital.setDataInicio(DateService.parseDate(dataInicial, "yyyy-MM-dd"));
         edital.setDataFim(DateService.parseDate(dataFinal, "yyyy-MM-dd"));
         if (file != null && (!file.isEmpty())) {

            //Trocar pelo caminho do servidor
            String destination = "C:/sgi/edital/" + edital.getNome() + ".pdf";
            edital.setPathArquivo(destination.replace("/", "\\\\"));

            File arquivo = new File(destination);
            file.transferTo(arquivo);
         }

         Usuario gestor = (Usuario) session.getAttribute("usuarioLogado");
         Gestor g = new Gestor();
         g.setId(gestor.getId());
         edital.setGestor(g);
         ServiceLocator.getEditalService().create(edital);
         mv = new ModelAndView("redirect:/incubadora/editais");
      } catch (Exception e) {
         mv = new ModelAndView("error");
         mv.addObject("e", e);
      }
      return mv;
   }

   @RequestMapping(value = "/incubadora/editais", method = RequestMethod.GET)
   public ModelAndView list() {
      ModelAndView mv = new ModelAndView("edital/list");
      mv.addObject("editalActive", "active");

      List<Edital> editalList;
      try {
         editalList = ServiceLocator.getEditalService().readByCriteria(null);
         mv.addObject("editalList", editalList);
      } catch (Exception ex) {
         Logger.getLogger(EditalController.class.getName()).log(Level.SEVERE, null, ex);
      }

      return mv;
   }

   @RequestMapping(value = "/incubadora/edital", method = RequestMethod.POST)
   public ModelAndView list(String nome, String resumo, String dataInicio,
           String dataFim, HttpSession session, @RequestParam("path") MultipartFile file) {
      //ModelAndView mv = new ModelAndView("redirect:/incubadora/editais");

      ModelAndView mv = null;
      try {
         Map<String, Object> fields = new HashMap<String, Object>();
         fields.put("nome", nome);
         fields.put("resumo", resumo);
         fields.put("dataInicio", dataInicio);
         fields.put("dataFim", dataFim);

         Map<String, String> errors = ServiceLocator.getEditalService().validateForCreate(fields);

         if (errors.isEmpty()) {
            Edital entity = new Edital();
            entity.setNome(nome);
            entity.setResumo(resumo);
            Date d = DateService.parseDate(dataInicio, "dd/MM/yyyy");
            entity.setDataInicio(d);
            d = DateService.parseDate(dataFim, "dd/MM/yyyy");
            entity.setDataFim(d);

            Gestor gestor = new Gestor();
            gestor.setId(((Usuario) session.getAttribute("usuarioLogado")).getId());
            entity.setGestor(gestor);
            ServiceLocator.getEditalService().create(entity);

            if (file != null && (!file.isEmpty())) {
               //Trocar pelo caminho do servidor
               String destination = "C:/sgi/edital/" + entity.getId() + ".pdf";
               entity.setPathArquivo(destination.replace("/", "\\\\"));

               ServiceLocator.getEditalService().update(entity);
               File arquivo = new File(destination);
               file.transferTo(arquivo);
            }

            mv = new ModelAndView("redirect:/incubadora/editais");
         } else {
            mv = new ModelAndView("edital/new");
            mv.addObject("edital", fields);
            mv.addObject("errors", errors);
         }

      } catch (Exception e) {
         mv = new ModelAndView("incubadora/edital/novo");
      }

      return mv;
   }

   @RequestMapping(value = "/incubadora/edital/atualizar/{id}", method = RequestMethod.GET)
   public ModelAndView update(@PathVariable Long id) {
      ModelAndView mv ;
      try {
         Edital edital = ServiceLocator.getEditalService().readById(id);
         mv = new ModelAndView("edital/new");
         mv.addObject("editalAcitive", "active");
         mv.addObject("edital", edital);
      } catch (Exception ex) {
         mv = new ModelAndView("redirect:/incubadora/editais");
      }

      return mv;
   }

   @RequestMapping(value = "/incubadora/edital/atualizar/{id}", method = RequestMethod.POST)
   public ModelAndView update(String id, String nome, String resumo, String dataInicio,
           String dataFim, HttpSession session, @RequestParam("path") MultipartFile file) {
      ModelAndView mv = null;
      try {
         Map<String, Object> fields = new HashMap<String, Object>();
         fields.put("nome", nome);
         fields.put("resumo", resumo);
         fields.put("dataInicio", dataInicio);
         fields.put("dataFim", dataFim);

         Map<String, String> errors = ServiceLocator.getEditalService().validateForCreate(fields);

         if (errors.isEmpty()) {
            Edital entity = new Edital();
            entity.setId(Long.parseLong(id));
            entity.setNome(nome);
            entity.setResumo(resumo);
            entity.setDataInicio(DateService.parseDate(dataInicio, "dd/MM/yyyy"));
            entity.setDataFim(DateService.parseDate(dataFim, "dd/MM/yyyy"));
            Gestor gestor = new Gestor();
            gestor.setId(((Usuario) session.getAttribute("usuarioLogado")).getId());
            entity.setGestor(gestor);
            //entity.setGestor((Gestor) session.getAttribute("usuarioLogado"));

            if (file != null && (!file.isEmpty())) {
               byte[] bytes = file.getBytes();

               //Trocar pelo caminho do servidor
               String destination = "C:/sgi/edital/" + entity.getId() + ".pdf";
               entity.setPathArquivo(destination.replace("/", "\\\\"));

               File arquivo = new File(destination);
               file.transferTo(arquivo);
            }

            ServiceLocator.getEditalService().update(entity);
            mv = new ModelAndView("redirect:/incubadora/editais");
         } else {
            mv = new ModelAndView("incubadora/edital/novo");
            mv.addObject("errors", errors);
            mv.addObject("edital", fields);
         }
      } catch (Exception ex) {
         mv = new ModelAndView("error");
         mv.addObject("e", ex);

      }

      return mv;
   }

   @RequestMapping(value = "/incubadora/edital/{id}/excluir")
   public ModelAndView delete(@PathVariable Long id) {
      ModelAndView mv = new ModelAndView("redirect:/incubadora/editais");
      mv.addObject("editalAcitive", "active");
      try {
         ServiceLocator.getEditalService().delete(id);
      } catch (Exception e) {
      }
      return mv;
   }

   @RequestMapping(value = "/incubadora/edital/{id}/detalhes", method = RequestMethod.GET)
   public ModelAndView getInfo(@PathVariable Long id) {
      ModelAndView mv = null;
      try {
         mv = new ModelAndView("edital/info");
         Edital edital = ServiceLocator.getEditalService().readById(id);
         mv.addObject("edital", edital);
         mv.addObject("editalActive", "active");

      } catch (Exception ex) {
         mv = new ModelAndView("/error");
         mv.addObject("e", ex);
      }

      return mv;
   }

   @RequestMapping(value = "/incubadora/edital/prorrogar", method = RequestMethod.POST)
   public ModelAndView updateProrrogacao(Long id, String dataProrrogacao) {
      ModelAndView mv = null;
      try {
         Edital edital = new Edital();
         edital.setId(id);
         dataProrrogacao = dataProrrogacao.replace("-", "/");
         edital.setDataProrrogacao(new Date(dataProrrogacao));
         ServiceLocator.getEditalService().updateDataProrrogacao(edital);
         mv = new ModelAndView("redirect:/incubadora/edital/" + edital.getId() + "/detalhes");
      } catch (Exception ex) {
         mv = new ModelAndView("error");
         mv.addObject("e", ex);
      }

      return mv;
   }

   @RequestMapping(value = "/edital/aberto/all", method = RequestMethod.GET)
   @ResponseBody
   public String getEditaisAbertos(HttpServletResponse response) {
      String editalList = null;
      try {
         Map<String, Object> criteria = new HashMap<String, Object>();
         criteria.put(EditalDAO.CRITERION_EDITAL_ABERTO, EditalDAO.CRITERION_EDITAL_ABERTO);
         List<Edital> editais = ServiceLocator.getEditalService().readByCriteria(criteria);
         Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
         editalList = g.toJson(editais);
         
         response.setStatus(200);
      } catch (Exception e) {
         response.setStatus(500);
      }
      return editalList;
   }

}
