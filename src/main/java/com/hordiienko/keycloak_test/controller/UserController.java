package com.hordiienko.keycloak_test.controller;

import com.hordiienko.keycloak_test.service.UserTargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserTargetService userTargetService;

    @GetMapping("/user")
    public String createUser(@RequestParam String username) {
        try {
            userTargetService.createUser(username);
        } catch (ChangeSetPersister.NotFoundException e) {
            return e.getLocalizedMessage();
        }
        return username;
    }
}
