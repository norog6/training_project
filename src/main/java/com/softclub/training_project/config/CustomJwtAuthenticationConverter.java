package com.softclub.training_project.config;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

public class CustomJwtAuthenticationConverter extends JwtAuthenticationConverter {

    public CustomJwtAuthenticationConverter() {
        // Используем JwtGrantedAuthoritiesConverter для извлечения ролей из "authorities"
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

        // Указываем, что мы работаем с claim "authorities", а не с "scope"
        grantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");
        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");

        // Устанавливаем кастомный конвертер в JwtAuthenticationConverter
        this.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
    }
}





