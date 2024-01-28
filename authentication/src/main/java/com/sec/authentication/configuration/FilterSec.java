package com.sec.authentication.configuration;

import com.sec.authentication.domain.Usuario;
import com.sec.authentication.repository.UsuarioRepository;
import com.sec.authentication.service.AuthenticatiService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FilterSec extends OncePerRequestFilter {
    @Autowired
    private AuthenticatiService authenticatiService;

    @Autowired
    private UsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = extraTokenHeader(request);

        if(token != null){
            String login = authenticatiService.validateToken(token);
            Usuario usuario = repository.findByLogin(login);

            var authentication = new UsernamePasswordAuthenticationToken
                    (usuario, null, usuario.getAuthorities()
                    );

            SecurityContextHolder.getContext().setAuthentication(authentication);

        }

        filterChain.doFilter(request, response);

    }

    public String extraTokenHeader(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");

        if (authHeader == null){
            return null;
        }

        if (!authHeader.split(" ")[0].equals("Bearer")){
            return null;
        }

        return authHeader.split(" ")[1];
    }

}
