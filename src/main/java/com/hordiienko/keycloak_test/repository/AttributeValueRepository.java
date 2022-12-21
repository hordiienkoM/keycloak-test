package com.hordiienko.keycloak_test.repository;

import com.hordiienko.keycloak_test.entity.AttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeValueRepository extends JpaRepository<AttributeValue, Long> {
}
