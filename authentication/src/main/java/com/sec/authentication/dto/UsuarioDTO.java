package com.sec.authentication.dto;

import com.sec.authentication.role.UsuarioRole;

public record UsuarioDTO(
        String nome,
        String login,
        String senha,
        UsuarioRole usuarioRole
) {
}
