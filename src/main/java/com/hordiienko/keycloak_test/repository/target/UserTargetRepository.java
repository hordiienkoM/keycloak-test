package com.hordiienko.keycloak_test.repository.target;

import com.hordiienko.keycloak_test.entity.target.UserTarget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UserTargetRepository extends JpaRepository<UserTarget, String> {
    Set<UserTarget> findByUsername(String username);
}
