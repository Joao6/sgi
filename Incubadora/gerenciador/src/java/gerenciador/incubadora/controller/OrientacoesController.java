package gerenciador.incubadora.controller;

import gerenciador.incubadora.model.ServiceLocator;
import gerenciador.incubadora.model.entity.Categoria;
import gerenciador.incubadora.model.entity.Orientacao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author William
 */
@Controller
public class OrientacoesController {

   @RequestMapping(value = "/incubadora/orientacoes", method = RequestMethod.GET)
   public ModelAndView read() {
      ModelAndView mv;
      try {
         Map<String, Object> criteria = new HashMap<String, Object>();
         List<Orientacao> orientacoes = ServiceLocator.getOrientacaoService().readByCriteria(criteria);
         mv = new ModelAndView("orientacoes/list");
         mv.addObject("orientacoes", orientacoes);
         mv.addObject("orientacoesActive", "active");
      } catch (Exception e) {
         mv = new ModelAndView("error");
         mv.addObject("e", e);
      }
      return mv;
   }

   @RequestMapping(value = "/orientacoes/nova", method = RequestMethod.GET)
   public ModelAndView create() throws Exception {
      ModelAndView mv;
      try {
         Map<String, Object> criteria = new HashMap<String, Object>();
         List<Categoria> categorias = ServiceLocator.getCategoriaService().readByCriteria(criteria);
         mv = new ModelAndView("orientacoes/new");
         mv.addObject("categorias", categorias);
      } catch (Exception e) {
         mv = new ModelAndView("error");
         mv.addObject("e", e);
      }
      return mv;
   }

   @RequestMapping(value = "/orientacoes/nova", method = RequestMethod.POST)
   public ModelAndView create(Orientacao orientacao) {
      ModelAndView mv;
      try {
         ServiceLocator.getOrientacaoService().create(orientacao);
         mv = new ModelAndView("redirect:/incubadora/orientacoes");
      } catch (Exception e) {
         mv = new ModelAndView("error");
         mv.addObject("e", e);
      }
      return mv;
   }

   @RequestMapping(value = "/orientacao/{id}/alterar", method = RequestMethod.GET)
   public ModelAndView update(@PathVariable Long id) {
      ModelAndView mv;
      try {
         Map<String, Object> criteria = new HashMap<String, Object>();
         List<Categoria> categorias = ServiceLocator.getCategoriaService().readByCriteria(criteria);
         Orientacao orientacao = ServiceLocator.getOrientacaoService().readById(id);
         mv = new ModelAndView("orientacoes/new");
         mv.addObject("categorias", categorias);
         mv.addObject("orientacao", orientacao);
      } catch (Exception e) {
         mv = new ModelAndView("error");
         mv.addObject("error", e);
      }
      return mv;
   }

   @RequestMapping(value = "/orientacao/{id}/alterar", method = RequestMethod.POST)
   public ModelAndView update(Orientacao orientacao) {
      ModelAndView mv;
      try {
         ServiceLocator.getOrientacaoService().update(orientacao);
         mv = new ModelAndView("redirect:/incubadora/orientacoes");
      } catch (Exception e) {
         mv = new ModelAndView("error");
         mv.addObject("error", e);
      }
      return mv;
   }

   @RequestMapping(value = "/orientacao/{id}/excluir", method = RequestMethod.GET)
   public ModelAndView delete(@PathVariable Long id) {
      ModelAndView mv;
      try {
         ServiceLocator.getOrientacaoService().delete(id);
         mv = new ModelAndView("redirect:/incubadora/orientacoes");
      } catch (Exception e) {
         mv = new ModelAndView("error");
         mv.addObject("error", e);
      }
      return mv;
   }

}
