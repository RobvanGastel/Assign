package com.robvangastel.assign.controllers;

import com.robvangastel.assign.domain.Post;
import com.robvangastel.assign.domain.Role;
import com.robvangastel.assign.domain.User;
import com.robvangastel.assign.security.Secured;
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
import java.util.List;

/**
 *
 * @author Rob van Gastel
 */

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
    private SecurityContext securityContext;

    /***
     * Get all the posts
     * @return An array with the posts
     */
    @GET
    public List<Post> get() {
        return postService.findAll();
    }

    /***
     * Get a post by id
     * @param id of the post
     * @return A post object with matching id
     */
    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") long id) {
        Post post = postService.findById(id);
        if(post == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok(post).build();
    }

    /***
     * Get post(s) by email
     * @param email of the user
     * @return An array of posts matching the email
     */
    @GET
    @Path("/email")
    public Response getByEmail(@QueryParam("email") String email) {
        List<Post> posts = postService.findByEmail(email);
        if(posts == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok(posts).build();
    }

    /***
     * Get post(s) by a description query
     * @param description
     * @return An array of posts matching the description
     */
    @GET
    @Path("/description")
    public Response getByDescription(@QueryParam("description") String description) {
        List<Post> posts = postService.findByDescription(description);
        if(posts == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok(posts).build();
    }

    /***
     * Create a post for the authenticated user
     * @param title
     * @param description
     * @return The created post
     * @throws Exception
     */
    @POST
    @Secured({Role.USER})
    public Response create(@QueryParam("title") String title,
                           @QueryParam("description") String description) throws Exception {

        User user = userService.findByEmail(securityContext.getUserPrincipal().getName());

        Post post = postService.create(new Post(user, title, description));
        if(post == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return Response.ok(post).build();
    }

    /***
     * Delete a post if the user created the post
     * @param id
     * @return A response with statuscode indicating success or failure
     * @throws Exception
     */
    @DELETE
    @Path("/{id}")
    @Secured({Role.USER})
    public Response delete(@PathParam("id") long id) throws Exception {
        User user = userService.findByEmail(securityContext.getUserPrincipal().getName());
        Post post = postService.findById(id);

        if(user.getId() == post.getUser().getId()) {
            postService.delete(id);
            return Response.noContent().build();
        }

        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
