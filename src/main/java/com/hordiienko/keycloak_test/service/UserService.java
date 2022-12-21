package com.hordiienko.keycloak_test.service;

import com.hordiienko.keycloak_test.entity.User;
import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.*;

@Service
public class UserService {

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
    public synchronized void createUser(User newUser) {
        Keycloak keycloak = getKeycloakInstance();

        UserRepresentation user = new UserRepresentation();
        user.setUsername(newUser.getUsername());
        user.setFirstName(newUser.getFirstname());
        user.setLastName(newUser.getLastname());
        user.setCreatedTimestamp(System.currentTimeMillis());
        Map<String, List<String>> attributes = getUserAttributes(newUser);

        user.setAttributes(attributes);

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue("qwe123");

        user.setCredentials(Collections.singletonList(credential));

        user.setEnabled(true);
        Response response = keycloak.realm("my_realm").users().create(user);
        response.close();
        keycloak.close();

        addRolePassword(username);
    }

    private Map<String, List<String>> getUserAttributes(User newUser) {
        new HashMap<>();
        attributes.put("phone", Collections.singletonList("0853428674"));
        attributes.put("address", Collections.singletonList("whole the world"));
        attributes.put("life-style", Collections.singletonList("Black-life matter!!!"));
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

        keycloak.close();
    }
}
