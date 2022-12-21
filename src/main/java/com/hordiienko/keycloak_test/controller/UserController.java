package com.hordiienko.keycloak_test.controller;

import com.hordiienko.keycloak_test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userTargetService;

    @PostMapping("/user")
    public String createUser(@RequestParam String username) {
        userTargetService.createUser(username);
        return username;
    }

//    @DeleteMapping("/user")
//    public String deleteByName(@RequestParam String username) {
//        userTargetService.deleteByUsername(username);
//        return "deleted";
//    }
}
