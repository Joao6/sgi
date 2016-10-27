/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.incubadora.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import gerenciador.incubadora.model.ServiceLocator;
import gerenciador.incubadora.model.dao.CriterioAvaliacaoDAO;
import gerenciador.incubadora.model.entity.CriterioAvaliacao;
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
public class CriterioAvaliacaoController {

    @RequestMapping(value = "/criterio/eixo/{id}/api", method = RequestMethod.GET)
    public ModelAndView list(@PathVariable Long eixoID) {
        ModelAndView mv = null;

        try {
            Map<String, Object> criteria = new HashMap<String, Object>();
            criteria.put(CriterioAvaliacaoDAO.CRITERION_EIXO_ID, eixoID);
            List<CriterioAvaliacao> criterioAvaliacaoList = ServiceLocator.getCriterioAvaliacaoService().readByCriteria(criteria);
            mv = new ModelAndView("criterio-avaliacao/list");
            mv.addObject("criterioAvaliacaoList", criterioAvaliacaoList);

            Eixo eixo = ServiceLocator.getEixoService().readById(eixoID);
            mv.addObject("eixo", eixo);
        } catch (Exception e) {
            mv = new ModelAndView("/error");
            mv.addObject("e", e);
        }

        return mv;
    }

    @RequestMapping(value = "/criterio/api", method = RequestMethod.GET)
    @ResponseBody
    public String getCriterios(HttpServletResponse response) {
        String criterios = null;
        try {
            List<CriterioAvaliacao> criterioAvaliacaoList = ServiceLocator.getCriterioAvaliacaoService().readByCriteria(null);
            Gson g = new Gson();
            criterios = g.toJson(criterioAvaliacaoList);
            response.setStatus(200);
        } catch (Exception e) {
            response.setStatus(500);
        }

        return criterios;
    }

    @RequestMapping(value = "/eixo/add/criterio/api", method = RequestMethod.POST)
    @ResponseBody
    public void create(@RequestBody String criterio, HttpServletResponse response) {
        try {
            Type type = new TypeToken<CriterioAvaliacao>() {
            }.getType();
            Gson g = new Gson();

            CriterioAvaliacao ca = g.fromJson(criterio, type);

            Map<String, Object> fields = new HashMap<String, Object>();
            fields.put("nome", ca.getNome());
            Map<String, String> errors = ServiceLocator.getCriterioAvaliacaoService().validateForCreate(fields);
            if (errors.isEmpty()) {
                ca.setAtivo(true);
                ServiceLocator.getCriterioAvaliacaoService().create(ca);
                response.setStatus(200);
            } else {
                response.setStatus(500);
            }
        } catch (Exception e) {
            response.setStatus(500);
        }
    }

    //////getCriterioById aqui
    @RequestMapping(value = "/criterio/{idCriterio}", method = RequestMethod.GET)
    @ResponseBody
    public String getCriterioById(@PathVariable Long idCriterio, HttpServletResponse response) {
        String criterio = null;
        try {
            CriterioAvaliacao getCriterio = ServiceLocator.getCriterioAvaliacaoService().readById(idCriterio);
            Gson g = new Gson();
            criterio = g.toJson(getCriterio);
            response.setStatus(200);
        } catch (Exception e) {
            response.setStatus(500);
        }

        return criterio;
    }

    @RequestMapping(value = "/criterio/save/alteracoes", method = RequestMethod.POST)
    @ResponseBody
    public void saveAlteracoes(@RequestBody String criterioList, HttpServletResponse response) {
        try {
            Type type = new TypeToken<List<CriterioAvaliacao>>() {
            }.getType();
            Gson g = new Gson();

            List<CriterioAvaliacao> caList = g.fromJson(criterioList, type);

            for (CriterioAvaliacao aux : caList) {
                ServiceLocator.getCriterioAvaliacaoService().update(aux);
            }
            
        } catch (Exception e) {
            response.setStatus(500);
        }
    }
    
    @RequestMapping(value = "/criterio/save", method = RequestMethod.POST)
    @ResponseBody
    public void saveCriterio(@RequestBody String criterio, HttpServletResponse response) {
        try {
            Type type = new TypeToken<CriterioAvaliacao>() {
            }.getType();
            Gson g = new Gson();

            CriterioAvaliacao ca = g.fromJson(criterio, type);

            ServiceLocator.getCriterioAvaliacaoService().update(ca);
            response.setStatus(200);
            
        } catch (Exception e) {
            response.setStatus(500);
        }
    }

    @RequestMapping(value = "/criterioAvaliacao/{id}/editar", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable Long id) {
        ModelAndView mv = null;

        try {
            CriterioAvaliacao criterioAvaliacao = ServiceLocator.getCriterioAvaliacaoService().readById(id);
            mv = new ModelAndView("/criterio-avaliacao/new");
            mv.addObject("criterioAvaliacao", criterioAvaliacao);
            mv.addObject("editarCriterio", true);
        } catch (Exception e) {
            mv = new ModelAndView("/error");
            mv.addObject("e", e);
        }

        return mv;
    }

    @RequestMapping(value = "/criterioAvaliacao/{id}/editar", method = RequestMethod.POST)
    public ModelAndView update(@PathVariable Long id, CriterioAvaliacao criterioAvaliacao) {
        ModelAndView mv = null;

        try {
            ServiceLocator.getCriterioAvaliacaoService().update(criterioAvaliacao);
            mv = new ModelAndView("redirect:/eixo/" + criterioAvaliacao.getEixo().getId() + "/criterioAvaliacao/list");
            mv.addObject("criterioAvaliacao", criterioAvaliacao);
        } catch (Exception e) {
            mv = new ModelAndView("/error");
            mv.addObject("e", e);
        }

        return mv;
    }

    @RequestMapping(value = "/excluir/criterio/{id}/api", method = RequestMethod.GET)
    @ResponseBody
    public void delete(@PathVariable Long id, HttpServletResponse response) {
        try {
            ServiceLocator.getCriterioAvaliacaoService().delete(id);
            response.setStatus(200);
        } catch (Exception e) {
            response.setStatus(500);

        }
    }

}
