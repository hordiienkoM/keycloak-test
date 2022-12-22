package com.hordiienko.keycloak_test.service;

import com.hordiienko.keycloak_test.dto.AttributeValuePostDto;
import com.hordiienko.keycloak_test.entity.Attribute;
import com.hordiienko.keycloak_test.entity.AttributeValue;
import com.hordiienko.keycloak_test.entity.User;
import com.hordiienko.keycloak_test.repository.AttributeRepository;
import com.hordiienko.keycloak_test.repository.AttributeValueRepository;
import com.hordiienko.keycloak_test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AttributeValueService {
    @Autowired
    private AttributeValueRepository attributeValueRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AttributeRepository attributeRepository;
    public AttributeValue createAttribute(Long attributeId, Long userId, String value) {
        AttributeValue attributeValue = new AttributeValue();
        User user = userRepository.findById(userId).orElseThrow(NotFoundException::new);
        Attribute attribute = attributeRepository.findById(attributeId).orElseThrow(NotFoundException::new);
        attributeValue.setValue(value);
        attributeValue.setAttribute(attribute);
        attributeValue.setUser(user);
        return attributeValueRepository.save(attributeValue);
    }

    private Set<AttributeValue> toAttributeValues(Set<AttributeValuePostDto> attributesDto, User user) {
        return attributesDto.stream()
                .map(dto -> {
                    Attribute attribute = attributeRepository
                            .findByName(dto.getAttributeName())
                            .orElseThrow(NotFoundException::new);
                    return AttributeValue.builder()
                            .attribute(attribute)
                            .user(user)
                            .value(dto.getAttributeValue())
                            .build();
                }).collect(Collectors.toSet());
    }
}
