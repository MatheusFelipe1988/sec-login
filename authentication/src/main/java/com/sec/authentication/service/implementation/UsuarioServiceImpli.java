package com.sec.authentication.service.implementation;

import com.sec.authentication.domain.Usuario;
import com.sec.authentication.dto.UsuarioDTO;
import com.sec.authentication.repository.UsuarioRepository;
import com.sec.authentication.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpli implements UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        Usuario existUsuario = repository.findByLogin(usuarioDTO.login());

        if(existUsuario != null){
            throw new RuntimeException("User exist");
        }

        var passwordHash = passwordEncoder.encode(usuarioDTO.senha());

        Usuario entity = new Usuario(usuarioDTO.nome(),
                usuarioDTO.login(), passwordHash, usuarioDTO.usuarioRole());
        Usuario newUser = repository.save(entity);

        return new UsuarioDTO(newUser.getNome(),
                newUser.getLogin(), newUser.getSenha(), newUser.getUsuarioRole());
    }
}
