package com.hordiienko.keycloak_test.service;

import com.hordiienko.keycloak_test.dto.AttributeValuePostDto;
import com.hordiienko.keycloak_test.dto.UserPostDto;
import com.hordiienko.keycloak_test.entity.Attribute;
import com.hordiienko.keycloak_test.entity.AttributeValue;
import com.hordiienko.keycloak_test.entity.Role;
import com.hordiienko.keycloak_test.entity.User;
import com.hordiienko.keycloak_test.repository.AttributeRepository;
import com.hordiienko.keycloak_test.repository.UserRepository;
import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Value("${keycloak.realm_name}")
    private String realmName;
    @Autowired
    private AttributeRepository attributeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AttributeValueService attributeValueService;

    public User create(UserPostDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setLastname(userDto.getLastname());
        user.setFirstname(userDto.getFirstname());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());

        Set<AttributeValue> attributes = toAttributeValues(userDto.getAttributes(), user);
        Set<Role> roles = toRoles
    }



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
    public synchronized void setToKeycloak(User newUser) {
        Keycloak keycloak = getKeycloakInstance();

        UserRepresentation user = toUserRepresentation(newUser);

        Response response = keycloak.realm(realmName).users().create(user);
        response.close();
        keycloak.close();

        addRoles(newUser);
    }

    private UserRepresentation toUserRepresentation(User newUser) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(newUser.getUsername());
        user.setFirstName(newUser.getFirstname());
        user.setLastName(newUser.getLastname());
        user.setEmail(newUser.getEmail());
        user.setCreatedTimestamp(System.currentTimeMillis());

        Map<String, List<String>> attributes = getUserAttributes(newUser);
        user.setAttributes(attributes);

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(newUser.getPassword());

        user.setCredentials(Collections.singletonList(credential));

        user.setEnabled(true);
        return user;
    }

    private Map<String, List<String>> getUserAttributes(User newUser) {
        return newUser
                .getAttributes()
                .stream()
                .collect(
                        Collectors.groupingBy(
                                attrValue -> attrValue.getAttribute().getName(),
                                Collectors.mapping(AttributeValue::getValue, Collectors.toList())
                        )
                );
    }

    public void addRoles(User user) {
        Keycloak keycloak = getKeycloakInstance();

        UserRepresentation userRepresentation = keycloak.realm(realmName)
                .users()
                .search(user.getUsername())
                .get(0);

        UserResource userResource = keycloak
                .realm(realmName)
                .users()
                .get(userRepresentation.getId());

        List<RoleRepresentation> rolesToAdd = getRealmRoles(keycloak, user);

        userResource.roles().realmLevel().add(rolesToAdd);

        keycloak.close();
    }

    private List<RoleRepresentation> getRealmRoles (Keycloak keycloak, User user) {
        return user.getRoles().stream()
                .map(Role::getName)
                .map(roleName -> {
                    return keycloak
                            .realm(realmName)
                            .roles()
                            .get(roleName)
                            .toRepresentation();
                })
                .collect(Collectors.toList());
    }
}
