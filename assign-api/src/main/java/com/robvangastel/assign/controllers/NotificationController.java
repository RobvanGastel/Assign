package com.robvangastel.assign.controllers;

import com.robvangastel.assign.domain.Notification;
import com.robvangastel.assign.domain.Role;
import com.robvangastel.assign.domain.User;
import com.robvangastel.assign.security.Secured;
import com.robvangastel.assign.services.NotificationService;
import com.robvangastel.assign.services.UserService;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Rob van Gastel
 */
@Stateless // Request scoped for the Filters
@Path("/notifications")
@Secured({Role.USER})
@Produces({MediaType.APPLICATION_JSON})
public class NotificationController {

    private static final Logger LOG = Logger.getLogger(NotificationController.class.getSimpleName());

    @Inject
    private UserService userService;

    @Inject
    private NotificationService notificationService;

    @Context
    private SecurityContext securityContext;

    /***
     * Get all the notifications for the authenticated user
     * @param start of the list
     * @param size of the list
     * @return A list of the Notification objects or statuscode 404
     * when no notifications are found.
     */
    @GET
    public Response get(
            @DefaultValue("0") @QueryParam("start") int start,
            @DefaultValue("20") @QueryParam("size") int size) {

        User user = userService.findByEmail(securityContext.getUserPrincipal().getName());
        return Response.ok(notificationService.findAll(user, start, size)).build();
    }

    /***
     * Get a notification by id
     * @param id of the notification
     * @return A post object with matching id or a statuscode 404
     * when no notification is found.
     */
    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") long id) {
        Notification notification = notificationService.findById(id);
        if (notification == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok(notification).build();
    }

    /***
     * set Notifications to read by an id array
     * @param ids array of ids
     * @return a matching id or a statuscode 404
     * when no notification is found.
     */
    @PUT
    public Response readNotifications(@QueryParam("ids") String idsString) {

        User user = userService.findByEmail(securityContext.getUserPrincipal().getName());

        List<Long> idsList = new ArrayList<>();
        List<String> splittedArray = Arrays.asList(idsString.split(","));


        if (splittedArray == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        for(String i : splittedArray) {
            idsList.add(Long.parseLong(i));
        }

        notificationService.readNotifications(idsList);
        return Response.ok().build();
    }

    /***
     * set a single notification to read by id
     * @param id of the notification
     * @return a matching id or a statuscode 404
     * when no notification is found.
     */
    @PUT
    @Path("/{id}")
    public Response readNotification(@PathParam("id") long id) {

        notificationService.readNotification(id);
        return Response.ok().build();
    }

    /***
     * Delete notification by id
     * @param id of the notification
     * @return A statuscode indicating failure or success
     * @throws Exception
     */
    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") long id) throws Exception {
        User user = userService.findByEmail(securityContext.getUserPrincipal().getName());
        Notification notification = notificationService.findById(id);

        if (user.getId() == notification.getUser().getId()) {
            notificationService.delete(id);
            return Response.noContent().build();
        }

        return Response.status(Response.Status.BAD_REQUEST).build();
    }

}
