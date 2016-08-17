package gerenciador.incubadora.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import gerenciador.incubadora.model.ServiceLocator;
import gerenciador.incubadora.model.dao.ArquivoDAO;
import gerenciador.incubadora.model.dao.PraticaChaveDAO;
import gerenciador.incubadora.model.entity.ArquivoPratica;
import gerenciador.incubadora.model.entity.EstagioEvolucao;
import gerenciador.incubadora.model.entity.PraticaChave;
import gerenciador.incubadora.model.entity.ProcessoChave;
import gerenciador.util.GSONConverter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Aliane Leal
 */
@Controller
public class PraticaChaveController {

   /**
    * LISTAR PRÁTICAS-CHAVE - GET
    *
    * @param id
    * @param session
    *
    * @return
    */
   @RequestMapping(value = "/processo-chave/{id}/pratica-chave/add", method = RequestMethod.GET)
   public ModelAndView create(@PathVariable Long id, HttpSession session) {
      ModelAndView mv;
      try {
         List<EstagioEvolucao> estagioList = ServiceLocator.getEstagioEvolucaoService()
                 .readByCriteria(new HashMap<String, Object>());

         ProcessoChave processo = ServiceLocator.getProcessoChaveService().readById(id);
         if (processo != null) {
            session.setAttribute("processoChave", processo);
         }

         mv = new ModelAndView("pratica-chave/new");
      } catch (Exception ex) {
         mv = new ModelAndView("error");
         mv.addObject("e", ex);
      }
      return mv;
   }

   /**
    * CADASTRAR PRÁTICA-CHAVE - POST
    *
    *
    * @param data
    * @param id
    * @param response
    *
    * @return
    */
   @ResponseBody
   @RequestMapping(value = "/processo-chave/{id}/pratica-chave/nova", method = RequestMethod.POST)
   public String create(@RequestBody String data, @PathVariable Long id, HttpServletResponse response) {
      try {
         Type type = new TypeToken<List<PraticaChave>>() {
         }.getType();

         data = data.replaceAll("/", "-");
         List<PraticaChave> praticaList = (List<PraticaChave>) GSONConverter.convert(data, type);
         for (PraticaChave pratica : praticaList) {
            ServiceLocator.getPraticaChaveService().create(pratica);
         }
         response.setStatus(200);
         return "success";
      } catch (Exception ex) {
         ex.printStackTrace();
         response.setStatus(500);
      }
      return "";
   }

   /**
    * ATUALIZAR PRÁTICA-CHAVE - GET
    *
    * @param id
    *
    * @return
    */
   @RequestMapping(value = "/processo-chave/{processoID}/pratica-chave/{id}/alterar", method = RequestMethod.GET)
   public ModelAndView update(@PathVariable Long id) {
      ModelAndView mv;
      try {
         PraticaChave praticaChave = ServiceLocator.getPraticaChaveService().readById(id);
         List<EstagioEvolucao> estagioList = ServiceLocator.getEstagioEvolucaoService()
                 .readByCriteria(new HashMap<String, Object>());
         mv = new ModelAndView("pratica-chave/new");
         mv.addObject("praticaChave", praticaChave);
         mv.addObject("processo", praticaChave.getProcessoChave());
         mv.addObject("estagioList", estagioList);
         mv.addObject("status", "update");
      } catch (Exception e) {
         mv = new ModelAndView("error");
         mv.addObject("e", e);
      }
      return mv;
   }

   /**
    * ATUALIZAR PRÁTICA-CHAVE - POST
    *
    * @param praticaChave
    * @param processoID
    *
    * @return
    */
   @RequestMapping(value = "/processo-chave/{processoID}/pratica-chave/{id}/alterar", method = RequestMethod.POST)
   public ModelAndView update(PraticaChave praticaChave, @PathVariable Long processoID) {
      ModelAndView mv;
      try {
         ServiceLocator.getPraticaChaveService().update(praticaChave);
         mv = new ModelAndView("redirect:/processo-chave/" + processoID + "/pratica-chave");
      } catch (Exception e) {
         mv = new ModelAndView("error");
         mv.addObject("e", e);
      }
      return mv;
   }

   /**
    * DELETAR PRÁTICA-CHAVE
    *
    * @param id
    * @param processoID
    *
    * @return
    */
   @RequestMapping(value = "/processo-chave/{processoID}/pratica-chave/{id}/excluir", method = RequestMethod.GET)
   public ModelAndView delete(@PathVariable Long id, @PathVariable Long processoID) {
      ModelAndView mv;
      try {
         ServiceLocator.getPraticaChaveService().delete(id);
         mv = new ModelAndView("redirect:/processo-chave/" + processoID + "/pratica-chave");

         PraticaChave pc = ServiceLocator.getPraticaChaveService().readById(id);

         if (pc.getArquivos() != null && pc.getArquivos().isEmpty()) {

            for (ArquivoPratica ap : pc.getArquivos()) {
               File anexo = new File(ap.getPath());
               anexo.delete();
               anexo.exists();
            }

         }
      } catch (Exception e) {
         mv = new ModelAndView("error");
         mv.addObject("e", e);
      }
      return mv;
   }

   /**
    * SERVIÇOS DE CONSULTA DOS ESTADOS DE EVOLUÇÃO
    *
    * @param response {HttpServletResponse}
    * @return estagios {String}
    */
   @RequestMapping(value = "/pratica-chave/estagio-evolucao/api", method = RequestMethod.GET)
   @ResponseBody
   public String getEstagiosEvolucao(HttpServletResponse response) {
      String estagios = null;
      try {
         Map<String, Object> criteria = new HashMap<String, Object>();
         List<EstagioEvolucao> estagioList = ServiceLocator.getEstagioEvolucaoService().readByCriteria(criteria);
         if (estagioList != null && !estagioList.isEmpty()) {
            Gson g = new Gson();
            estagios = g.toJson(estagioList);
            response.setStatus(200);
         } else {
            response.setStatus(500);
         }
      } catch (Exception e) {
         response.setStatus(200);
      }
      return estagios;
   }

   /**
    * SERVIÇO CADASTRO DE PRÁTICAS-CHAVE
    *
    * @param praticas
    * @param response
    * @param session
    */
   @RequestMapping(value = "/pratica-chave/save/api", method = RequestMethod.POST)
   @ResponseBody
   public void savePraticasChave(@RequestBody String praticas, HttpServletResponse response, HttpSession session) {
      try {
         Type type = new TypeToken<List<PraticaChave>>() {
         }.getType();
         Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
         List<PraticaChave> praticaChaveList = g.fromJson(praticas, type);
         ProcessoChave processo = (ProcessoChave) session.getAttribute("processoChave");
         if (praticaChaveList != null && !praticaChaveList.isEmpty() && processo != null) {
            for (PraticaChave pc : praticaChaveList) {
               pc.setProcessoChave(processo);
               ServiceLocator.getPraticaChaveService().create(pc);
            }
            response.setStatus(200);
         } else {
            response.setStatus(500);
         }
      } catch (Exception e) {
         response.setStatus(500);
      }
   }

   @RequestMapping(value = "/pratica-chave/{idPratica}/edit", method = RequestMethod.GET)
   public ModelAndView editPraticaChave(@PathVariable Long idPratica, HttpSession session) {
      ModelAndView mv;
      try {
         PraticaChave pc = ServiceLocator.getPraticaChaveService().readById(idPratica);
         mv = new ModelAndView("pratica-chave/new");
         mv.addObject("praticaEdit", pc.getId());
      } catch (Exception e) {
         mv = new ModelAndView("error");
         mv.addObject("e", e);
      }
      return mv;
   }

   @RequestMapping(value = "/processo-chave/{idProcesso}/praticas-chave", method = RequestMethod.GET)
   public ModelAndView getPraticas(@PathVariable Long idProcesso, HttpSession session) {
      ModelAndView mv;
      try {
         Map<String, Object> criteria = new HashMap<String, Object>();
         criteria.put(PraticaChaveDAO.CRITERION_PROCESSO_ID, idProcesso);

         ProcessoChave processoChave = ServiceLocator.getProcessoChaveService().readById(idProcesso);
         if (processoChave != null) {
            session.setAttribute("processoChave", processoChave);
         }
         mv = new ModelAndView("pratica-chave/list");
      } catch (Exception e) {
         mv = new ModelAndView("error");
         mv.addObject("error", e);
      }
      return mv;
   }

   //Obter processo-chave
   @RequestMapping(value = "/pratica-chave/processo-chave/api", method = RequestMethod.GET)
   @ResponseBody
   public String getProcessoChave(HttpSession session, HttpServletResponse response) {
      String processo = null;
      try {
         ProcessoChave pc = (ProcessoChave) session.getAttribute("processoChave");
         if (pc != null) {
            Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            processo = g.toJson(pc);
            response.setStatus(200);
         } else {
            response.setStatus(500);
         }
      } catch (Exception e) {
         response.setStatus(500);
      }
      return processo;
   }

   // obter todas as praticas-chave de um determinado processo
   @RequestMapping(value = " /view/praticas-chave/from/processo-chave", method = RequestMethod.GET)
   @ResponseBody
   public String read(HttpSession session, HttpServletResponse response) {
      String praticas = "";
      try {
         ProcessoChave pc = (ProcessoChave) session.getAttribute("processoChave");

         Map<String, Object> criteria = new HashMap<String, Object>();
         criteria.put(PraticaChaveDAO.CRITERION_PROCESSO_ID, pc.getId());

         List<PraticaChave> praticaChaveList = ServiceLocator.getPraticaChaveService().readByCriteria(criteria);
         Gson g = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
         if (praticaChaveList != null && !praticaChaveList.isEmpty()) {
            praticas = g.toJson(praticaChaveList);
            response.setStatus(200);
         } else {
            response.setStatus(204);
            praticaChaveList = new ArrayList<PraticaChave>();
            praticas = g.toJson(praticaChaveList);
         }
      } catch (Exception e) {
         response.setStatus(500);
         e.printStackTrace();
      }
      return praticas;
   }

   // obter todas as praticas-chave 
   @RequestMapping(value = "/praticas-chave/all/api", method = RequestMethod.GET)
   @ResponseBody
   public String getPraticasChave(HttpServletResponse response) {
      String praticas = "";
      try {
         Map<String, Object> criteria = new HashMap<String, Object>();

         List<PraticaChave> praticaChaveList = ServiceLocator.getPraticaChaveService().readByCriteria(criteria);
         Gson g = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
         if (praticaChaveList != null && !praticaChaveList.isEmpty()) {
            praticas = g.toJson(praticaChaveList);
            response.setStatus(200);
         } else {
            response.setStatus(204);
            praticaChaveList = new ArrayList<PraticaChave>();
            praticas = g.toJson(praticaChaveList);
         }
      } catch (Exception e) {
         response.setStatus(500);
         e.printStackTrace();
      }
      return praticas;
   }

   /**
    * SERVIÇO PARA EXCLUIR PRÁTICA-CHAVE
    *
    * @param id
    * @param response
    */
   @RequestMapping(value = "/pratica-chave/delete/api/{id}", method = RequestMethod.GET)
   @ResponseBody
   public void deletePraticaChave(@RequestBody @PathVariable Long id, HttpServletResponse response) {
      try {

         PraticaChave pc = ServiceLocator.getPraticaChaveService().readById(id);
         for (ArquivoPratica ap : pc.getArquivos()) {
            File anexo = new File(ap.getPath());
            anexo.delete();
            anexo.exists();
         }
         ServiceLocator.getPraticaChaveService().delete(id);
         response.setStatus(200);
      } catch (Exception e) {
         response.setStatus(500);
      }
   }

   @RequestMapping(value = "/pratica-chave/anexo", method = RequestMethod.POST)
   public ModelAndView anexarArquivo(@RequestParam("arquivo") MultipartFile newFile, Long processoId, Long praticaId, HttpSession session) {
      ModelAndView mv;
      try {
         mv = new ModelAndView("redirect:/processo-chave/" + processoId + "/praticas-chave");
         if (newFile != null && !newFile.isEmpty()) {
            PraticaChave pc = ServiceLocator.getPraticaChaveService().readById(praticaId);

            if (pc != null) {

               Map<String, Object> criteria = new HashMap<String, Object>();
               List<ArquivoPratica> arquivoList = ServiceLocator.getArquivoService().readByCriteria(criteria);

               //Trocar pelo caminho do servidor
               String destination = "C:/sgi/pratica-chave/anexos/" + praticaId + " - " + newFile.getOriginalFilename();
               ArquivoPratica ap = new ArquivoPratica(destination.replace("/", "\\\\"));

               criteria.put(ArquivoDAO.CRITERION_PATH_ILIKE, destination);
               List<ArquivoPratica> arquivos = ServiceLocator.getArquivoService().readByCriteria(criteria);
               if (arquivos == null || arquivos.isEmpty()) {

                  ServiceLocator.getArquivoService().readByCriteria(criteria);

                  List<ArquivoPratica> arquivoPraticaList = new ArrayList<ArquivoPratica>();
                  arquivoPraticaList.add(ap);

                  pc.setArquivos(arquivoPraticaList);

                  ServiceLocator.getPraticaChaveService().update(pc);

                  File arquivo = new File(destination);
                  newFile.transferTo(arquivo);
               }

            }

         }

      } catch (Exception e) {
         mv = new ModelAndView("error");
         mv.addObject("e", e);
      }
      return mv;
   }

   @RequestMapping(value = "/pratica-chave/{idPratica}/anexo/{idAnexo}/download", method = RequestMethod.GET)
   public void handleFileDownload(HttpServletResponse response, @PathVariable Long idPratica, @PathVariable Long idAnexo) throws Exception {
      try {
         PraticaChave pc = ServiceLocator.getPraticaChaveService().readById(idPratica);
         if (pc.getArquivos() != null && !pc.getArquivos().isEmpty()) {

            for (ArquivoPratica ap : pc.getArquivos()) {
               if (ap.getId().equals(idAnexo)) {
                  File file = new File(ap.getPath());

                  response.setContentType("application/pdf");
                  response.setContentLength(new Long(file.length()).intValue());

                  try {
                     FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
                     response.setStatus(200);
                  } catch (IOException e) {
                     response.setStatus(500);
                     e.printStackTrace();
                  }
               }

            }

         } else {
            response.setStatus(404);
         }
      } catch (Exception e) {
         e.printStackTrace();
         response.setStatus(404);
      }

   }

   @RequestMapping(value = "/processo-chave/{idProcesso}/pratica-chave/{idPratica}/anexos", method = RequestMethod.GET)
   public ModelAndView getAnexos(@PathVariable Long idProcesso, @PathVariable Long idPratica) {
      ModelAndView mv = null;
      try {
         mv = new ModelAndView("pratica-chave/anexos-list");
         mv.addObject("idProcesso", idProcesso);
         mv.addObject("idPratica", idPratica);

         PraticaChave praticaList = ServiceLocator.getPraticaChaveService().readById(idPratica);
         List<ArquivoPratica> arquivos = new ArrayList<ArquivoPratica>();
         for (ArquivoPratica ap : praticaList.getArquivos()) {
            String nomeArquivo = ap.getPath().replace("\\\\", "/").split("/")[4];
            ArquivoPratica newAP = new ArquivoPratica(nomeArquivo);
            newAP.setId(ap.getId());
            arquivos.add(newAP);
         }
         mv.addObject("arquivos", arquivos);

      } catch (Exception e) {
         e.printStackTrace();
      }

      return mv;
   }

   @RequestMapping(value = "/excluir/anexo/{idAnexo}", method = RequestMethod.GET)
   @ResponseBody
   public void deleteAnexo(@RequestBody @PathVariable Long idAnexo, HttpServletResponse response) {
      try {
         ArquivoPratica ap = ServiceLocator.getArquivoService().readArquivoPraticaById(idAnexo);
         File anexo = new File(ap.getPath());
         boolean ok = anexo.delete();
         System.out.println("ArquivoPratica: " + idAnexo + "Excluido: " + ok);
         anexo.exists();
         ServiceLocator.getArquivoService().delete(idAnexo);
         response.setStatus(200);
      } catch (Exception e) {
         response.setStatus(500);
      }
   }

}
