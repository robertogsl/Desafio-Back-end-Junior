package com.example.project.config;

import com.example.project.model.Usuario;
import com.example.project.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class BasicAuthInterceptor implements HandlerInterceptor {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String authorization = request.getHeader("Authorization");

        if (request.getRequestURI().startsWith("/register")) {
            return true;
        }

        if (request.getMethod().equalsIgnoreCase("DELETE")) {
            return true;
        }

        if (authorization == null || !authorization.startsWith("Basic")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setHeader("WWW-Authenticate", "Basic realm=realm");
            return false;
        }

        String base64Credentials = authorization.substring("Basic".length()).trim();
        byte[] credentials = Base64.getDecoder().decode(base64Credentials);
        String[] values = new String(credentials, StandardCharsets.UTF_8).split(":", 2);

        Usuario user = usuarioRepository.findByNomeAndSenha(values[0], values[1]);

        if (user == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setHeader("WWW-Authenticate", "Basic realm=realm");
            return false;
        }

        request.setAttribute("usuario", user);
        return true;
    }
}
