package com.hordiienko.keycloak_test.repository.source;

import com.hordiienko.keycloak_test.entity.source.UserSource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSourceRepository extends JpaRepository<UserSource, String> {
}
