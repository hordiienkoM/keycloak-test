package com.hordiienko.keycloak_test.repository;

import com.hordiienko.keycloak_test.entity.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {
    Optional<Attribute> findByName(String attributeName);
}
