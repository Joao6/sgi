package gerenciador.incubadora.interceptor;

import gerenciador.incubadora.model.entity.Usuario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Interceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {

        //REMOVE JSESSIONID DA URL
        String uri = request.getRequestURI();
        int index = uri.indexOf(";"); //JSESSIONID COMEÇA COM ';' APÓS A URL

        if (index != -1) {
            uri = uri.substring(0, index);
        }

        //CSS
        boolean ok = false;
        if (uri.startsWith("/gerenciador/css/") && uri.endsWith(".css")) {
            ok = true;
        }
        
        //JS
        if (uri.startsWith("/gerenciador/js/") && uri.endsWith(".js")) {
            ok = true;
        }

        // IMG e FONT
        if (uri.startsWith("/gerenciador/img/") && uri.endsWith(".jpg")) {
            ok = true;
        }
        if (uri.startsWith("/gerenciador/img/") && uri.endsWith(".png")) {
            ok = true;
        }
        if (uri.startsWith("/gerenciador/fonts/") && uri.endsWith(".svg")) {
            ok = true;
        }
        if (uri.startsWith("/gerenciador/fonts/") && uri.endsWith(".ttf")) {
            ok = true;
        }
        if (uri.startsWith("/gerenciador/fonts/") && uri.endsWith(".woff")) {
            ok = true;
        }
        if (uri.startsWith("/gerenciador/fonts/") && uri.endsWith(".eot")) {
            ok = true;
        }
 
        //pdf
        if (uri.startsWith("/gerenciador/pdf/") && uri.endsWith(".pdf")) {
            ok = true;
        }
        
        // HTML
        if (uri.startsWith("/gerenciador/html/") && uri.endsWith(".html")) {
            ok = true;
        }
        
        // Candidato
         if (uri.contains("/candidato/email")) {
            ok = true;
        }
         if (uri.contains("/candidato/novo")) { 
            ok = true;
        }
        

        //URI Livres de Autenticação
        if (!ok) {
  
           if (uri.contentEquals("/gerenciador/candidato")) {
                ok = true;
            }
            if (uri.contentEquals("/gerenciador/incubadora/candidato/novo")) {
                ok = true;
            }
            if (uri.startsWith("/gerenciador/sgi")) {
                ok = true;
            }
            if (uri.startsWith("/gerenciador/login")) {
                ok = true;
            }
        }

        //Verificar Autenticação
        if (!ok) {
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
            if (usuario != null /* && usuario.getTipoUsuario().equals(Usuario.TIPO_USUARIO_INCUBADORA)*/) {
                ok = true;
            }
            // Criar um mecanismo para verificar Autorização
        }

        //Finalizando
        if (!ok) {
            response.sendRedirect("/gerenciador/sgi");
        }

        return ok;
    }
}
