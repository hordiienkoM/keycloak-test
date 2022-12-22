package com.hordiienko.keycloak_test.controller;

import com.hordiienko.keycloak_test.dto.AttributeValuePostDto;
import com.hordiienko.keycloak_test.dto.UserPostDto;
import com.hordiienko.keycloak_test.entity.User;
import com.hordiienko.keycloak_test.repository.UserRepository;
import com.hordiienko.keycloak_test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.NotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userTargetService;

    @PostMapping
    public User createUser(@RequestBody UserPostDto userDto) {
        return userTargetService.create(userDto);
    }

    @DeleteMapping
    public String deleteByUsername(String username) {
        userTargetService.deleteByUsername(username);
        return "deleted";
    }

    @PostMapping("/keycloak")
    public String moveAllToKeycloak() {
        userTargetService.moveAllToKeycloak();
        return "success";
    }

    @GetMapping
    public User getExample() {
        AttributeValuePostDto attr1 = AttributeValuePostDto.builder()
                .attributeName("phone")
                .attributeValue("Samsung s400")
                .build();

        AttributeValuePostDto attr2 = AttributeValuePostDto.builder()
                .attributeName("life-style")
                .attributeValue("Something wrong...")
                .build();

        Set<AttributeValuePostDto> attributes = new HashSet<>();
        attributes.add(attr1);
        attributes.add(attr2);

        Set<String> roleNames = new HashSet<>(Arrays.asList("ROLE_ADMIN", "ROLE_USER"));

        UserPostDto.builder()
                .username("alan")
                .firstname("al")
                .lastname("an")
                .email("al@an.la")
                .password("qwe123")
                .attributes(attributes)
                .roleNames(roleNames)
                .build();

        return userRepository.findByUsername("alan").orElseThrow(NotFoundException::new);
    }
}
