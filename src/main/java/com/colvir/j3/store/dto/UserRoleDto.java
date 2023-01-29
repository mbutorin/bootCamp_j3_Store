package com.colvir.j3.store.dto;

import com.colvir.j3.store.domain.UserRoleEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDto {
    private String login;
    private String role;

    public UserRoleDto(final UserRoleEntity entity) {
//        this.login = entity.getLogin();
//        this.role = entity.getRole();
    }
}
