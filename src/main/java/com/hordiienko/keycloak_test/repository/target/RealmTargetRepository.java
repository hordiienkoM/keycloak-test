package com.hordiienko.keycloak_test.repository.target;

import com.hordiienko.keycloak_test.entity.target.RealmTarget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RealmTargetRepository extends JpaRepository<RealmTarget, String> {
    Optional<RealmTarget> findByName(String realmName);
}
