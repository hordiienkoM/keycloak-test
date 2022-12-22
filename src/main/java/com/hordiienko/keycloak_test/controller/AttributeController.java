package com.hordiienko.keycloak_test.controller;

import com.hordiienko.keycloak_test.entity.Attribute;
import com.hordiienko.keycloak_test.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attributes")
public class AttributeController {

    @Autowired
    private AttributeService attributeService;

    @PostMapping
    public Attribute createAttribute(@RequestParam String attributeName) {
        return attributeService.createAttribute(attributeName);
    }
}
