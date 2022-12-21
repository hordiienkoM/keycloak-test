package com.hordiienko.keycloak_test.entity;


import com.hordiienko.keycloak_test.entity.enums.AttributeName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private AttributeName name;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "attribute", fetch = FetchType.LAZY)
    private Set<AttributeValue> values;
}
