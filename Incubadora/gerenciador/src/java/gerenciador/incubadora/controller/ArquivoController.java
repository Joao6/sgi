/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.incubadora.controller;

import gerenciador.incubadora.model.ServiceLocator;
import gerenciador.incubadora.model.entity.ArquivoProcesso;
import gerenciador.incubadora.model.entity.Empreendimento;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author William
 */
@Controller
public class ArquivoController {

    //Upload da logo
    @RequestMapping(value = "/imagem/{id}")
    public ModelAndView download(HttpServletResponse response, @PathVariable Long id)
            throws FileNotFoundException, Exception {

        InputStream is = new ByteArrayInputStream(FileUtils.readFileToByteArray(new File(Empreendimento.EMPREENDIMENTO_PATH_LOGO_DEFAULT)));

        ModelAndView mv = new ModelAndView("redirect:/empreendedor");
        Empreendimento empreendimento = ServiceLocator.getEmpreendimentoService().readById(id);
        mv.addObject("empreendimento", empreendimento);
        //is.close();
        response.setContentType("image/jpeg");
        IOUtils.copy(is, response.getOutputStream());
        response.flushBuffer();
        if (response.isCommitted()) {
            System.out.println("Resposta Comitada!");
        }
        return new ModelAndView("empreendimento/new");
    }

    @RequestMapping(value = "/arquivo/{id}", method = RequestMethod.GET)
    public ModelAndView getArquivo(@PathVariable Long id) {
        ArquivoProcesso arquivo = new ArquivoProcesso("http://localhost:8084/gerenciador/logo/13");
        String path = arquivo.getPath();
        ModelAndView mv = new ModelAndView("redirect:/incubadora/empreendimento");
        mv.addObject("arquivo", path);
        return mv;
    }

    @RequestMapping(value = "/imagem/upload/{id}", method = RequestMethod.GET)
    public ModelAndView upload(@PathVariable Long id) throws Exception {
        Empreendimento empreendimento = ServiceLocator.getEmpreendimentoService().readById(id);
        ModelAndView mv = new ModelAndView("upload/img");
        mv.addObject("empreendimento", empreendimento);
        return mv;
    }

    @RequestMapping(value = "/imagem/upload/{id}", method = RequestMethod.POST)
    public ModelAndView upload(@RequestParam("logo") MultipartFile file,
            @PathVariable Long id) throws IOException {
        ModelAndView mv;
        try {
            byte[] bytes = file.getBytes();
            Empreendimento empreendimento = new Empreendimento();
            empreendimento.setId(id);

            String logo = "C://imagens//empreendimento//" + id + " - " + file.getOriginalFilename();

            empreendimento.setLogo(logo);
            ServiceLocator.getEmpreendimentoService().uplaod(bytes, empreendimento);
            empreendimento = ServiceLocator.getEmpreendimentoService().readById(id);
            mv = new ModelAndView("redirect:/empreendimento");
            mv.addObject("empreendimento", empreendimento);
        } catch (Exception ex) {
            mv = new ModelAndView("error");
            mv.addObject("e", ex);
        }
        return mv;
    }

}
