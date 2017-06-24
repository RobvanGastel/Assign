package com.robvangastel.assign.controllers;

import com.robvangastel.assign.domain.Post;
import com.robvangastel.assign.domain.Role;
import com.robvangastel.assign.domain.User;
import com.robvangastel.assign.security.Secured;
import com.robvangastel.assign.security.UserPrincipal;
import com.robvangastel.assign.services.PostService;
import com.robvangastel.assign.services.UserService;
import io.swagger.annotations.Api;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.util.List;

/**
 * Created by Rob on 23-4-2017.
 */

@Secured({Role.USER})
@RequestScoped
@Path("/posts")
@Api(tags = {"posts"}, value = "/posts", description = "Operations about posts")
@Produces({MediaType.APPLICATION_JSON})
public class PostController {

    @Inject
    private PostService postService;

    @Inject
    private UserService userService;

    @Context
    private SecurityContext context;

    @GET
    public List<Post> get() {
        return postService.findAll();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") long id) {
        Post post = postService.findById(id);
        if(post == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok(post).build();
    }

    @GET
    @Path("/email")
    public Response getByEmail(@QueryParam("email") String email) {
        Post post = postService.findByEmail(email);
        if(post == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok(post).build();
    }

    @GET
    @Path("/description")
    public Response getByDescription(@QueryParam("description") String description) {
        List<Post> posts = postService.findByDescription(description);
        if(posts == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok(posts).build();
    }

    @POST
    @Secured({Role.USER})
    public Response create(@QueryParam("title") String title,
                           @QueryParam("description") String description) throws Exception {
        Principal p = context.getUserPrincipal();
        UserPrincipal up = (UserPrincipal) p;

        User user = userService.findByEmail(p.getName());
        Post post = postService.create(new Post(user, title, description));
        if(post == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return Response.ok(post).build();
    }

    @DELETE
    @Path("/{id}")
    @Secured({Role.USER})
    public Response delete(@PathParam("id") long id) throws Exception {
        User user = userService.findByEmail(context.getUserPrincipal().getName());
        Post post = postService.findById(id);

        if(user.getId() == post.getUser().getId()) {
            postService.delete(id);
            return Response.noContent().build();
        }

        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
