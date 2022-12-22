package com.hordiienko.keycloak_test.repository;

import com.hordiienko.keycloak_test.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.Stream;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Transactional
    void deleteAllByUsername(String username);

    Stream<User> findAllBy();
}
