package com.hordiienko.keycloak_test.service;

import com.hordiienko.keycloak_test.entity.target.CredentialTarget;
import com.hordiienko.keycloak_test.entity.target.RealmTarget;
import com.hordiienko.keycloak_test.entity.target.RoleTarget;
import com.hordiienko.keycloak_test.entity.target.UserTarget;
import com.hordiienko.keycloak_test.repository.target.CredentialTargetRepository;
import com.hordiienko.keycloak_test.repository.target.RealmTargetRepository;
import com.hordiienko.keycloak_test.repository.target.RoleTargetRepository;
import com.hordiienko.keycloak_test.repository.target.UserTargetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

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

    @Transactional
    public void createUser(String username) throws ChangeSetPersister.NotFoundException {
        UserTarget user = new UserTarget();
        user.setCreatedTimestamp(System.currentTimeMillis());
        user.setEmail("random1@gmail.co");
        RoleTarget role = roleRepository
                .findByName("ROLE_ADMIN")                             //magic word
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        user.setRoles(Collections.singleton(role));
        RealmTarget realm = realmRepository.findByName("my_realm")   //magic word
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        user.setRealm(realm);
        user.setUsername(username);
        user = userTargetRepository.save(user);
        userSetCredential(user);
    }

    private void userSetCredential(UserTarget user) {
        CredentialTarget credential = new CredentialTarget();
        credential.setUser(user);
        //many magic words and sentences
        credential.setType("password");
        credential.setPriority(10);
        credential.setCreatedDate(System.currentTimeMillis());
        credential.setCredentialData("{\"hashIterations\":27500,\"algorithm\":" +
                "\"pbkdf2-sha256\",\"additionalParameters\":{}}");
        credential.setSecretData("{\"value\":\"PPwBwHwagr9MKRjVhdFGHm7eFZ/2zCmTNjl9/4YttbhdKModu/G3xD/dATXLjsSXE/" +
                "0Lk/EIcUDM6YfDOJIoVg==\",\"salt\":\"QvgYkRh+XXT3Yqr9+wXvqQ==\",\"additionalParameters\":{}}");
        credentialTargetRepository.save(credential);
    }
}
