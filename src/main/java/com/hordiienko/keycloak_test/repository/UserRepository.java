package com.hordiienko.keycloak_test.repository;

import com.hordiienko.keycloak_test.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
