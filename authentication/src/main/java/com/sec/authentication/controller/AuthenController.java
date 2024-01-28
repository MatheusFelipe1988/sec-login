package com.sec.authentication.controller;

import com.sec.authentication.dto.AuthDTO;
import com.sec.authentication.service.AuthenticatiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authe")
public class AuthenController{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticatiService authenticatiService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String authe(@RequestBody AuthDTO authDTO){

        var usuarioAutheToken = new UsernamePasswordAuthenticationToken(authDTO.login()
                ,authDTO.senha());

        authenticationManager.authenticate(usuarioAutheToken);

        return authenticatiService.obterToken(authDTO);
    }
}
