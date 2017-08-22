package com.robvangastel.assign.controllers;

import com.robvangastel.assign.domain.Post;
import com.robvangastel.assign.domain.User;
import com.robvangastel.assign.firebase.Body;
import com.robvangastel.assign.firebase.FirebaseService;
import com.robvangastel.assign.services.DashboardService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author Rob van Gastel
 */
@Stateless
@Path("/dashboard")
@Produces({MediaType.APPLICATION_JSON})
public class DashboardController {

    @Inject
    private DashboardService dashboardService;

    @GET
    @Path("/users")
    public Response getUsers(
            @DefaultValue("0") @QueryParam("start") int start,
            @DefaultValue("20") @QueryParam("size") int size) {
        List<User> users = dashboardService.findAllUsers(start, size);

        return Response.ok(users).build();
    }

    @GET
    @Path("/posts")
    public Response getPosts(
            @DefaultValue("0") @QueryParam("start") int start,
            @DefaultValue("20") @QueryParam("size") int size) {
//        List<Post> posts = dashboardService.findAllPosts(start, size);
//        return Response.ok(posts).build();

        FirebaseService fb = new FirebaseService();
        return Response.ok(fb.createNotificationkey(new Body())).build();
    }
}
