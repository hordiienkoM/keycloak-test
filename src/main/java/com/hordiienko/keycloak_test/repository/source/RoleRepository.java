package com.hordiienko.keycloak_test.repository.source;

import com.hordiienko.keycloak_test.entity.source.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
