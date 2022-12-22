package com.hordiienko.keycloak_test.controller;

import com.hordiienko.keycloak_test.entity.AttributeValue;
import com.hordiienko.keycloak_test.service.AttributeValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("AttributeValues")
public class AttributeValueController {

    @Autowired
    private AttributeValueService attributeValueService;

    @PostMapping
    public AttributeValue createAttributeValue(@RequestParam Long attributeId,
                                               @RequestParam Long userId,
                                               @RequestParam String value) {
        return attributeValueService.createAttribute(attributeId, userId, value);
    }
}
