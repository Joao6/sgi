package gerenciador.incubadora.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import gerenciador.incubadora.model.ServiceLocator;
import gerenciador.incubadora.model.dao.CriterioAvaliacaoDAO;
import gerenciador.incubadora.model.dao.EditalDAO;
import gerenciador.incubadora.model.dao.EmpreendimentoDAO;
import gerenciador.incubadora.model.dao.NotaDAO;
import gerenciador.incubadora.model.entity.ApresentacaoNegocio;
import gerenciador.incubadora.model.entity.Avaliacao;
import gerenciador.incubadora.model.entity.Avaliador;
import gerenciador.incubadora.model.entity.CriterioAvaliacao;
import gerenciador.incubadora.model.entity.Edital;
import gerenciador.incubadora.model.entity.Eixo;
import gerenciador.incubadora.model.entity.Empreendedor;
import gerenciador.incubadora.model.entity.Empreendimento;
import gerenciador.incubadora.model.entity.Endereco;
import gerenciador.incubadora.model.entity.Nota;
import gerenciador.incubadora.model.entity.RamoAtividade;
import gerenciador.incubadora.model.entity.Usuario;
import gerenciador.incubadora.model.service.EmpreendimentoService;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmpreendimentoController {

    @RequestMapping(value = "/empreendimento/novo", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView mv;
        try {
            List<RamoAtividade> ramoAtividadeList = ServiceLocator.getRamoAtividade()
                    .readByCriteria(new HashMap<String, Object>());

            Map<String, Object> criteria = new HashMap<String, Object>();
            criteria.put(EditalDAO.CRITERION_EDITAL_ABERTO, EditalDAO.CRITERION_EDITAL_ABERTO);
            List<Edital> editalList = ServiceLocator.getEditalService().readByCriteria(criteria);

            mv = new ModelAndView("empreendimento/empreendedor/new");
            //Flag
            mv.addObject("status", "create");
        } catch (Exception ex) {
            mv = new ModelAndView("error");
            mv.addObject("e", ex);
        }
        return mv;
    }

    @RequestMapping(value = "/empreendimento/novo", method = RequestMethod.POST)
    @ResponseBody
    public void create(@RequestBody String empreendimento, HttpSession session, HttpServletResponse response) {
        try {
            Type type = new TypeToken<Empreendimento>() {
            }.getType();
            Gson g = new Gson();
            Empreendimento e = g.fromJson(empreendimento, type);
            e.setStatus("-- Sem proposta enviada --");

            Map<String, Object> fields = new HashMap<String, Object>();
            fields.put("nome", e.getNome());
            fields.put("razaoSocial", e.getRazaoSocial());
            fields.put("cnpj", e.getCnpj());
            fields.put("inscricaoEstadual", e.getInscricaoEstadual());
            fields.put("inscricaoMunicipal", e.getInscricaoMunicipal());
            fields.put("missao", e.getMissao());
            fields.put("visao", e.getVisao());
            fields.put("valores", e.getValores());
            fields.put("editalFK", e.getEdital().getId());
            fields.put("ramoAtividadeFK", e.getRamoAtividade().getId());

            Map<String, String> errors = ServiceLocator.getEmpreendimentoService().validateForCreate(fields);
            if (errors.isEmpty()) {

                Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
                if (usuario.getTipoUsuario().equals(Usuario.TIPO_USUARIO_EMPREENDEDOR)) {
                    Empreendedor empreendedor = new Empreendedor();
                    empreendedor.setId(usuario.getId());
                    e.getEmpreendedorList().add(empreendedor);
                    ServiceLocator.getEmpreendimentoService().create(e);
                    response.setStatus(200);
                } else {
                    response.setStatus(400);
                }

            } else {
                response.setStatus(400);
            }
        } catch (Exception ex) {
            response.setStatus(500);
        }

    }

    @RequestMapping(value = "/empreendimento/{id}/alterar", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable Long id) {
        ModelAndView mv;
        try {
            Empreendimento empreendimento = ServiceLocator.getEmpreendimentoService().readById(id);
            List<RamoAtividade> ramoAtividadeList = ServiceLocator.getRamoAtividade()
                    .readByCriteria(new HashMap<String, Object>());
            List<Empreendedor> empreendedorList = ServiceLocator.getEmpreendedorService()
                    .readByCriteria(new HashMap<String, Object>());
            Map<String, Object> criteria = new HashMap<String, Object>();
            criteria.put(EditalDAO.CRITERION_EDITAL_ABERTO, EditalDAO.CRITERION_EDITAL_ABERTO);
            List<Edital> editalList = ServiceLocator.getEditalService().readByCriteria(criteria);

            mv = new ModelAndView("empreendimento/gestao/new");

            mv.addObject("empreendimento", empreendimento);
            mv.addObject("empreendedorList", empreendedorList);
            mv.addObject("ramoAtividadeList", ramoAtividadeList);
            mv.addObject("editalList", editalList);
            mv.addObject("status", "update");
        } catch (Exception ex) {
            mv = new ModelAndView("error");
            mv.addObject("e", ex);
        }
        return mv;
    }

    @RequestMapping(value = "/empreendimento/{id}/alterar", method = RequestMethod.POST)
    public ModelAndView update(Empreendimento empreendimento, Long ramoAtividadeFK, Long editalFK,
            @PathVariable Long id, HttpSession session) {
        ModelAndView mv;
        try {
            Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

            Empreendedor e = new Empreendedor();
            e.setId(usuario.getId());

            empreendimento.getEmpreendedorList().add(e);

            RamoAtividade ramoAtividade = new RamoAtividade();
            ramoAtividade.setId(ramoAtividadeFK);
            empreendimento.setRamoAtividade(ramoAtividade);

            Edital edital = new Edital();
            edital.setId(editalFK);

            empreendimento.setEdital(edital);

            ServiceLocator.getEmpreendimentoService().update(empreendimento);

            mv = new ModelAndView("redirect:/empreendimentos");
        } catch (Exception ex) {
            ex.printStackTrace();
            mv = new ModelAndView("error");
            mv.addObject("e", ex);
        }
        return mv;
    }

    @RequestMapping(value = "/remover/empreendimento/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void delete(@RequestBody @PathVariable Long id, HttpSession session, HttpServletResponse response) {
        try {
            ServiceLocator.getEmpreendimentoService().delete(id);
            response.setStatus(200);
        } catch (Exception ex) {
            response.setStatus(500);
        }
    }

    @RequestMapping(value = "/empreendimento/{id}/detalhes", method = RequestMethod.GET)
    public ModelAndView readDetail(@PathVariable Long id) {
        ModelAndView mv = null;
        try {
            Empreendimento empreendimento = ServiceLocator.getEmpreendimentoService().readById(id);
            mv = new ModelAndView("empreendimento/gestao/detail");
            mv.addObject("empreendimento", empreendimento);
        } catch (Exception ex) {
            mv = new ModelAndView("error");
            mv.addObject("e", ex);
        }
        return mv;
    }

    //Visão do empreendedor
    @RequestMapping(value = "/empreendedor/empreendimentos", method = RequestMethod.GET)
    public ModelAndView readEmpreendedor(HttpSession session) {

        ModelAndView mv;
        try {
            Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
            Map<String, Object> criteria = new HashMap<String, Object>();
            criteria.put(EmpreendimentoDAO.CRITERION_EMPREENDEDOR_ID, usuario.getId());
            List<Empreendimento> empreendimentoList = ServiceLocator.getEmpreendimentoService()
                    .readByCriteria(criteria);
            mv = new ModelAndView("empreendimento/empreendedor/list");
            if (!empreendimentoList.isEmpty()) {
                mv.addObject("empreendimento", empreendimentoList.get(0));
            }
        } catch (Exception ex) {
            mv = new ModelAndView("error");
            mv.addObject("e", ex);
        }
        return mv;
    }

    @RequestMapping(value = "/empreendedor/empreendimento/{id}/atualizar", method = RequestMethod.GET)
    public ModelAndView updateEmpreendimento(@PathVariable Long id) {
        ModelAndView mv;
        try {
            Empreendimento empreendimento = ServiceLocator.getEmpreendimentoService().readById(id);
            List<RamoAtividade> ramoAtividadeList = ServiceLocator.getRamoAtividade().readByCriteria(new HashMap<String, Object>());
            mv = new ModelAndView("/empreendimento/gestao/new");

            Map<String, Object> criteria = new HashMap<String, Object>();
            criteria.put(EditalDAO.CRITERION_EDITAL_ABERTO, EditalDAO.CRITERION_EDITAL_ABERTO);
            List<Edital> editalList = ServiceLocator.getEditalService().readByCriteria(criteria);

            mv.addObject("empreendimento", empreendimento);
            mv.addObject("ramoAtividadeList", ramoAtividadeList);
            mv.addObject("editalList", editalList);
            mv.addObject("editarEmpreendimento", true);
        } catch (Exception ex) {
            mv = new ModelAndView("error");
            mv.addObject("e", ex);
        }
        return mv;
    }

    @RequestMapping(value = "/empreendedor/empreendimento/{id}/atualizar", method = RequestMethod.POST)
    public ModelAndView updateEmpreendimento(Empreendimento empreendimento, Endereco endereco, @PathVariable Long id, HttpSession session) {
        ModelAndView mv;
        try {
            Empreendedor empreendedor = new Empreendedor();
            empreendedor.setId(((Usuario) session.getAttribute("usuarioLogado")).getId());
            empreendimento.getEmpreendedorList().add(empreendedor);
            ServiceLocator.getEmpreendimentoService().update(empreendimento);
            Long empreendedorID = ((Usuario) session.getAttribute("usuarioLogado")).getId();
            mv = new ModelAndView("redirect:/empreendimentos");
        } catch (Exception ex) {
            mv = new ModelAndView("error");
            mv.addObject("e", ex);
        }
        return mv;
    }

    @RequestMapping(value = "/empreendimentos", method = RequestMethod.GET)
    public ModelAndView empreendimentos(HttpSession session) {
        ModelAndView mv;
        try {
            mv = new ModelAndView("empreendimento/gestao/list");
        } catch (Exception ex) {
            mv = new ModelAndView("/error");
            mv.addObject("e", ex);
        }

        return mv;
    }

    @RequestMapping(value = "/empreendimentos/api", method = RequestMethod.GET)
    @ResponseBody
    public String getEmpreendimentos(HttpServletResponse response) {
        String empreendimentoJSON = null;

        try {
            Map<String, Object> criteria = new HashMap<String, Object>();
            List<Empreendimento> empreendimentoList = ServiceLocator.getEmpreendimentoService().readByCriteria(criteria);
            Gson g = new GsonBuilder().setDateFormat("dd-MM-yyyy HH:mm").create();
            empreendimentoJSON = g.toJson(empreendimentoList);
            response.setStatus(200);
        } catch (Exception ex) {
            response.setStatus(500);
        }

        return empreendimentoJSON;
    }

    @RequestMapping(value = "/empreendimento/{id}/avaliar", method = RequestMethod.GET)
    public ModelAndView getAvaliar(@PathVariable Long id, HttpSession session) {
        ModelAndView mv = null;

        try {
            List<Eixo> eixoList = ServiceLocator.getEixoService().readByCriteria(null);

            Map<Eixo, List<CriterioAvaliacao>> eixoMap = new HashMap<Eixo, List<CriterioAvaliacao>>();

            Map<String, Object> criteria = new HashMap<String, Object>();
            criteria.put(NotaDAO.CRITERION_EMPREENDIMENTO_ID, id);
            List<Nota> notaList = ServiceLocator.getNotaService().readByCriteria(criteria);

            Map<Long, String> fields = new HashMap<Long, String>();
            for (Nota aux : notaList) {
                fields.put(aux.getCriterioAvaliacao().getId(), aux.getNota().toString());
            }
            for (Eixo aux : eixoList) {
                criteria = new HashMap<String, Object>();
                criteria.put(CriterioAvaliacaoDAO.CRITERION_EIXO_ID, aux.getId());

                List<CriterioAvaliacao> criterioAvaliacaoList = ServiceLocator.getCriterioAvaliacaoService().readByCriteria(criteria);
                eixoMap.put(aux, criterioAvaliacaoList);
            }

            Map<Long, String> errors = (Map) session.getAttribute("errors");
            mv = new ModelAndView("/empreendimento/avaliador/avaliacao");
            Integer eixoListSize = eixoList.size();
            mv.addObject("eixoListSize", eixoListSize);
            mv.addObject("eixoMap", eixoMap);
            mv.addObject("eixoMapSize", eixoMap.size());
            mv.addObject("errors", errors);
//            mv.addObject("avaliacaoEmpreendimento", avaliacaoEmpreendimento);

            Empreendimento empreendimento = ServiceLocator.getEmpreendimentoService().readById(id);
            mv.addObject("empreendimento", empreendimento);
            Usuario avaliador = (Usuario) session.getAttribute("usuarioLogado");
            mv.addObject("avaliador", avaliador);

            Map<String, Double> avaliacaoEmpreendimento = new HashMap<String, Double>();
            avaliacaoEmpreendimento = ServiceLocator.getNotaService().getNotaAvaliador(avaliador.getId(), id);;
            mv.addObject("avaliacaoEmpreendimento", avaliacaoEmpreendimento);

            criteria = new HashMap<String, Object>();
            criteria.put(NotaDAO.CRITERION_AVALIADOR_ID, ((Usuario) session.getAttribute("usuarioLogado")).getId());
            criteria.put(NotaDAO.CRITERION_EMPREENDIMENTO_ID, id);
            List<Nota> notaListAux = ServiceLocator.getNotaService().readByCriteria(criteria);

            boolean avaliacao = false;
            if (notaList.size() > 0) {
                Map<Long, Double> notaMap = new HashMap<Long, Double>();
                for (Nota n : notaListAux) {
                    notaMap.put(n.getCriterioAvaliacao().getId(), n.getNota());
                    avaliacao = true;
                }
                mv.addObject("notaMap", notaMap);
            }
            mv.addObject("avaliacao", avaliacao);
            session.removeAttribute("erro");
            session.removeAttribute("errors");
        } catch (Exception e) {
            mv = new ModelAndView("/error");
            mv.addObject("e", e);
            session.removeAttribute("erro");
            session.removeAttribute("errors");
        }

        return mv;
    }

    @RequestMapping(value = "/empreendimento/{id}/avaliar", method = RequestMethod.POST)
    public ModelAndView postAvaliar(Long[] criterioID, Double[] criterioNota, @PathVariable Long id, HttpSession session) {
        ModelAndView mv = null;

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        try {
            if (usuario.getTipoUsuario().equals(Usuario.TIPO_USUARIO_AVALIADOR) && criterioID.length == criterioNota.length) {
                Map<Long, Double> fields = new HashMap<Long, Double>();
                for (int i = 0; i < criterioID.length; i++) {
                    fields.put(criterioID[i], criterioNota[i]);
                }

                Map<Long, String> errors = ServiceLocator.getNotaService().validateForCreateNota(fields);
                if (errors.isEmpty()) {
                    for (int i = 0; i < criterioID.length; i++) {

                        Nota nota = new Nota();
                        Avaliador avaliador = new Avaliador();
                        avaliador.setId(usuario.getId());
                        nota.setAvaliador(avaliador);

                        CriterioAvaliacao criterioAvaliacao = new CriterioAvaliacao();
                        criterioAvaliacao.setId(criterioID[i]);
                        nota.setCriterioAvaliacao(criterioAvaliacao);

                        Empreendimento empreendimento = new Empreendimento();
                        empreendimento = ServiceLocator.getEmpreendimentoService().readById(id);
//                        empreendimento.setId(id);
//                        empreendimento.getNome();
                        nota.setEmpreendimento(empreendimento);
                        nota.setDataHora(new java.util.Date());
                        nota.setNota(criterioNota[i]);

                        ServiceLocator.getNotaService().create(nota);

//                        Empreendimento e = ServiceLocator.getEmpreendimentoService().readById(id);
//                        e.setStatus(Empreendimento.EMPREENDIMENTO_STATUS_AV_REALIZADA);
//                        ServiceLocator.getEmpreendimentoService().update(e);
                    }
                    Map<String, Object> criteria = new HashMap<String, Object>();
                    criteria.put("4", "1");
                    List<Usuario> admList = ServiceLocator.getUsuarioService().readByCriteria(criteria);
                    Empreendimento empreendimento = new Empreendimento();
                    empreendimento = ServiceLocator.getEmpreendimentoService().readById(id);
                    for (Usuario usuarioAdm : admList) {
                        String email = usuarioAdm.getEmail();
                        String assunto = "Avaliação de Empreendimento";
                        String texto = "Olá, Gestor."
                                + " O empreendimento '" + empreendimento.getNome() + "' acabou de ser avaliado pelo avaliador " + usuario.getNome() + " " + usuario.getSobrenome() + "!"
                                + " Caso queira visualizar as notas referentes à esta avaliação, acesse o sistema.";
                        ServiceLocator.getEmailService().sendEmail(email, assunto, texto);
                    }

                    mv = new ModelAndView("/usuario/avaliador/confirmacao-avaliacao");
                    session.setAttribute("erro", false);
                } else {
                    List<Eixo> eixoList = ServiceLocator.getEixoService().readByCriteria(null);

                    Map<Eixo, List<CriterioAvaliacao>> eixoMap = new HashMap<Eixo, List<CriterioAvaliacao>>();
                    for (Eixo aux : eixoList) {
                        Map<String, Object> criteria = new HashMap<String, Object>();
                        criteria.put(CriterioAvaliacaoDAO.CRITERION_EIXO_ID, aux.getId());

                        List<CriterioAvaliacao> criterioAvaliacaoList = ServiceLocator.getCriterioAvaliacaoService().readByCriteria(criteria);
                        eixoMap.put(aux, criterioAvaliacaoList);
                    }

                    session.setAttribute("erro", true);
                    session.setAttribute("errors", errors);
                    mv = new ModelAndView("redirect:/empreendimento/{id}/avaliar");
//                    mv.addObject("eixoMap", eixoMap);
//                    mv.addObject("eixoMapSize", eixoMap.size());
//                    mv.addObject("errors", errors);
//                    mv.addObject("fields", fields);
                }
            }

        } catch (Exception e) {
            mv = new ModelAndView("/error");
            mv.addObject("e", e);
        }

        return mv;
    }

    @RequestMapping(value = "/avaliacao/empreendimento/{id}", method = RequestMethod.GET)
    public ModelAndView goAvaliacao(@PathVariable Long id, HttpSession session) {
        ModelAndView mv = null;

        try {            
            Map<String, Double> avaliacaoEmpreendimento = new HashMap<String, Double>();
            avaliacaoEmpreendimento = ServiceLocator.getNotaService().getAvaliacao(id);

            Empreendimento empreendimento = new Empreendimento();
            empreendimento = ServiceLocator.getEmpreendimentoService().readById(id);

            Map<String, List<Avaliacao>> avaliacaoAvaliador = ServiceLocator.getAvaliacaoService().getAvaliacaoEmpreendimento(id);            
            Map<String, Map<String, Double>> mapNotasAvaliadorEixo = new HashMap<String, Map<String, Double>>();
            for (Avaliador aux : empreendimento.getAvaliadorList()) {
                Map<String, Double> notaAvaliadorPorEixo = ServiceLocator.getAvaliacaoService().getNotaEixoAvaliador(id, aux.getId());
                mapNotasAvaliadorEixo.put(aux.getNome()+" "+aux.getSobrenome(), notaAvaliadorPorEixo);
//                listNotaAvaliadorPorEixo.add(notaAvaliadorPorEixo);
            }
            
            mv = new ModelAndView("empreendimento/gestao/avaliacao");
            mv.addObject("avaliacaoAvaliador", avaliacaoAvaliador);
            mv.addObject("empreendimento", empreendimento);
            mv.addObject("avaliacaoEmpreendimento", avaliacaoEmpreendimento);
            mv.addObject("notaPorEixoAvalidor", mapNotasAvaliadorEixo);

        } catch (Exception e) {
            mv = new ModelAndView("/error");
            mv.addObject("e", e);
        }

        return mv;
    }

    /**
     * Obter Eixos
     *
     * @param response
     * @return
     */
    @RequestMapping(value = "/eixos/api", method = RequestMethod.GET)
    @ResponseBody
    public String getEixos(HttpServletResponse response) {
        String eixoJSON = null;
        try {
            List<Eixo> eixoList = ServiceLocator.getEixoService().readByCriteria(null);
            Map<Eixo, List<CriterioAvaliacao>> eixoMap = new HashMap<Eixo, List<CriterioAvaliacao>>();
            Map<String, Object> criteria;
            for (Eixo aux : eixoList) {
                criteria = new HashMap<String, Object>();
                criteria.put(CriterioAvaliacaoDAO.CRITERION_EIXO_ID, aux.getId());

                List<CriterioAvaliacao> criterioAvaliacaoList = ServiceLocator.getCriterioAvaliacaoService().readByCriteria(criteria);
                eixoMap.put(aux, criterioAvaliacaoList);
            }

            Gson g = new Gson();
            eixoJSON = g.toJson(eixoMap);
            response.setStatus(200);
        } catch (Exception e) {
            response.setStatus(500);
        }
        return eixoJSON;
    }

    /*@RequestMapping(value = "/empreendimento/avaliar", method = RequestMethod.POST)
     @ResponseBody
     public void avaliar(@RequestBody String notaList, HttpServletResponse response, HttpSession session) {

     Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
     try {
     if (usuario.getTipoUsuario().equals(Usuario.TIPO_USUARIO_AVALIADOR)) {

     Gson g = new GsonBuilder().setDateFormat("dd-MM-YYYY").create();
     Type type = new TypeToken<List<Nota>>() {
     }.getType();

     List<Nota> notas = g.fromJson(notaList, type);

     Map<Long, Double> fields = new HashMap<Long, Double>();
     for (Nota nota : notas) {
     fields.put(nota.getId(), nota.getNota());
     }

     Map<Long, String> errors = ServiceLocator.getNotaService().validateForCreateNota(fields);
     if (errors.size() == 0) {
     for (Nota nota : notas) {
     ServiceLocator.getNotaService().create(nota);
     }
     Empreendimento e = ServiceLocator.getEmpreendimentoService().readById(notas.get(0).getEmpreendimento().getId());
     e.setStatus(Empreendimento.EMPREENDIMENTO_STATUS_AV_REALIZADA);
     ServiceLocator.getEmpreendimentoService().update(e);
     response.setStatus(200);

     } else {
     response.setStatus(500);
     }
     }

     } catch (Exception e) {
     e.printStackTrace();
     response.setStatus(500);

     }

     }*/
    @RequestMapping(value = "/empreendimento/{id}/adicionarAvaliador", method = RequestMethod.GET)
    public ModelAndView addAvaliador(@PathVariable Long id) {
        ModelAndView mv = null;

        try {
            mv = new ModelAndView("/empreendimento/gestao/add-avaliador");
            List<Avaliador> avaliadorList = ServiceLocator.getAvaliadorService().readByCriteria(null);
            mv.addObject("avaliadorList", avaliadorList);

            Empreendimento empreendimento = ServiceLocator.getEmpreendimentoService().readById(id);
            mv.addObject("empreendimento", empreendimento);

        } catch (Exception e) {
            e.printStackTrace();
            mv = new ModelAndView("/error");
            mv.addObject("e", e);
        }

        return mv;
    }

    @RequestMapping(value = "/empreendimento/{id}/adicionarAvaliador", method = RequestMethod.POST)
    public ModelAndView addAvaliadores(@PathVariable Long id, Long[] avaliador) {
        ModelAndView mv = null;

        try {
            Empreendimento empreendimento = ServiceLocator.getEmpreendimentoService().readById(id);

            empreendimento.getAvaliadorList().clear();
            if (avaliador != null) {
                for (Long idAvaliador : avaliador) {
                    //Avaliador av = new Avaliador();

                    //av.setId(idAvaliador);
                    Avaliador av = ServiceLocator.getAvaliadorService().readById(idAvaliador);
                    empreendimento.getAvaliadorList().add(av);
                    try {

//                  String mensagem = "Você foi selecionado para avaliar o empreendimento " + empreendimento.getNome() + ". Favor verificar no sistema.";
//                  ServiceLocator.getEmailService().sendEmail(av.getEmail(), "Você foi vinculado como avaliador", mensagem);
                    } catch (Exception ex) {

                    }
                }
            }

            ServiceLocator.getEmpreendimentoService().update(empreendimento);

            mv = new ModelAndView("redirect:/incubadora/empreendimento");

        } catch (Exception e) {
            e.printStackTrace();
            mv = new ModelAndView("/error");
            mv.addObject("e", e);
        }

        return mv;
    }

    /* ADICIONA AVALIADORES AO EMPREENDIMENTO */
    @RequestMapping(value = "/empreendimento/add/avaliadores", method = RequestMethod.POST)
    @ResponseBody
    public void addAvaliadores(@RequestBody String empreendimento, HttpServletResponse response) {
        try {
            Type type = new TypeToken<Empreendimento>() {
            }.getType();
            Gson g = new GsonBuilder().setDateFormat("dd-MM-yyyy HH:mm").create();
            Empreendimento e = g.fromJson(empreendimento, type);
            ServiceLocator.getEmpreendimentoService().update(e);
            response.setStatus(200);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(500);
        }
    }

    /* ADICIONA EMPREENDEDORES AO EMPREENDIMENTO */
    @RequestMapping(value = "/empreendimento/add/empreendedores", method = RequestMethod.POST)
    @ResponseBody
    public void addEmpreendedores(@RequestBody String empreendimento, HttpServletResponse response) {
        try {
            Type type = new TypeToken<Empreendimento>() {
            }.getType();
            Gson g = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
            Empreendimento e = g.fromJson(empreendimento, type);
            ServiceLocator.getEmpreendimentoService().update(e);
            response.setStatus(200);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(500);
        }
    }

    @RequestMapping(value = "/empreendimento/{id}/alterarStatus", method = RequestMethod.GET)
    public ModelAndView updateStatus(@PathVariable Long id) {
        ModelAndView mv = null;

        try {
            Empreendimento empreendimento = ServiceLocator.getEmpreendimentoService().readById(id);
            mv = new ModelAndView("/status-empreendimento/form");
            mv.addObject("empreendimento", empreendimento);
        } catch (Exception e) {
            mv = new ModelAndView("/error");
            mv.addObject("e", e);
        }

        return mv;
    }

    @RequestMapping(value = "/empreendimento/{id}/status/{status}", method = RequestMethod.GET)
    public ModelAndView updateStatus(@PathVariable Long id, @PathVariable Integer status) {
        ModelAndView mv = null;

        try {
            switch (status) {
                case 1:
                    ServiceLocator.getEmpreendimentoService().updateStatusEmpreendimento(id, Empreendimento.EMPREENDIMENTO_STATUS_ENVIADA);
                    break;

                case 2:
                    ServiceLocator.getEmpreendimentoService().updateStatusEmpreendimento(id, Empreendimento.EMPREENDIMENTO_STATUS_AP_AGENDADA);
                    break;

                case 3:
                    ServiceLocator.getEmpreendimentoService().updateStatusEmpreendimento(id, Empreendimento.EMPREENDIMENTO_STATUS_AP_REALIZADA);
                    break;

                case 5:
                    ServiceLocator.getEmpreendimentoService().updateStatusEmpreendimento(id, Empreendimento.EMPREENDIMENTO_STATUS_APROVADO);
                    break;

                case 6:
                    ServiceLocator.getEmpreendimentoService().updateStatusEmpreendimento(id, Empreendimento.EMPREENDIMENTO_STATUS_REPROVADO);
                    break;

                case 7:
                    ServiceLocator.getEmpreendimentoService().updateStatusEmpreendimento(id, Empreendimento.EMPREENDIMENTO_STATUS_CANCELADO);
                    break;
            }
            mv = new ModelAndView("redirect:/incubadora/empreendimento");
        } catch (Exception exception) {
            mv = new ModelAndView("/error");
            mv.addObject("e", exception);
        }

        return mv;
    }

    @RequestMapping(value = "/empreendimento/change-state", method = RequestMethod.POST)
    @ResponseBody
    public void changeState(@RequestBody String empreendimento, HttpServletResponse response) {
        try {
            Type type = new TypeToken<Empreendimento>() {
            }.getType();
            Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            Empreendimento e = g.fromJson(empreendimento, type);
            ServiceLocator.getEmpreendimentoService().updateStatusEmpreendimento(e.getId(), e.getStatus());

            EmpreendimentoService es = new EmpreendimentoService();
            es.sendEmailStatus(e);
            response.setStatus(200);
        } catch (Exception e) {
            response.setStatus(500);
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/empreendimento/agendar-apresentacao", method = RequestMethod.POST)
    @ResponseBody
    public void agendarAvaliacao(@RequestBody String apresentacao, HttpSession session, HttpServletResponse response) {

        try {
            Type type = new TypeToken<Apresentacao>() {
            }.getType();
            Gson g = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
            Apresentacao agenda = g.fromJson(apresentacao, type);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(agenda.getDataApresentacao());

            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            java.util.Date hora = formatter.parse(agenda.getHoraApresentacao());
            calendar.set(Calendar.HOUR, hora.getHours());
            calendar.set(Calendar.MINUTE, hora.getMinutes());
            java.util.Date data = calendar.getTime();

            ServiceLocator.getEmpreendimentoService().agendarApresentacao(agenda.getId(), new java.sql.Date(data.getTime()), agenda.getLocalApresentacao());
            response.setStatus(200);
        } catch (Exception ex) {
            response.setStatus(500);
            ex.printStackTrace();
        }

    }

    @RequestMapping(value = "/empreendimento/{id}/contrato", method = RequestMethod.GET)
    public ModelAndView contrato() {
        ModelAndView mv = new ModelAndView("/empreendimento/gestao/contrato");
        return mv;
    }

    @RequestMapping(value = "/empreendimento/{id}/contrato", method = RequestMethod.POST)
    public ModelAndView contrato(@PathVariable Long id, Boolean aceite
    ) {
        ModelAndView mv = null;

        try {
            ServiceLocator.getEmpreendimentoService().updateContratacao(id, aceite);
            mv = new ModelAndView("redirect:/empreendimentos");
        } catch (Exception ex) {
            mv = new ModelAndView("/error");
            mv.addObject("e", ex);
        }

        return mv;
    }

    @RequestMapping(value = "/empreendimento/{id}/enviar-proposta", method = RequestMethod.GET)
    public ModelAndView contrato(@PathVariable Long id, Long novaProposta) {
        ModelAndView mv = null;

        try {
            Empreendimento empreendimento = ServiceLocator.getEmpreendimentoService().readById(id);
            ApresentacaoNegocio apresentacaoNegocio = ServiceLocator.getApresentacaoNegocioService().readById(id);
            if (apresentacaoNegocio != null && novaProposta == null) {
                mv = new ModelAndView("empreendimento/empreendedor/proposta-new-aviso");
                mv.addObject("empreendimento", empreendimento);
            } else if (novaProposta == 1) {
                mv = new ModelAndView("empreendimento/empreendedor/proposta-new");
                mv.addObject("empreendimento", empreendimento);
            }
        } catch (Exception ex) {
            mv = new ModelAndView("/error");
            mv.addObject("e", ex);
        }

        return mv;
    }

    /**
     * Serviço para buscar empreendimento(s) a partir do id do empreendedor
     *
     * @param session
     * @param response
     * @return
     */
    @RequestMapping(value = "/empreendimentos/empreendedor", method = RequestMethod.GET)
    @ResponseBody
    public String getEmpreendimentos(HttpSession session, HttpServletResponse response) {
        String empreendimentos = null;
        try {
            Map<String, Object> criteria = new HashMap<String, Object>();

            Usuario empreendedor = (Usuario) session.getAttribute("usuarioLogado");
            if (empreendedor != null) {
                criteria.put(EmpreendimentoDAO.CRITERION_EMPREENDEDOR_ID, empreendedor.getId());
                List<Empreendimento> empreendimentoList = ServiceLocator.getEmpreendimentoService().readByCriteria(criteria);
                if (empreendimentoList != null && !empreendimentoList.isEmpty()) {
                    Gson g = new GsonBuilder().setDateFormat("dd-MM-yyyy HH:mm").create();
                    empreendimentos = g.toJson(empreendimentoList);
                }
                response.setStatus(200);
            } else {
                response.setStatus(500);
            }

        } catch (Exception e) {
            response.setStatus(500);
        }
        return empreendimentos;
    }

    @RequestMapping(value = "/empreendimento/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getEmpreendimento(@PathVariable Long id, HttpServletResponse response) {
        String empreendimento = null;
        try {
            Empreendimento e = ServiceLocator.getEmpreendimentoService().readById(id);
            Empreendimento emp = new Empreendimento();
            emp.setId(e.getId());

            Gson g = new Gson();
            empreendimento = g.toJson(emp);
            response.setStatus(200);
        } catch (Exception e) {
            response.setStatus(500);
        }
        return empreendimento;
    }

    @RequestMapping(value = "/empreendimento/update", method = RequestMethod.POST)
    @ResponseBody
    public void getEmpreendimento(@RequestBody String empreendimento, HttpServletResponse response) {
        try {
            Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            Type type = new TypeToken<Empreendimento>() {
            }.getType();
            Empreendimento e = g.fromJson(empreendimento, type);

            Empreendimento emp = ServiceLocator.getEmpreendimentoService().readById(e.getId());
            emp.setApresentacaoNegocio(e.getApresentacaoNegocio());
            emp.setStatus(Empreendimento.EMPREENDIMENTO_STATUS_ENVIADA);
            ServiceLocator.getEmpreendimentoService().update(emp);

            response.setStatus(200);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getCause());
            response.setStatus(500);
        }
    }

    @RequestMapping(value = "/empreendimento/add/ap-negocio", method = RequestMethod.POST)
    @ResponseBody
    public void addApNegocio(@RequestBody String apNegocio, HttpServletResponse response) {
        try {
            Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            Type type = new TypeToken<ApresentacaoNegocio>() {
            }.getType();
            ApresentacaoNegocio apNeg = g.fromJson(apNegocio, type);

            Empreendimento emp = ServiceLocator.getEmpreendimentoService().readById(apNeg.getEmpreendimento().getId());
            emp.setApresentacaoNegocio(apNeg);
            emp.setStatus(Empreendimento.EMPREENDIMENTO_STATUS_ENVIADA);
            ServiceLocator.getEmpreendimentoService().update(emp);

            response.setStatus(200);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getCause());
            response.setStatus(500);

        }
    }

    @RequestMapping(value = "/empreendimento/by/avaliador/{idAvaliador}", method = RequestMethod.GET)
    @ResponseBody
    public String getEmpreendimentosByAvaliador(@RequestBody @PathVariable Long idAvaliador,
            HttpServletResponse response) {

        String empreendimentos = null;
        try {
            Gson g = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
            Map<String, Object> criteria = new HashMap<String, Object>();
            criteria.put(EmpreendimentoDAO.CRITERION_AVALIADOR_ID, idAvaliador);
            //   criteria.put(EmpreendimentoDAO.CRITERION_NOT_NOTA, true);
            criteria.put(EmpreendimentoDAO.CRITERION_STATUS_ILIKE, "Apresentação Realizada");
            List<Empreendimento> empreendimentoList = ServiceLocator.getEmpreendimentoService().readByCriteria(criteria);
            empreendimentos = g.toJson(empreendimentoList);
            response.setStatus(200);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(500);
        }
        return empreendimentos;
    }

    @RequestMapping(value = "/empreendimento/by/empreendedor/{idEmpreendedor}", method = RequestMethod.GET)
    @ResponseBody
    public String getEmpreendimentosByEmpreendedor(@RequestBody @PathVariable Long idEmpreendedor,
            HttpServletResponse response) {

        String empreendimentos = null;
        try {
            Gson g = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
            Map<String, Object> criteria = new HashMap<String, Object>();
            criteria.put(EmpreendimentoDAO.CRITERION_EMPREENDEDOR_ID, idEmpreendedor);
            List<Empreendimento> empreendimentoList = ServiceLocator.getEmpreendimentoService().readByCriteria(criteria);
            empreendimentos = g.toJson(empreendimentoList);
            response.setStatus(200);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(500);
        }
        return empreendimentos;
    }

    private class Apresentacao {

        @DateTimeFormat(pattern = "dd-MMM-yyyy")
        private Long id;
        private Date dataApresentacao;
        private String localApresentacao;
        private String horaApresentacao;

        public void setId(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }

        public void setLocalApresentacao(String localApresentacao) {
            this.localApresentacao = localApresentacao;
        }

        public String getLocalApresentacao() {
            return localApresentacao;
        }

        public void setHoraApresentacao(String horaApresentacao) {
            this.horaApresentacao = horaApresentacao;
        }

        public String getHoraApresentacao() {
            return horaApresentacao;
        }

        public void setDataApresentacao(Date dataApresentacao) {
            this.dataApresentacao = dataApresentacao;
        }

        public Date getDataApresentacao() {
            return dataApresentacao;
        }

    }

}
