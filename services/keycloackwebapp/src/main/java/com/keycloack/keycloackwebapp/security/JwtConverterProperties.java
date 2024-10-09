package com.keycloack.keycloackwebapp.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;


@Validated
@Configuration
@ConfigurationProperties(prefix = "jwt.auth.converter")
@Data
public class JwtConverterProperties {

    private String resourceId;
    private String principalAttribute;


}
