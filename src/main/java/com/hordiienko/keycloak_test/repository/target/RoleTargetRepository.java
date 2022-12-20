package com.hordiienko.keycloak_test.repository.target;

import com.hordiienko.keycloak_test.entity.target.RoleTarget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleTargetRepository extends JpaRepository<RoleTarget, String> {
    Optional<RoleTarget> findByName(String roleName);
}
