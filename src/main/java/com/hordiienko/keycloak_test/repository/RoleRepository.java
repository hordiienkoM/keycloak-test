package com.hordiienko.keycloak_test.repository;

import com.hordiienko.keycloak_test.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
