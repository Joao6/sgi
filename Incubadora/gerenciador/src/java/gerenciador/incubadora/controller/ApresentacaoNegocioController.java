/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.incubadora.controller;

import gerenciador.incubadora.model.ServiceLocator;
import gerenciador.incubadora.model.entity.ApresentacaoNegocio;
import gerenciador.incubadora.model.entity.Empreendimento;
import gerenciador.incubadora.model.entity.Usuario;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Edgard
 */
@Controller
public class ApresentacaoNegocioController {

    @RequestMapping(value = "/empreendimento/{empreendimentoID}/proposta/novo", method = RequestMethod.GET)
    public ModelAndView create(@PathVariable Long empreendimentoID) {
        ModelAndView mv = null;

        try {
            Empreendimento empreendimento = ServiceLocator.getEmpreendimentoService().readById(empreendimentoID);
            mv = new ModelAndView("/apresentacao-negocio/new");
            mv.addObject("empreendimento", empreendimento);
        } catch (Exception ex) {
            ex.printStackTrace();
            mv = new ModelAndView("/error");
            mv.addObject("e", ex);
        }

        return mv;
    }

    @RequestMapping(value = "/empreendimento/{empreendimentoID}/proposta/novo", method = RequestMethod.POST)
    public ModelAndView create(@PathVariable Long empreendimentoID, ApresentacaoNegocio apresentacao, HttpSession session) {
        ModelAndView mv = null;

        try {
            Empreendimento empreendimento = new Empreendimento();
            empreendimento.setId(empreendimentoID);
            apresentacao.setEmpreendimento(empreendimento);
            ServiceLocator.getApresentacaoNegocioService().create(apresentacao);

            Usuario u = (Usuario) session.getAttribute("usuarioLogado");

            mv = new ModelAndView("redirect:/empreendimentos");
        } catch (Exception ex) {
            mv = new ModelAndView("/error");
            mv.addObject("e", ex);
        }

        return mv;
    }

    @RequestMapping(value = "/empreendimento/proposta/{id}", method = RequestMethod.GET)
    public ModelAndView getApresentacaoNegocio(@PathVariable Long id) {
        ModelAndView mv = null;

        try {
            ApresentacaoNegocio apresentacaoNegocio = ServiceLocator.getApresentacaoNegocioService().readById(id);
            Empreendimento empreendimento = ServiceLocator.getEmpreendimentoService().readById(id);
            
            mv = new ModelAndView("/apresentacao-negocio/info");
            mv.addObject("apresentacao", apresentacaoNegocio);
            mv.addObject("empreendimento", empreendimento);
        } catch (Exception ex) {
            mv = new ModelAndView("/error");
            mv.addObject("e", ex);
        }

        return mv;
    }

    @RequestMapping(value = "/empreendimento/proposta/{id}/editar", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable Long id) {
        ModelAndView mv = null;

        try {
            ApresentacaoNegocio apresentacao = ServiceLocator.getApresentacaoNegocioService().readById(id);
            mv = new ModelAndView("/apresentacao-negocio/new");
            mv.addObject("apresentacao", apresentacao);
        } catch (Exception e) {
            e.printStackTrace();
            mv = new ModelAndView("/error");
            mv.addObject("e", e);
        }

        return mv;
    }

    @RequestMapping(value = "/empreendimento/proposta/{id}/editar", method = RequestMethod.POST)
    public ModelAndView update(@PathVariable Long id, ApresentacaoNegocio apresentacaoNegocio, HttpSession session) {
        ModelAndView mv = null;
        try {
            Empreendimento empreendimento = new Empreendimento();
            empreendimento.setId(id);
            apresentacaoNegocio.setEmpreendimento(empreendimento);
            ServiceLocator.getApresentacaoNegocioService().update(apresentacaoNegocio);
            
            Usuario u = (Usuario) session.getAttribute("usuarioLogado");
            
            mv = new ModelAndView("redirect:/empreendimentos");
        } catch (Exception e) {
            mv = new ModelAndView("/error");
            mv.addObject("e", e);
        }

        return mv;
    }

}
