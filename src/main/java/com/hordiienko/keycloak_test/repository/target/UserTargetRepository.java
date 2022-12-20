package com.hordiienko.keycloak_test.repository.target;

import com.hordiienko.keycloak_test.entity.target.UserTarget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTargetRepository extends JpaRepository<UserTarget, String> {
}
