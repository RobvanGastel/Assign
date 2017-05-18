package com.robvangastel.assign.controllers;

import com.robvangastel.assign.security.Credentials;
import com.robvangastel.assign.security.IdToken;
import com.robvangastel.assign.services.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Rob on 28-4-2017.
 */
@Stateless
@Path("/auth")
@Produces({MediaType.APPLICATION_JSON})
public class AuthController {

    @Inject
    private UserService service;

    @POST
    @Path("/login")
    public Response authenticate(@QueryParam("username") String username,
                         @QueryParam("password") String password) throws Exception {
        IdToken token = service.authenticate(new Credentials(username, password));
        return Response.ok(token).build();
    }
}
