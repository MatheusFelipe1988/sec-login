package com.sec.authentication.service;

import com.sec.authentication.dto.AuthDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthenticatiService extends UserDetailsService {
    public String obterToken(AuthDTO authDTO);

    public String validateToken(String token);


}
