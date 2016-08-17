package gerenciador.incubadora.controller;

import com.google.gson.Gson;
import gerenciador.incubadora.model.ServiceLocator;
import gerenciador.incubadora.model.entity.RamoAtividade;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RamoAtividadeController {

  @RequestMapping(value = "/ramoAtividade/novo", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mv = null;
    try {
      mv = new ModelAndView("ramoAtividade/new");
    } catch (Exception ex) {

    }
    return mv;
  }

  @RequestMapping(value = "/ramoAtividade/novo", method = RequestMethod.POST)
  public ModelAndView create(RamoAtividade ramoAtividade) {
    ModelAndView mv = null;
    try {
      ServiceLocator.getRamoAtividade().create(ramoAtividade);
      mv = new ModelAndView("redirect:/ramoAtividade");
    } catch (Exception ex) {
      Logger.getLogger(RamoAtividadeController.class.getName()).log(Level.SEVERE, null, ex);
    }

    return mv;
  }

  @RequestMapping(value = "/ramoAtividade", method = RequestMethod.GET)
  public ModelAndView read() {
    Map<String, Object> criteria = new HashMap<String, Object>();
    ModelAndView mv = null;
    List<RamoAtividade> ramoAtividadeList = new ArrayList<RamoAtividade>();
    try {
      ramoAtividadeList = ServiceLocator.getRamoAtividade().readByCriteria(criteria);
      mv = new ModelAndView("ramoAtividade/list");
      mv.addObject("ramoAtividadeList", ramoAtividadeList);
    } catch (Exception ex) {
      Logger.getLogger(RamoAtividadeController.class.getName()).log(Level.SEVERE, null, ex);

    }
    return mv;
  }

  @RequestMapping(value = "/ramoAtividade/{id}/alterar", method = RequestMethod.GET)
  public ModelAndView update(@PathVariable Long id) {
    ModelAndView mv = null;
    RamoAtividade ramoAtividade = null;
    try {
      ramoAtividade = ServiceLocator.getRamoAtividade().readById(id);
      mv = new ModelAndView("ramoAtividade/new");
      mv.addObject("ramoAtividade", ramoAtividade);
    } catch (Exception ex) {
      Logger.getLogger(RamoAtividadeController.class.getName()).log(Level.SEVERE, null, ex);
    }
    return mv;
  }

  @RequestMapping(value = "/ramoAtividade/{id}/alterar", method = RequestMethod.POST)
  public ModelAndView update(RamoAtividade ramoAtividade) {
    ModelAndView mv = null;
    try {
      ServiceLocator.getRamoAtividade().update(ramoAtividade);
      mv = new ModelAndView("redirect:/ramoAtividade");
    } catch (Exception ex) {
      Logger.getLogger(RamoAtividadeController.class.getName()).log(Level.SEVERE, null, ex);
    }

    return mv;
  }

  @RequestMapping(value = "/ramoAtividade/{id}/excluir", method = RequestMethod.GET)
  public ModelAndView delete(@PathVariable Long id) {
    ModelAndView mv = null;
    try {
      ServiceLocator.getRamoAtividade().delete(id);
      mv = new ModelAndView("redirect:/ramoAtividade");
    } catch (Exception ex) {
      Logger.getLogger(RamoAtividadeController.class.getName()).log(Level.SEVERE, null, ex);
    }
    return mv;
  }
  
  
  @RequestMapping(value = "/ramo/atividade/all", method = RequestMethod.GET)
  @ResponseBody
  public String getRamosAtividade(HttpServletResponse response){
     String ramoAtividadeList = null;
     try {
        Map<String, Object> criteria = new HashMap<String, Object>();
        List<RamoAtividade> ramos = ServiceLocator.getRamoAtividade().readByCriteria(criteria);
        
        Gson g = new Gson();
        ramoAtividadeList = g.toJson(ramos);
        response.setStatus(200);
        
     } catch (Exception e) {
        response.setStatus(500);
     }
     return ramoAtividadeList;
  }
  
}
