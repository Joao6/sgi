package gerenciador.incubadora.controller;

import gerenciador.incubadora.model.ServiceLocator;
import gerenciador.incubadora.model.dao.EmpreendimentoDAO;
import gerenciador.incubadora.model.dao.OrientacaoDAO;
import gerenciador.incubadora.model.entity.Categoria;
import gerenciador.incubadora.model.entity.Empreendimento;
import gerenciador.incubadora.model.entity.Orientacao;
import gerenciador.incubadora.model.entity.Acao;
import gerenciador.incubadora.model.entity.Usuario;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Aliane Leal
 */
@Controller
public class AcaoController {

    @RequestMapping(value = "/acao/novo", method = RequestMethod.GET)
    public ModelAndView create(HttpSession session) {
        ModelAndView mv;
        try {
            mv = new ModelAndView("acao/new");
            Map<String, Object> criteria = new HashMap<String, Object>();

            List<Categoria> categorias = ServiceLocator.getCategoriaService().readByCriteria(criteria);

            Usuario e = (Usuario) session.getAttribute("usuarioLogado");

            criteria.put(EmpreendimentoDAO.CRITERION_EMPREENDEDOR_ID, e.getId());
            List<Empreendimento> emList = ServiceLocator.getEmpreendimentoService().readByCriteria(criteria);
            mv.addObject("categorias", categorias);
            if (emList != null && !emList.isEmpty()) {
                session.setAttribute("empreendimento", emList.get(0));
            }

        } catch (Exception ex) {
            mv = new ModelAndView("error");
            mv.addObject("e", ex);
        }
        return mv;
    }

    //TODO arruar para injetar datas no objeto acao
    @RequestMapping(value = "/acao/novo", method = RequestMethod.POST)
    public ModelAndView create(Acao acao, Date dataInicial, Date dataFinal, HttpSession session) {
        ModelAndView mv;
        try {

            Empreendimento e = (Empreendimento) session.getAttribute("empreendimento");
            acao.setEmpreendimento(e);
            acao.setDataInicio(dataInicial);
            acao.setDataFim(dataFinal);
            ServiceLocator.getAcaoService().create(acao);
            mv = new ModelAndView("redirect:/acao");

        } catch (Exception e) {
            mv = new ModelAndView("error");
            mv.addObject("e", e);
        }
        return mv;
    }

    @RequestMapping(value = "/acao/{id}/atualizar", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable Long id) {
        ModelAndView mv;
        try {

            Map<String, Object> criteria = new HashMap<String, Object>();
            Acao acao = ServiceLocator.getAcaoService().readById(id);

            criteria.put(OrientacaoDAO.CRITERION_CATEGORIA_ID, acao.getCategoria().getId());
            List<Orientacao> orientacoes = ServiceLocator.getOrientacaoService().readByCriteria(criteria);

            List<Categoria> categorias = ServiceLocator.getCategoriaService().readByCriteria(criteria);

            mv = new ModelAndView("acao/new");
            mv.addObject("acaoActive", "active");
            mv.addObject("categorias", categorias);

            if (orientacoes != null && !orientacoes.isEmpty()) {
                mv.addObject("orientacao", orientacoes.get(0).getDescricao());
            }
            mv.addObject("acao", acao);
            mv.addObject("update", "update");
        } catch (Exception e) {
            mv = new ModelAndView("error");
            mv.addObject("e", e);
        }
        return mv;
    }

    //TODO arruar para injetar datas no objeto acao
    @RequestMapping(value = "/acao/{id}/atualizar", method = RequestMethod.POST)
    public ModelAndView update(Acao acao, Date dataInicial, Date dataFinal, HttpSession session) {
        ModelAndView mv;
        try {
            acao.setDataInicio(dataInicial);
            acao.setDataFim(dataFinal);
            ServiceLocator.getAcaoService().update(acao);
            mv = new ModelAndView("redirect:/acao");
        } catch (Exception e) {
            mv = new ModelAndView("error");
            mv.addObject("e", e);
        }
        return mv;
    }

    @RequestMapping(value = "/acao/{id}/excluir", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable Long id) {
        ModelAndView mv;
        try {
            ServiceLocator.getAcaoService().delete(id);
            mv = new ModelAndView("redirect:/acao");
            mv.addObject("acaoActive", "active");
        } catch (Exception ex) {
            mv = new ModelAndView("error");
            mv.addObject("e", ex);
        }
        return mv;
    }

    @RequestMapping(value = "/acao", method = RequestMethod.GET)
    public ModelAndView read() {
        ModelAndView mv;
        try {
            mv = new ModelAndView("acao/list");
            mv.addObject("acaoActive", "active");
            Map<String, Object> criteria = new HashMap<String, Object>();
            List<Acao> acaoList = ServiceLocator.getAcaoService().readByCriteria(criteria);
            mv.addObject("acaoList", acaoList);
        } catch (Exception ex) {
            mv = new ModelAndView("error");
            mv.addObject("e", ex);
        }
        return mv;
    }

    @RequestMapping(value = "/acao/{id}/detalhes", method = RequestMethod.GET)
    public ModelAndView readDetail(@PathVariable Long id) {
        ModelAndView mv;
        try {
            Acao acao = ServiceLocator.getAcaoService().readById(id);
            mv = new ModelAndView("acao/detail");
            mv.addObject("acaoActive","active");
            mv.addObject("acao", acao);
        } catch (Exception ex) {
            mv = new ModelAndView("error");
            mv.addObject("e", ex);
        }
        return mv;
    }
}
