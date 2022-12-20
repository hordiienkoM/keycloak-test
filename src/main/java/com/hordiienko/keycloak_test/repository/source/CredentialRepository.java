package com.hordiienko.keycloak_test.repository.source;

import com.hordiienko.keycloak_test.entity.source.Credential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialRepository extends JpaRepository<Credential, String> {
}
