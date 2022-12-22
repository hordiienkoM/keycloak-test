package com.hordiienko.keycloak_test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
public class UserPostDto {
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Set<AttributeValuePostDto> attributes;
    private Set<String> roleNames;
}
