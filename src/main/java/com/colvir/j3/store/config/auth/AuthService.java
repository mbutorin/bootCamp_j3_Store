package com.colvir.j3.store.config.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public interface AuthService extends UserDetailsService {
    UsernamePasswordAuthenticationToken setAuthentication(UserDetails userDetails);

    void authoriseRequest(HttpServletRequest request);

    String generateToken(Authentication authentication);

}
