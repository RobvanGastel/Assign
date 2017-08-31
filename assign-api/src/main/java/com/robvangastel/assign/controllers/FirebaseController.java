package com.robvangastel.assign.controllers;

import com.robvangastel.assign.domain.Reply;
import com.robvangastel.assign.domain.Role;
import com.robvangastel.assign.domain.User;
import com.robvangastel.assign.firebase.FirebaseService;
import com.robvangastel.assign.firebase.domain.Body;
import com.robvangastel.assign.firebase.domain.Data;
import com.robvangastel.assign.firebase.domain.Notification;
import com.robvangastel.assign.firebase.domain.Payload;
import com.robvangastel.assign.security.Secured;
import com.robvangastel.assign.services.ReplyService;
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
    private ReplyService replyService;

    @Inject
    private UserService userService;

    @Context
    private SecurityContext securityContext;

    /***
     * Create Notification key or add registration id to Firebase
     * @param token being added
     * @return Statuscode indicating success or failure
     * @throws Exception
     */
    @POST
    public Response createNotificationKey(@QueryParam("token") String token) throws Exception {

        User user = userService.findByEmail(securityContext.getUserPrincipal().getName());
        Body body = new Body();

        if (user.getFirebase().getNotificationKey() == null) {

            body.setNotification_key_name(user.getFirebase().getNotificationKeyName());
            body.getRegistration_ids().add(token);

            firebaseService.createNotificationkey(body, user.getId());
        } else {

            body.setNotification_key_name(user.getFirebase().getNotificationKeyName());
            body.setNotification_key(user.getFirebase().getNotificationKey());
            body.getRegistration_ids().add(token);

            firebaseService.addRegistrationId(body, user.getId());
        }

        return Response.ok().build();
    }

    /***
     * Remove registration id from Firebase
     * @param token being removed
     * @return Statuscode indicating success or failure
     * @throws Exception
     */
    @DELETE
    public Response removeRegistrationId(@QueryParam("token") String token) throws Exception {

        User user = userService.findByEmail(securityContext.getUserPrincipal().getName());

        Body body = new Body();
        body.setNotification_key_name(user.getFirebase().getNotificationKeyName());
        body.setNotification_key(user.getFirebase().getNotificationKey());
        body.getRegistration_ids().add(token);

        firebaseService.removeRegistrationId(body, user.getId());
        return Response.ok().build();
    }

    @GET
    public Response test() throws Exception {

        User user = userService.findByEmail(securityContext.getUserPrincipal().getName());
        Reply entity = replyService.findById(30);

        String title = entity.getUser().getName() + " wants to help you out!";
        String body = entity.getUser().getName() + " offers to help you out with " + entity.getPost().getTitle();

        Payload payload = new Payload(
                new Notification(title, body),
                new Data(true),
                "APA91bGWJIPekuoEHIHlQTlcl2Xdh8o2vWEtHtKK3F8OB4cmbuGqMAF869ok05Bi4EHi0AbGioueDGTmrNQU-Ij8y3pCzlIsRxFbxgXWburz61GX55u95Bgzs6l-cxNrISo4CNrQ4_xU");

        // TODO Check for null value when retrieving Key
        firebaseService.sendNotification(payload, user.getId());

        return Response.ok().build();
    }
}
