package com.hordiienko.keycloak_test.controller;

import com.hordiienko.keycloak_test.entity.Role;
import com.hordiienko.keycloak_test.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public Role createRole(@RequestParam String roleName) {
        return roleService.createRole(roleName);
    }
}
