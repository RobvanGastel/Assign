package com.robvangastel.assign.security.filter;

import com.robvangastel.assign.domain.Role;
import com.robvangastel.assign.security.Secured;
import com.robvangastel.assign.security.UserPrincipal;
import com.robvangastel.assign.security.jwt.JwtHelper;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Principal;

/**
 *
 * @author Rob van Gastel
 */

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    @Inject
    JwtHelper jwtHelper;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        SecurityContext originalContext = requestContext.getSecurityContext();

        String token = extractTokenFromHeader(requestContext);
        try {
            // Validate the token
            UserPrincipal claims = jwtHelper.parseToken(token);

            // Set the SecurityContext with the CustomContext
            requestContext.setSecurityContext(new AuthorizerContext(claims, originalContext.isSecure()));

        } catch (Exception e) {
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    private String extractTokenFromHeader(ContainerRequestContext requestContext) throws NotAuthorizedException {
        // Get the HTTP Authorization header from the request
        String authorizationHeader
                = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        // Check if the HTTP Authorization header is present and formatted correctly
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }
        // Extract the token from the HTTP Authorization header
        String token = authorizationHeader.substring("Bearer".length()).trim();
        return token;
    }

    // Custom SecurityContext class
    static class AuthorizerContext implements SecurityContext {

        private UserPrincipal principal;
        private boolean isSecure;

        public AuthorizerContext(UserPrincipal principal, boolean isSecure) {
            this.principal = principal;
            this.isSecure = isSecure;
        }

        @Override
        public Principal getUserPrincipal() {
            return this.principal;
        }

        @Override
        public boolean isUserInRole(String role) {
            return this.principal.getRoles().contains(Role.valueOf(role));
        }

        @Override
        public boolean isSecure() {
            return this.isSecure;
        }

        @Override
        public String getAuthenticationScheme() {
            return "JWT";
        }
    }
}
