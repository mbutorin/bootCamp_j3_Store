package com.colvir.j3.store.config.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class TokenDto {
    private String username;
    private List<String> roles;
    private String token;

    public TokenDto(final @NotNull UserDetails userDetails, final String token) {
        this.username = userDetails.getUsername();
        this.roles = userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList());
        this.token = token;
    }
}
