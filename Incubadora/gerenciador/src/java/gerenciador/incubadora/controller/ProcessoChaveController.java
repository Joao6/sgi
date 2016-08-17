/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.incubadora.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import gerenciador.incubadora.model.ServiceLocator;
import gerenciador.incubadora.model.dao.PraticaChaveDAO;
import gerenciador.incubadora.model.entity.ArquivoProcesso;
import gerenciador.incubadora.model.entity.PraticaChave;
import gerenciador.incubadora.model.entity.ProcessoChave;
import gerenciador.incubadora.model.entity.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Type;
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
public class ProcessoChaveController {

   @RequestMapping(value = "/processo-chave/{id}/pratica-chave", method = RequestMethod.GET)
   public ModelAndView listPratica(@PathVariable Long id) {
      ModelAndView mv;
      try {
         Map<String, Object> criteria = new HashMap<String, Object>();
         criteria.put(PraticaChaveDAO.CRITERION_PROCESSO_ID, id);
         List<PraticaChave> praticaList = ServiceLocator.getPraticaChaveService().readByCriteria(criteria);
         ProcessoChave processo = ServiceLocator.getProcessoChaveService().readById(id);
         mv = new ModelAndView("pratica-chave/list");
         mv.addObject("praticaList", praticaList);
         mv.addObject("processo", processo);
      } catch (Exception ex) {
         mv = new ModelAndView("error");
         mv.addObject("e", ex);
      }
      return mv;
   }

   @RequestMapping(value = "/processo-chave/anexo", method = RequestMethod.POST)
   public ModelAndView anexarArquivo(@RequestParam("arquivo") MultipartFile newFile, Long processoId, HttpSession session) {
      ModelAndView mv;
      try {
         mv = new ModelAndView("redirect:/incubadora/processo-chave");
         if (newFile != null && !newFile.isEmpty()) {
            ProcessoChave pc = ServiceLocator.getProcessoChaveService().readById(processoId);

            if (pc != null) {
               if (pc.getAnexo() != null && pc.getAnexo().getPath() != null && !pc.getAnexo().getPath().isEmpty()) {
                  File oldFile = new File(pc.getAnexo().getPath());
                  if (oldFile.delete()) {
                     System.out.println(pc.getAnexo().getPath() + " - Deletado com sucesso!");
                  } else {
                     System.out.println("Erro ao tentar deletar anexo");
                  }
                  oldFile.exists();
               }
               //Trocar pelo caminho do servidor
               String destination = "C:/sgi/processo-chave/anexos/" + newFile.getOriginalFilename();
               ArquivoProcesso ap = new ArquivoProcesso(destination.replace("/", "\\\\"));
               pc.setAnexo(ap);

               Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
               pc.setGestor(usuario);
               ServiceLocator.getProcessoChaveService().update(pc);

               File arquivo = new File(destination);
               newFile.transferTo(arquivo);
               mv.addObject("statusArquivoAnexado", "SUCCESS");
            }

         }

      } catch (Exception e) {
         mv = new ModelAndView("error");
         mv.addObject("e", e);
      }
      return mv;
   }

   @RequestMapping(value = "/processo-chave/{id}/anexo/download", method = RequestMethod.GET)
   public void handleFileDownload(HttpServletResponse response, @PathVariable Long id) throws Exception {

      ProcessoChave pc = ServiceLocator.getProcessoChaveService().readById(id);
      if (pc.getAnexo() != null && pc.getAnexo().getPath() != null) {
         File file = new File(pc.getAnexo().getPath());

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

   /**
    * SERVIÇO PARA ATUALIZAR PROCESSO-CHAVE
    *
    * @param processoChave
    * @param response
    * @param session
    *
    */
   @RequestMapping(value = "/processo-chave/update/api", method = RequestMethod.POST)
   public void update(@RequestBody String processoChave, HttpSession session, HttpServletResponse response) {

      try {
         Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

         Type type = new TypeToken<ProcessoChave>() {
         }.getType();
         Gson g = new Gson();
         ProcessoChave pc = g.fromJson(processoChave, type);

         if (pc != null) {
            pc.setGestor(usuario);
            ProcessoChave processo = ServiceLocator.getProcessoChaveService().readById(pc.getId());
            ArquivoProcesso ap = new ArquivoProcesso(processo.getAnexo().getPath());
            pc.setAnexo(ap);
            ServiceLocator.getProcessoChaveService().update(pc);
            response.setStatus(200);
         } else {
            response.setStatus(500);
         }

      } catch (Exception e) {
         response.setStatus(500);
      }

   }

   /**
    * SERVIÇO PARA EXCLUIR PROCESSO-CHAVE
    *
    * @param id
    * @param response
    */
   @RequestMapping(value = "/processo-chave/delete/api/{id}", method = RequestMethod.GET)
   @ResponseBody
   public void deleteProcessoChave(@RequestBody @PathVariable Long id, HttpServletResponse response) {
      try {

         ProcessoChave pc = ServiceLocator.getProcessoChaveService().readById(id);

         if (pc.getAnexo() != null && pc.getAnexo().getPath() != null) {
            File anexo = new File(pc.getAnexo().getPath());
            anexo.delete();
            anexo.exists();
         }
         ServiceLocator.getProcessoChaveService().delete(id);
         response.setStatus(200);
      } catch (Exception e) {
         response.setStatus(500);
      }
   }

   /**
    * SERVIÇO PARA OBTER OS PROCESSOS-CHAVE CADASTRADOS
    *
    * @param response {HttpServletReponse}
    * @return processos {String}
    */
   @RequestMapping(value = "/processo-chave/api", method = RequestMethod.GET)
   @ResponseBody
   public String getProcessosChave(HttpServletResponse response) {
      String processos = null;
      try {
         Map<String, Object> criteria = new HashMap<String, Object>();
         List<ProcessoChave> processosChave = ServiceLocator.getProcessoChaveService().readByCriteria(criteria);
         if (processosChave != null && !processosChave.isEmpty()) {
            Gson g = new Gson();
            processos = g.toJson(processosChave);
            response.setStatus(200);
         } else {
            response.setStatus(204);
         }
      } catch (Exception e) {
         response.setStatus(500);
      }
      return processos;
   }

   /**
    * SERVIÇO PARA CADASTRAR PROCESSO-CHAVE
    *
    * @param processoChave {String}
    * @param response {HttpServletResponse}
    * @param session
    */
   @RequestMapping(value = "/processo-chave/add/api", method = RequestMethod.POST)
   public void addProcessoChave(@RequestBody String processoChave, HttpServletResponse response, HttpSession session) {
      try {

         Type type = new TypeToken<ProcessoChave>() {
         }.getType();

         Gson g = new Gson();
         ProcessoChave pc = g.fromJson(processoChave, type);

         Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
         pc.setGestor(usuario);
         ServiceLocator.getProcessoChaveService().create(pc);
         response.setStatus(200);
      } catch (Exception e) {
         e.printStackTrace();
         response.setStatus(500);
      }
   }

}
