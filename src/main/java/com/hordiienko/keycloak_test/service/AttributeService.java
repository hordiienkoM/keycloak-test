package com.hordiienko.keycloak_test.service;

import com.hordiienko.keycloak_test.entity.Attribute;
import com.hordiienko.keycloak_test.repository.AttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttributeService {
    @Autowired
    private AttributeRepository attributeRepository;
    public Attribute createAttribute(String attributeName) {
        Attribute attribute = new Attribute();
        attribute.setName(attributeName);
        return attributeRepository.save(attribute);
    }
}
