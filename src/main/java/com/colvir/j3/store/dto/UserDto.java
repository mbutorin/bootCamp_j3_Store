package com.colvir.j3.store.dto;

import com.colvir.j3.store.domain.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String nickname;

//    @JsonIgnore
    private String password;

    public UserDto(final UserEntity entity) {
        this.id = entity.getId();
        this.login = entity.getUsername();
        this.nickname = entity.getNickname();
        this.password = entity.getUserpwd();
    }
}
