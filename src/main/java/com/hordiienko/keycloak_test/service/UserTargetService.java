package com.hordiienko.keycloak_test.service;

import com.hordiienko.keycloak_test.entity.target.UserTarget;
import com.hordiienko.keycloak_test.repository.source.UserSourceRepository;
import com.hordiienko.keycloak_test.repository.target.CredentialTargetRepository;
import com.hordiienko.keycloak_test.repository.target.RealmTargetRepository;
import com.hordiienko.keycloak_test.repository.target.RoleTargetRepository;
import com.hordiienko.keycloak_test.repository.target.UserTargetRepository;
import lombok.var;
import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class UserTargetService {
    @Autowired
    private UserTargetRepository userTargetRepository;
    @Autowired
    private RoleTargetRepository roleRepository;
    @Autowired
    private CredentialTargetRepository credentialTargetRepository;
    @Autowired
    private RealmTargetRepository realmRepository;
    @Autowired
    private UserSourceRepository userSourceRepository;

    private Keycloak getKeycloakInstance() {
        return KeycloakBuilder.builder()
                .serverUrl("http://localhost:8911/auth")
                .grantType(OAuth2Constants.PASSWORD)
                .realm("master")
                .clientId("admin-cli")
                .username("root")
                .password("Root-123")
                .resteasyClient(
                        new ResteasyClientBuilderImpl()
                                .connectionPoolSize(10).build()
                ).build();
    }

    @Transactional
    public synchronized void createUser(String username) {
        Keycloak keycloak = getKeycloakInstance();

        UserRepresentation user = new UserRepresentation();
        user.setUsername(username);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setCreatedTimestamp(System.currentTimeMillis());
        user.setEnabled(true);
        Response response = keycloak.realm("my_realm").users().create(user);
        response.close();
        keycloak.close();

        addRolePassword(username);
    }

    public void addRolePassword(String username) {
        Keycloak keycloak = getKeycloakInstance();

        UserRepresentation userRepresentation = keycloak.realm("my_realm").users().search(username).get(0);
        UserResource userResource =
                keycloak.realm("my_realm").users().get(userRepresentation.getId());
        List<RoleRepresentation> rolesToAdd = Collections.singletonList(
                keycloak.realm("my_realm").roles().get("ROLE_ADMIN").toRepresentation()
        );
        userResource.roles().realmLevel().add(rolesToAdd);

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue("qwe123");
        userResource.credentials().add(credential);

        keycloak.close();
    }

    public void deleteByUsername(String username) {
        Set<UserTarget> users = userTargetRepository.findByUsername(username);
        userTargetRepository.deleteAll(users);
    }
}
