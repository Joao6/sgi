package gerenciador.incubadora.controller;

import gerenciador.incubadora.model.ServiceLocator;
import gerenciador.incubadora.model.dao.EmpreendimentoDAO;
import gerenciador.incubadora.model.entity.AmbienteExterno;
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
public class AmbienteExternoController {

    @RequestMapping(value = "/ambiente-externo", method = RequestMethod.GET)

    public ModelAndView readAmbienteExterno() {
        ModelAndView mv;
        try {
            Map<String, Object> criteria = new HashMap<String, Object>();
            List<AmbienteExterno> externoList = ServiceLocator.getAmbienteExternoService().readByCriteria(criteria);

            mv = new ModelAndView("analise-ambiente/externo/list");
            mv.addObject("ambienteExternoActive", "active");
            mv.addObject("ambienteExterno", externoList.get(0));

        } catch (Exception ex) {
            mv = new ModelAndView("error");
            mv.addObject("e", ex);
        }
        return mv;
    }

    @RequestMapping(value = "/ambiente-externo/novo", method = RequestMethod.POST)
    public ModelAndView createInterno(AmbienteExterno ambienteExterno, HttpSession session) {
        ModelAndView mv;
        try {
            Usuario empreendimento = (Usuario) session.getAttribute("usuarioLogado");

            Map<String, Object> criteria = new HashMap<String, Object>();
            criteria.put(EmpreendimentoDAO.CRITERION_EMPREENDEDOR_ID, empreendimento.getId());
            List<Empreendimento> empList = ServiceLocator.getEmpreendimentoService().readByCriteria(criteria);

            if (empList != null && !empList.isEmpty()) {
                ambienteExterno.setEmpreendimento(empList.get(0));
                ServiceLocator.getAmbienteExternoService().create(ambienteExterno);
            }

            mv = new ModelAndView("redirect:/ambiente-externo");
            mv.addObject("externoActive", "active");
        } catch (Exception ex) {
            mv = new ModelAndView("error");
            mv.addObject("e", ex);
        }
        return mv;
    }

    @RequestMapping(value = "/ambiente-externo/novo", method = RequestMethod.GET)
    public ModelAndView createExterno() {
        ModelAndView mv;
        try {
            mv = new ModelAndView("analise-ambiente/externo/new");
        } catch (Exception ex) {
            mv = new ModelAndView("error");
            mv.addObject("e", ex);
        }
        return mv;
    }

     @RequestMapping(value = "/ambiente-externo/editar", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable Long id) {
        ModelAndView mv;
        try {
            AmbienteExterno ambienteExterno = ServiceLocator.getAmbienteExternoService().readById(id);
             mv = new ModelAndView("/analise-ambiente/externo/new");
            mv.addObject("ambienteExterno", ambienteExterno);
        } catch (Exception e) {
            //e.printStackTrace();
            mv = new ModelAndView("/error");
            mv.addObject("e", e);
        }

        return mv;
    }

    @RequestMapping(value = "/ambiente-externo/editar", method = RequestMethod.POST)
    public ModelAndView update(@PathVariable Long id, AmbienteExterno ambienteExterno, HttpSession session) {
        ModelAndView mv;
        try {
           // Empreendimento empreendimento = new Empreendimento();
            //empreendimento.setId(id);
            //ambienteInterno.setEmpreendimento(empreendimento);
            ServiceLocator.getAmbienteExternoService().update(ambienteExterno);

            //Usuario u = (Usuario) session.getAttribute("usuarioLogado");

            mv = new ModelAndView("redirect:/ambiente-externo");
        } catch (Exception e) {
            mv = new ModelAndView("/error");
            mv.addObject("e", e);
        }

        return mv;
    }
}
