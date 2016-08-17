package gerenciador.incubadora.controller;

import gerenciador.incubadora.model.ServiceLocator;
import gerenciador.incubadora.model.dao.EmpreendimentoDAO;
import gerenciador.incubadora.model.entity.AmbienteInterno;
import gerenciador.incubadora.model.entity.Empreendimento;
import gerenciador.incubadora.model.entity.Usuario;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AmbienteInternoController {

    @RequestMapping(value = "/ambiente-interno", method = RequestMethod.GET)
    public ModelAndView readAmbienteInterno() {
        ModelAndView mv;
        try {
            Map<String, Object> criteria = new HashMap<String, Object>();
            List<AmbienteInterno> internoList = ServiceLocator.getAmbienteInternoService().readByCriteria(criteria);

            mv = new ModelAndView("analise-ambiente/interno/info");
            mv.addObject("ambienteInternoActive", "active");
            mv.addObject("ambienteInterno", internoList.get(0));

        } catch (Exception ex) {
            mv = new ModelAndView("error");
            mv.addObject("e", ex);
        }
        return mv;
    }

    @RequestMapping(value = "/ambiente-interno/novo", method = RequestMethod.GET)
    public ModelAndView createInterno() {
        ModelAndView mv;
        try {
            mv = new ModelAndView("analise-ambiente/interno/new");
        } catch (Exception ex) {
            mv = new ModelAndView("error");
            mv.addObject("e", ex);
        }
        return mv;
    }

    @RequestMapping(value = "/ambiente-interno/novo", method = RequestMethod.POST)
    public ModelAndView createInterno(AmbienteInterno ambienteInterno, HttpSession session) {
        ModelAndView mv;
        try {
            Usuario empreendimento = (Usuario) session.getAttribute("usuarioLogado");

            Map<String, Object> criteria = new HashMap<String, Object>();
            criteria.put(EmpreendimentoDAO.CRITERION_EMPREENDEDOR_ID, empreendimento.getId());
            List<Empreendimento> empList = ServiceLocator.getEmpreendimentoService().readByCriteria(criteria);

            if (empList != null && !empList.isEmpty()) {
                ambienteInterno.setEmpreendimento(empList.get(0));
                ServiceLocator.getAmbienteInternoService().create(ambienteInterno);
            }

            mv = new ModelAndView("redirect:/ambiente-interno");
            mv.addObject("internoActive", "active");
        } catch (Exception ex) {
            mv = new ModelAndView("error");
            mv.addObject("e", ex);
        }
        return mv;
    }

    @RequestMapping(value = "/ambiente-interno/editar", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable Long id) {
        ModelAndView mv;
        try {
            AmbienteInterno ambienteInterno = ServiceLocator.getAmbienteInternoService().readById(id);
             mv = new ModelAndView("/analise-ambiente/interno/new");
            mv.addObject("ambienteInterno", ambienteInterno);
        } catch (Exception e) {
            //e.printStackTrace();
            mv = new ModelAndView("/error");
            mv.addObject("e", e);
        }

        return mv;
    }

    @RequestMapping(value = "/ambiente-interno/{id}/editar", method = RequestMethod.POST)
    public ModelAndView update(@PathVariable Long id, AmbienteInterno ambienteInterno, HttpSession session) {
        ModelAndView mv = null;
        try {
           // Empreendimento empreendimento = new Empreendimento();
            //empreendimento.setId(id);
            //ambienteInterno.setEmpreendimento(empreendimento);
            ServiceLocator.getAmbienteInternoService().update(ambienteInterno);

            //Usuario u = (Usuario) session.getAttribute("usuarioLogado");

            mv = new ModelAndView("redirect:/ambiente-interno");
        } catch (Exception e) {
            mv = new ModelAndView("/error");
            mv.addObject("e", e);
        }

        return mv;
    }
}
