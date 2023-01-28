package com.colvir.j3.store.domain;

import com.colvir.j3.store.dto.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "user", schema = "public")
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(generator = "user_seq")
    private Long id;

    @Column
    private String login;


    @Column
    private String name;


    @Column
    private Integer role;

    @OneToMany(mappedBy = "user")
    private Set<ProductReviewEntity> userReviews;


    public UserEntity(final UserDto userDto) {
        this.id = userDto.getId();
        this.login = userDto.getLogin();
        this.name = userDto.getName();
        this.role = userDto.getRole();
    }
}
