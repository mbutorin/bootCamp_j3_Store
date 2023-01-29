package com.colvir.j3.store.repository;

import com.colvir.j3.store.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findById(Long id);
    Boolean existsByUsername(String username);

//    Optional<User> findByUsername(String username);
}
