package com.robvangastel.assign.controllers;

import com.robvangastel.assign.domain.Post;
import com.robvangastel.assign.domain.User;
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
    public Response getPosts(
            @DefaultValue("0") @QueryParam("start") int start,
            @DefaultValue("20") @QueryParam("size") int size) {
        List<User> users = dashboardService.findAllUsers(start, size);

        return Response.ok(users)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .header("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, Content-Length, Authentication, Authorization")
                .build();
    }
}
