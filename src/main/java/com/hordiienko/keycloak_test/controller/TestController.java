package com.hordiienko.keycloak_test.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/public")
    public String index() {
        return "Hello any user";
    }

    @GetMapping("/user")
    public String helloUser() {
        return "Hello user";
    }

    @GetMapping("/admin")
    public String helloAdmin() {
        return "Hello admin";
    }
}
