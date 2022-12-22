package com.hordiienko.keycloak_test.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttributeValuePostDto {
    private String attributeName;
    private String attributeValue;
}
