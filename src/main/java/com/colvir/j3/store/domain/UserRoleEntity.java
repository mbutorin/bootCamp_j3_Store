package com.colvir.j3.store.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@SuppressWarnings("com.haulmont.jpb.EntityIdMissingInspection")
@Setter
@Getter
@Entity
@Table(name = "roles", schema = "public")
@NoArgsConstructor
public class UserRoleEntity {

    @Id
    @GeneratedValue(generator = "role_seq")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private ERole name;

//    public UserRoleEntity(final UserDto userDto) {
//        this.id = userDto.getId();
//        this.login = userDto.getLogin();
//        this.nickname = userDto.getNickname();
//        this.userpwd = userDto.getPassword();
//    }
}
