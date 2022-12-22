package com.hordiienko.keycloak_test.service;

import com.hordiienko.keycloak_test.entity.Role;
import com.hordiienko.keycloak_test.entity.User;
import com.hordiienko.keycloak_test.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role createRole(String roleName) {
        Role role = new Role();
        role.setName(roleName);
        return roleRepository.save(role);
    }

    public Set<Role> toRoles(Set<String> roleNames, User user) {
        return roleNames.stream()
                .map(roleName -> {
                    return roleRepository.findByName(roleName)
                            .orElseThrow(NotFoundException::new);
                }).collect(Collectors.toSet());
    }
}
