package com.hordiienko.keycloak_test.repository.target;

import com.hordiienko.keycloak_test.entity.target.CredentialTarget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialTargetRepository extends JpaRepository<CredentialTarget, String> {
}
