package com.robvangastel.assign.controllers;

import com.robvangastel.assign.domain.Reply;
import com.robvangastel.assign.domain.Role;
import com.robvangastel.assign.domain.User;
import com.robvangastel.assign.firebase.domain.*;
import com.robvangastel.assign.firebase.FirebaseService;
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

    @POST
    public Response createNotificationKey(@QueryParam("id") String id) throws Exception {

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
    public Response removeRegistrationId(@QueryParam("id") String id) throws Exception {

        User user = userService.findByEmail(securityContext.getUserPrincipal().getName());

        Body body = new Body();
        body.setNotification_key_name(user.getFirebase().getNotificationKeyName());
        body.setNotification_key(user.getFirebase().getNotificationKey());
        body.getRegistration_ids().add(id);

        firebaseService.removeRegistrationId(body, user.getId());
        return Response.ok().build();
    }

    @GET
    public Response test() throws Exception {

        Reply entity = replyService.findById(30);

        String title = entity.getUser().getName() + " wants to help you out!";
        String body = entity.getUser().getName() + " offers to help you out with " + entity.getPost().getTitle();

        Payload payload = new Payload(
                new Notification(title, body),
                new Data(true),
                "APA91bGWJIPekuoEHIHlQTlcl2Xdh8o2vWEtHtKK3F8OB4cmbuGqMAF869ok05Bi4EHi0AbGioueDGTmrNQU-Ij8y3pCzlIsRxFbxgXWburz61GX55u95Bgzs6l-cxNrISo4CNrQ4_xU");

        // TODO Check for null value when retrieving Key
        // entity.getPost().getUser().getFirebase().getNotificationKey()
        firebaseService.sendNotification(payload, 10L);

        return Response.ok().build();

    }
}
