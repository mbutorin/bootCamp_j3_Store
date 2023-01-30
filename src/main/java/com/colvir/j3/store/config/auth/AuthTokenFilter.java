package com.colvir.j3.store.config.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {
    private final AuthService authenticationService;

    @Override
    protected void doFilterInternal(
            final @NotNull HttpServletRequest request,
            final @NotNull HttpServletResponse response,
            final @NotNull FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            authenticationService.authoriseRequest(request);
        } catch (final Exception ex) {
            throw ex;
        }

        filterChain.doFilter(request, response);
    }

}
