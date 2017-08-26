package com.robvangastel.assign.controllers;

import com.robvangastel.assign.domain.Role;
import com.robvangastel.assign.domain.User;
import com.robvangastel.assign.firebase.domain.*;
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

        if(user.getFirebase().getNotificationKey() == null) {

            body.setNotification_key_name(user.getFirebase().getNotificationKeyName());
            body.getRegistration_ids().add(id);

            firebaseService.createNotificationkey(body, user.getId());
        } else {

            body.setNotification_key_name(user.getFirebase().getNotificationKeyName());
            body.setNotification_key(user.getFirebase().getNotificationKey());
            body.getRegistration_ids().add(id);

            firebaseService.addRegistrationId(body, user.getId());
        }

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

    @GET
    public Response afdsadsad() {
        // TODO Add notification
        String title = " wants to help you out!";
         String body = " offers to help you out with ";

        Payload payload = new Payload(
                new Notification(title, body),
                new Data(true),
                "f_BQ2nIdHeY:APA91bFeGN4WEPpSiK-lAj5pF6M_Rz04TatTHz7E0lTTffln7dOYoKO-E_Njh3IXTTwW2FLBabMEWr_ZmAoP1h5kYk5NusjsUqFxmJhSukGD9WIbyZuNeAgAzKnN2mg-ja0-ynEqCumf",  // Firebase key
                Priority.normal);

        firebaseService.sendNotification(payload, 10L);

        return Response.ok().build();
    }
}
