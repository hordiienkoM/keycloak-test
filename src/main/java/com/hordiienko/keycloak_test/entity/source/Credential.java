package com.hordiienko.keycloak_test.entity.source;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Table(name = "credential")
public class Credential {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    private String id;
    @Column(name = "type")
    private String type;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",
            insertable = false,
            updatable = false)
    private UserSource userId;
    @Column
    private Long createdDate;
    @Column
    private String secretData;
    @Column
    private String credentialData;
    @Column
    private Integer priority;
}
