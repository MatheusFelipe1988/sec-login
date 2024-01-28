package com.sec.authentication.service.implementation;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.sec.authentication.domain.Usuario;
import com.sec.authentication.dto.AuthDTO;
import com.sec.authentication.repository.UsuarioRepository;
import com.sec.authentication.service.AuthenticatiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class AuthService implements AuthenticatiService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return repository.findByLogin(login);
    }

    @Override
    public String obterToken(AuthDTO authDTO) {
        Usuario usuario = repository.findByLogin(authDTO.login());

        return generateToken(usuario);
    }

    public String generateToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256("key-secret");

            return JWT.create()
                    .withIssuer("Authentication")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(dataExpire())
                    .sign(algorithm);
        }catch (JWTCreationException exception){
            throw new RuntimeException("erro create token" + exception.getMessage());
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256("key-secret");

            return JWT.require(algorithm)
                    .withIssuer("Authentication")
                    .build()
                    .verify(token)
                    .getSubject();


        }catch (JWTVerificationException exception){
            return " ";
        }
    }

    private Instant dataExpire() {
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }
}
