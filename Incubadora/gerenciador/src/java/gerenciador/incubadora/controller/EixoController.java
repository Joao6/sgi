/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.incubadora.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import gerenciador.incubadora.model.ServiceLocator;
import gerenciador.incubadora.model.entity.Eixo;
import java.lang.reflect.Type;
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
 * @author Edgard
 */
@Controller
public class EixoController {

   @RequestMapping(value = "/eixo/list", method = RequestMethod.GET)
   public ModelAndView list() {
      ModelAndView mv;

      try {
         mv = new ModelAndView("/eixo/list");
      } catch (Exception e) {
         mv = new ModelAndView("/error");
         mv.addObject("e", e);
      }

      return mv;
   }

   /* :::::::::: APIs ::::::::::: */
   /**
    * API para obter os eixos que compões o modelo de avaliação
    *
    * @param response
    * @return String
    */
   @RequestMapping(value = "/eixo/api", method = RequestMethod.GET)
   @ResponseBody
   public String getEixos(HttpServletResponse response) {
      String eixos = null;
      try {
         List<Eixo> eixoList = ServiceLocator.getEixoService().readByCriteria(null);
         if (eixoList != null && !eixoList.isEmpty()) {
            Gson g = new Gson();
            eixos = g.toJson(eixoList);
         }
         response.setStatus(200);
      } catch (Exception e) {
         response.setStatus(500);
      }
      return eixos;
   }

   @RequestMapping(value = "/eixo/novo/api", method = RequestMethod.POST)
   @ResponseBody
   public void create(@RequestBody String eixo, HttpServletResponse response) {

      try {
         Type type = new TypeToken<Eixo>() {
         }.getType();
         Gson g = new Gson();
         Eixo e = g.fromJson(eixo, type);

         Map<String, Object> fields = new HashMap<String, Object>();
         fields.put("nome", e.getNome());
         fields.put("peso", e.getPeso());

         Map<String, String> errors = ServiceLocator.getEixoService().validateForCreate(fields);
         if (errors.isEmpty()) {
            ServiceLocator.getEixoService().create(e);
            response.setStatus(200);
         } else {
            response.setStatus(500);
         }
      } catch (Exception e) {
         response.setStatus(500);
      }
   }

   @RequestMapping(value = "/eixo/update/api", method = RequestMethod.POST)
   @ResponseBody
   public void update(@RequestBody String eixo, HttpServletResponse response) {
      try {
         Type type = new TypeToken<Eixo>() {
         }.getType();
         Gson g = new Gson();

         Eixo e = g.fromJson(eixo, type);
         ServiceLocator.getEixoService().update(e);
         response.setStatus(200);
      } catch (Exception e) {
         response.setStatus(500);
      }

   }

   @RequestMapping(value = "/excluir/eixo/{id}/api", method = RequestMethod.GET)
   @ResponseBody
   public void delete(@PathVariable Long id, HttpServletResponse response) {
      try {
         ServiceLocator.getEixoService().delete(id);
         response.setStatus(200);
      } catch (Exception e) {
         response.setStatus(500);
      }

   }
}
