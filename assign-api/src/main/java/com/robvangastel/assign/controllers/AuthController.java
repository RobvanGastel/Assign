package com.robvangastel.assign.controllers;

import com.robvangastel.assign.security.Credentials;
import com.robvangastel.assign.security.IdToken;
import com.robvangastel.assign.services.UserService;
import io.swagger.annotations.Api;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Rob van Gastel
 */

@Stateless
@Path("/auth")
@Api(tags = {"auth"}, value = "/auth", description = "Manages the authentication of the users")
@Produces({MediaType.APPLICATION_JSON})
public class AuthController {

    @Inject
    private UserService service;

    /***
     * Authenticate the user by creating a JWT token.
     * @param username
     * @param password
     * @return Response with the JWT token
     * @throws Exception when invalid credentials are given.
     */
    @POST
    public Response authenticate(@QueryParam("email") String username,
                                 @QueryParam("password") String password) throws Exception {
        IdToken token = service.authenticate(new Credentials(username, password));
        return Response.ok(token).build();
    }

    // TODO add change password
}
