package gerenciador.incubadora.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import gerenciador.incubadora.model.ServiceLocator;
import gerenciador.incubadora.model.dao.UsuarioDAO;
import gerenciador.incubadora.model.entity.Usuario;
import org.springframework.util.DigestUtils;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsuarioController {

   @RequestMapping(value = "/login", method = RequestMethod.POST)
   public ModelAndView login(String usuario, String senha, HttpSession session) {
      ModelAndView mv;
      try {
         //  senha = DigestUtils.md2Hex(senha);
         Usuario usuarioAutenticado = ServiceLocator.getUsuarioService().login(usuario, senha);

         if (usuarioAutenticado != null) {
            session.setAttribute("usuarioLogado", usuarioAutenticado);
            mv = new ModelAndView("redirect:/usuario/home");
         } else {
            mv = new ModelAndView("redirect:/");
         }
      } catch (Exception e) {
         mv = new ModelAndView("error");
         mv.addObject("e", e);
      }
      return mv;
   }

   @RequestMapping(value = "/logout", method = RequestMethod.GET)
   public ModelAndView logout(HttpSession session) {
      session.invalidate();
      return new ModelAndView("redirect:/");
   }

   @RequestMapping(value = "/home", method = RequestMethod.GET)
   public ModelAndView goHome() {
      ModelAndView mv = new ModelAndView("usuario/home");
      return mv;
   }

   @RequestMapping(value = "/novo", method = RequestMethod.POST)
   public ModelAndView create(Usuario usuario) {
      ModelAndView mv;
      try {
         //usuario.setSenha(DigestUtils.md5Hex(usuario.getSenha()));
         ServiceLocator.getUsuarioService().create(usuario);
         mv = new ModelAndView("redirect:/");
      } catch (Exception e) {
         mv = new ModelAndView("error");
         mv.addObject("e", e);
      }
      return mv;
   }
   /* Serviços */

   @RequestMapping(value = "/login/validate", method = RequestMethod.POST)
   @ResponseBody
   public String validadeUser(@RequestBody String user, HttpServletResponse response) {
      String usuarioValidado = "{\'id\':null}";
      try {
         Type type = new TypeToken<Usuario>() {
         }.getType();
         Gson g = new Gson();
         Usuario u = g.fromJson(user, type);

         Map<String, Object> criteria = new HashMap<String, Object>();
         criteria.put(UsuarioDAO.CRITERION_EMAIL_EQ, u.getEmail());
         List<Usuario> usuarioList = ServiceLocator.getUsuarioService().readByCriteria(criteria);
         if (usuarioList == null) {
            System.out.println("usuario nao encontrado");
            response.setStatus(206);
         } else {
            Usuario test = usuarioList.get(0);
                      
            String senha, email, nome, sobrenome, telefone;
            nome = DigestUtils.md5DigestAsHex(test.getNome().getBytes());
            sobrenome = DigestUtils.md5DigestAsHex(test.getSobrenome().getBytes());            
            senha = DigestUtils.md5DigestAsHex(test.getSenha().getBytes());
            email = DigestUtils.md5DigestAsHex(test.getEmail().getBytes());
            telefone = DigestUtils.md5DigestAsHex(test.getTelefone().getBytes());
            
            
            test.setNome(nome);
            test.setSobrenome(sobrenome);
            test.setSenha(senha);
            test.setEmail(email);
            test.setTelefone(telefone);
            
           
            usuarioValidado = g.toJson(test);
            System.out.println("usuario id: " + test.getId());

            response.setStatus(200);
         }
      } catch (Exception e) {
         System.out.println("Exception ao procurar usuario: " + e.getMessage());
         response.setStatus(500);
      }
      return usuarioValidado;
   }

   @RequestMapping(value = "/login/default", method = RequestMethod.POST)
   @ResponseBody
   public String loginAdministrador(@RequestBody String user, HttpSession session,
           HttpServletResponse response) {
      String usuario = null;
      try {
         Type type = new TypeToken<Usuario>() {
         }.getType();
         Gson g = new Gson();
         Usuario u = g.fromJson(user, type);
         Usuario usuarioAutenticado = ServiceLocator.getUsuarioService().login(u.getEmail(), u.getSenha());

         if (usuarioAutenticado != null) {
            session.setAttribute("usuarioLogado", usuarioAutenticado);
            usuario = g.toJson(usuarioAutenticado);
            response.setStatus(200);
         } else {
            response.setStatus(500);
         }
      } catch (Exception e) {
         response.setStatus(500);
      }

      return usuario;
   }

   /* Fim Serviços */
   @RequestMapping(value = "/usuario/home", method = RequestMethod.GET)
   public ModelAndView home(HttpSession session) {
      ModelAndView mv = null;

      Usuario usuarioAutenticado = (Usuario) session.getAttribute("usuarioLogado");
      if (usuarioAutenticado.getTipoUsuario().equals(Usuario.TIPO_USUARIO_INCUBADORA)) {
         session.setAttribute("usuarioLogado", usuarioAutenticado);
         mv = new ModelAndView("usuario/incubadora/home");

      } else if (usuarioAutenticado.getTipoUsuario().equals(Usuario.TIPO_USUARIO_EMPREENDEDOR)) {
         session.setAttribute("usuarioLogado", usuarioAutenticado);
         mv = new ModelAndView("redirect:/empreendedor/home");
         mv.addObject("usuarioLogado", usuarioAutenticado);

      } else if (usuarioAutenticado.getTipoUsuario().equals(Usuario.TIPO_USUARIO_AVALIADOR)) {
         session.setAttribute("usuarioLogado", usuarioAutenticado);
         mv = new ModelAndView("redirect:/avaliador/home");
         mv.addObject("usuarioLogado", usuarioAutenticado);
      }

      return mv;
   }
}
