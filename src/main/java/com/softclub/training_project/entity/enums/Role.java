package com.softclub.training_project.entity.enums;

import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
//public enum Role implements GrantedAuthority {
public enum Role {
    ADMIN("ADMIN"),
    USER("USER");

    private final String value;

//    @Override
//    public String getAuthority() {
//        return value;
//    }

}