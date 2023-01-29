package com.colvir.j3.store.domain;

import com.colvir.j3.store.dto.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
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
    private String username;

    @Column
    private String nickname;

    @Column(name = "password")
    private String userpwd;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<UserRoleEntity> roles = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<ProductReviewEntity> userReviews;


    public UserEntity(final UserDto userDto) {
        this.id = userDto.getId();
        this.username = userDto.getLogin();
        this.nickname = userDto.getNickname();
        this.userpwd = userDto.getPassword();
    }
}
