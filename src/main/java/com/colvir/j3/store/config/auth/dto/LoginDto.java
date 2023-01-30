package com.colvir.j3.store.config.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class LoginDto {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}
