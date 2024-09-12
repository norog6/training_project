package com.softclub.training_project.service;

import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.Collections;

@Service
public class RegistrationService {

//    @Value("${keycloak.auth-server-url}")
//    private String keycloakServerUrl;
//
//    @Value("${keycloak.realm}")
//    private String realm;
//
//    @Value("${keycloak.resource}")
//    private String clientId;
//
//    @Value("${keycloak.credentials.secret}")
//    private String clientSecret;
//
//    @Value("${admin.username}")
//    private String adminUsername;
//
//    @Value("${admin.password}")
//    private String adminPassword;
//
//    private Keycloak getKeycloakAdminClient() {
//        return KeycloakBuilder.builder()
//                .serverUrl(keycloakServerUrl)
//                .realm("master")  // "master" realm is the default for admin operations
//                .clientId("admin-cli")
//                .username(adminUsername)
//                .password(adminPassword)
//                .grantType("password")
//                .build();
//    }
//
//    public String registerUser(String username, String password, String email) {
//        Keycloak keycloak = getKeycloakAdminClient();
//
//        UserRepresentation user = new UserRepresentation();
//        user.setUsername(username);
//        user.setEmail(email);
//        user.setEnabled(true);
//
//        CredentialRepresentation credentials = new CredentialRepresentation();
//        credentials.setTemporary(false);
//        credentials.setType(CredentialRepresentation.PASSWORD);
//        credentials.setValue(password);
//
//        user.setCredentials(Collections.singletonList(credentials));
//
//        // Create the user in the Keycloak realm
//        Response response = keycloak.realm(realm).users().create(user);
//
//        if (response.getStatus() == 201) {
//            return "User created successfully";
//        } else {
//            return "Failed to create user";
//        }
//    }
}

