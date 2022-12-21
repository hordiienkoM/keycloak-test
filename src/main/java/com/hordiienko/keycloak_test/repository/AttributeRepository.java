package com.hordiienko.keycloak_test.repository;

import com.hordiienko.keycloak_test.entity.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {
}
