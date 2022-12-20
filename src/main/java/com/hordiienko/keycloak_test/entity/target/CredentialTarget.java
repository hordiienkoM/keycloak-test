package com.hordiienko.keycloak_test.entity.target;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Table(name = "credential")
public class CredentialTarget {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    private String id;
    @Column(name = "type")
    private String type;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",
            insertable = false,
            updatable = false)
    private UserTarget user;
    @Column(name = "created_date")
    private Long createdDate;
    @Column(name = "secret_data")
    private String secretData;
    @Column(name = "credential_data")
    private String credentialData;
    @Column(name = "priority")
    private Integer priority;
}
