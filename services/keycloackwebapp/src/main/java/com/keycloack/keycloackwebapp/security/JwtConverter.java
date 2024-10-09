package com.keycloack.keycloackwebapp.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JwtConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    private final JwtConverterProperties properties;

    public JwtConverter(JwtConverterProperties properties) {
        this.properties = properties;
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = Stream.concat(
                jwtGrantedAuthoritiesConverter.convert(jwt).stream(),
                extractResourceRoles(jwt).stream()).collect(Collectors.toSet());
        return new JwtAuthenticationToken(jwt, authorities, getPrincipalClaimName(jwt));
    }

    private String getPrincipalClaimName(Jwt jwt) {
        String claimName = JwtClaimNames.SUB;
        if (properties.getPrincipalAttribute() != null) {
            claimName = properties.getPrincipalAttribute();
        }
        return jwt.getClaim(claimName);
    }

    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        Map<String, Object> resource;
        Collection<String> resourceRoles = new ArrayList<>();

        // Récupérer les rôles sous "resource_access"
        if (resourceAccess != null &&
                (resource = (Map<String, Object>) resourceAccess.get(properties.getResourceId())) != null) {
            Collection<String> rolesFromResource = (Collection<String>) resource.get("roles");
            if (rolesFromResource != null) {
                resourceRoles.addAll(rolesFromResource);
            }
        }

        // Récupérer les rôles sous "realm_access" (s'il y en a)
        Map<String, Object> realmAccess = jwt.getClaim("realm_access");
        if (realmAccess != null) {
            Collection<String> rolesFromRealm = (Collection<String>) realmAccess.get("roles");
            if (rolesFromRealm != null) {
                resourceRoles.addAll(rolesFromRealm);
            }
        }

        // Récupérer les rôles sous "account" (s'il y en a)
        Map<String, Object> accountAccess = jwt.getClaim("resource_access");
        if (accountAccess != null) {
            Map<String, Object> account = (Map<String, Object>) accountAccess.get("account");
            Collection<String> rolesFromAccount = (Collection<String>) account.get("roles");
            if (rolesFromAccount != null) {
                resourceRoles.addAll(rolesFromAccount);
            }
        }

        return resourceRoles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toSet());
    }

}