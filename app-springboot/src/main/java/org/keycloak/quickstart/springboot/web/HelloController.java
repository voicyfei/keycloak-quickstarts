package org.keycloak.quickstart.springboot.web;

import org.keycloak.quickstart.util.KeycloakContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping("/helloUser")
    public String helloUser() {
        String returnvalue = "hello, ";
        if (KeycloakContext.getPreferredUsername().isPresent()) {
            returnvalue = returnvalue + KeycloakContext.getPreferredUsername().get() + "\n";
        }
        if (KeycloakContext.getEmail().isPresent()) {
            returnvalue = returnvalue + "Email=" + KeycloakContext.getEmail().get() + "\n";
        }
        if (KeycloakContext.getId().isPresent()) {
            returnvalue = returnvalue + "keycloak id=" + KeycloakContext.getId().get() + "\n";
        }
        return returnvalue;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/helloAdmin")
    public String helloAdmin() {
        return "hello world, Admin";
    }
}