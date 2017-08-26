package com.robvangastel.assign.controllers;

import com.robvangastel.assign.domain.Role;
import com.robvangastel.assign.domain.User;
import com.robvangastel.assign.firebase.domain.Body;
import com.robvangastel.assign.firebase.FirebaseService;
import com.robvangastel.assign.security.Secured;
import com.robvangastel.assign.services.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 * @author Rob van Gastel
 */
@Stateless
@Path("/firebase")
@Secured({Role.USER})
@Produces({MediaType.APPLICATION_JSON})
public class FirebaseController {

    @Inject
    private FirebaseService firebaseService;

    @Inject
    private UserService userService;

    @Context
    private SecurityContext securityContext;

    @POST
    public Response createNotificationKey(@QueryParam("id") String id) {

        User user = userService.findByEmail(securityContext.getUserPrincipal().getName());

        Body body = new Body();
        body.setNotification_key_name(user.getFirebase().getNotificationKeyName());
        body.getRegistration_ids().add(id);

        firebaseService.createNotificationkey(body, user.getId());
        return Response.ok().build();
    }

    @PUT
    public Response addRegistrationId(@QueryParam("id") String id) {

        User user = userService.findByEmail(securityContext.getUserPrincipal().getName());

        Body body = new Body();
        body.setNotification_key_name(user.getFirebase().getNotificationKeyName());
        body.setNotification_key(user.getFirebase().getNotificationKey());
        body.getRegistration_ids().add(id);

        firebaseService.addRegistrationId(body, user.getId());
        return Response.ok().build();
    }

    @DELETE
    public Response removeRegistrationId(@QueryParam("id") String id) {

        User user = userService.findByEmail(securityContext.getUserPrincipal().getName());

        Body body = new Body();
        body.setNotification_key_name(user.getFirebase().getNotificationKeyName());
        body.setNotification_key(user.getFirebase().getNotificationKey());
        body.getRegistration_ids().add(id);

        firebaseService.removeRegistrationId(body, user.getId());
        return Response.ok().build();
    }
}