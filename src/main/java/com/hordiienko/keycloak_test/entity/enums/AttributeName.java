package com.hordiienko.keycloak_test.entity.enums;

public enum AttributeName {
    PHONE("phone"),
    ADDRESS("address"),
    LIFE_STILE("life-stile");

    private final String attributeName;
    AttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeName() {
        return attributeName;
    }
}
