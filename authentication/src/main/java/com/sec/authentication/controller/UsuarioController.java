package com.sec.authentication.controller;

import com.sec.authentication.dto.UsuarioDTO;
import com.sec.authentication.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    private UsuarioDTO save(@RequestBody UsuarioDTO usuarioDTO){
        return usuarioService.save(usuarioDTO);
    }

    @GetMapping("/admin")
    private String getAdmin(){
        return "admin permitido";
    }

    @GetMapping("/user")
    private String getUser(){
        return "usuario permitido";
    }


}
