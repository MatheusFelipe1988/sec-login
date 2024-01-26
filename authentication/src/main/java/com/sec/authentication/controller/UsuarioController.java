package com.sec.authentication.controller;

import com.sec.authentication.dto.UsuarioDTO;
import com.sec.authentication.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    private UsuarioDTO save(@RequestBody UsuarioDTO usuarioDTO){
        return usuarioService.save(usuarioDTO);
    }


}
