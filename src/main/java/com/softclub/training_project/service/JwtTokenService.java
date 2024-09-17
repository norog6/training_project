package com.softclub.training_project.service;


import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
    public class JwtTokenService {

        private final JwtDecoder jwtDecoder;

        public JwtTokenService() {
            this.jwtDecoder = JwtDecoders.fromIssuerLocation("http://localhost:8081/realms/myrealm");
        }

        public Jwt getDecodedJwt(String token) {
            return jwtDecoder.decode(token);
        }

        public List<String> getRolesFromToken(String token) {
            Jwt jwt = getDecodedJwt(token);
            return (List<String>) jwt.getClaimAsMap("realm_access").get("roles");
        }
    }
