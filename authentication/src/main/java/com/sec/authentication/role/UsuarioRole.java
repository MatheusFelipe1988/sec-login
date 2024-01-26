package com.sec.authentication.role;

import lombok.Getter;

@Getter
public enum UsuarioRole {
    ADMIN("admin"),
    USER("user");

    private String role;

    UsuarioRole(String role) {
        this.role = role;
    }
}
