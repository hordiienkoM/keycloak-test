package com.hordiienko.keycloak_test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/user_again")
    public String helloUserAgain() {
        return "Hello user again";
    }

    @GetMapping("/admin")
    public String helloAdmin() {
        return "Hello admin";
    }
}
