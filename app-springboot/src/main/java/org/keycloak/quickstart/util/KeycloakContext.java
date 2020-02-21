package org.keycloak.quickstart.util;

import java.util.Optional;

import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import freemarker.core.OptInTemplateClassResolver;

public final class KeycloakContext {
    private KeycloakContext() {
    }

    public static Optional<AccessToken> getAccessToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication instanceof KeycloakAuthenticationToken) || !authentication.isAuthenticated()) {
            return Optional.empty();
        }
        KeycloakSecurityContext credentials = (KeycloakSecurityContext) authentication.getCredentials();
        return Optional.of(credentials.getToken());
    }

    public static Optional<String> getPreferredUsername() {
        Optional<AccessToken> accessToken = getAccessToken();
        return accessToken.map(AccessToken::getPreferredUsername);
    }

    public static Optional<String> getEmail() {
        Optional<AccessToken> accessToken = getAccessToken();
        return accessToken.map(AccessToken::getEmail);
    }

    public static Optional<String> getId(){
        Optional<AccessToken> accessToken = getAccessToken();
        return accessToken.map(AccessToken::getSubject);
    }
}