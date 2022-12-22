package com.hordiienko.keycloak_test.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserPostDto {
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Set<AttributeValuePostDto> attributes;
    private Set<String> roles;
}
