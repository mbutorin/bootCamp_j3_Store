package com.colvir.j3.store.dto;

import com.colvir.j3.store.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String login;
    private String name;
    private Integer role;

    public UserDto(final UserEntity entity) {
        this.id = entity.getId();
        this.login = entity.getLogin();
        this.name = entity.getName();
        this.role = entity.getRole();
    }
}
