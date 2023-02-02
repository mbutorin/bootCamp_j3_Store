package com.colvir.j3.store.config.auth.impl;

import io.jsonwebtoken.*;
import com.colvir.j3.store.config.auth.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImplementation implements AuthService {
    private final UserDetailsServiceImpl userTechService;

    @Value("${mbutorin.app.jwtSecret}")
    private String jwtSecret;

    @Value("${mbutorin.app.jwtExpirationMs}")
    private int jwtExpiration;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return  userTechService.loadUserByUsername(username);
    }

    @Override
    public UsernamePasswordAuthenticationToken setAuthentication(UserDetails userDetails) {
        final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        return authenticationToken;
    }

    @Override
    public void authoriseRequest(final @NotNull HttpServletRequest request) {
        prepareToken(request.getHeader("Authorization"))
                .filter(this::tokenIsValid)
                .ifPresent(token -> addAuthorization(request, loadUserByUsername(getUserNameFromToken(token))));
    }

    private Optional<String> prepareToken(final String token) {
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return Optional.of(token.substring(7));
        }
        return Optional.empty();
    }

    private void addAuthorization(final HttpServletRequest request, final UserDetails userDetails) {
        setAuthentication(userDetails).setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    }

    @Override
    public String generateToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean tokenIsValid(final String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
//            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
//            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
//            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
//            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
//            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

}
