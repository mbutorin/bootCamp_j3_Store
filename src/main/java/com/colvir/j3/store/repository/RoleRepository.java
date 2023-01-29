package com.colvir.j3.store.repository;

import com.colvir.j3.store.domain.ERole;
import com.colvir.j3.store.domain.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<UserRoleEntity, Long> {
  Optional<UserRoleEntity> findByName(ERole name);
}
