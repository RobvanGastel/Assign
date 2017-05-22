package com.robvangastel.assign.controllers;

import com.robvangastel.assign.domain.Post;
import com.robvangastel.assign.domain.User;
import com.robvangastel.assign.services.PostService;
import com.robvangastel.assign.services.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Rob on 23-4-2017.
 */

@Stateless
@Path("/posts")
@Produces({MediaType.APPLICATION_JSON})
public class PostController {

    @Inject
    private PostService postService;

    @Inject
    private UserService userService;

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
    public Response create(@QueryParam("id") int id,
                           @QueryParam("title") String title,
                           @QueryParam("description") String description) throws Exception {
        //TODO add authentication
        User user = userService.findById(id);
        Post post = postService.create(new Post(user, title, description));
        if(post == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return Response.ok(post).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") long id) throws Exception {
        postService.delete(id);
        return Response.noContent().build();
    }
}
