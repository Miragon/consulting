package io.miragon.camunda.sso.config.camunda;

import io.miragon.camunda.sso.config.spring.GrantedAuthoritiesExtractor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.rest.security.auth.AuthenticationProvider;
import org.camunda.bpm.engine.rest.security.auth.AuthenticationResult;

import java.security.Principal;

import static java.util.Collections.emptyList;

/**
 * Similar to camunda's {@link org.camunda.bpm.engine.rest.security.auth.impl.ContainerBasedAuthenticationProvider} but also adds SSO roles to the authentication result.
 */
public class OAuthContainerBasedAuthenticationProvider implements AuthenticationProvider {

    @Override
    public AuthenticationResult extractAuthenticatedUser(HttpServletRequest request, ProcessEngine engine) {
        Principal principal = request.getUserPrincipal();

        if (principal == null) {
            return AuthenticationResult.unsuccessful();
        }

        String name = principal.getName();
        if (name == null || name.isEmpty()) {
            return AuthenticationResult.unsuccessful();
        }

        AuthenticationResult result = AuthenticationResult.successful(name);
        result.setGroups(GrantedAuthoritiesExtractor.extractRoles(principal));
        result.setTenants(emptyList());
        return result;
    }

    @Override
    public void augmentResponseByAuthenticationChallenge(HttpServletResponse response, ProcessEngine engine) {
        // noop
    }
}
