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
 * @author Rob van Gastel
 */

@RequestScoped
@Path("/posts")
@Secured({Role.USER})
@Api(tags = {"posts"}, value = "/posts", description = "Operations about posts")
@Produces({MediaType.APPLICATION_JSON})
public class PostController {

    // TODO Add get posts for users

    @Inject
    private PostService postService;

    @Inject
    private UserService userService;

    @Context
    private SecurityContext securityContext;

    /***
     * Get all the posts for the authenticated user
     * @param start of the list
     * @param size of the list
     * @return A list of the Post objects or statuscode 404
     * when no posts are found.
     */
    @GET
    public List<Post> get(
            @DefaultValue("0") @QueryParam("start") int start,
            @DefaultValue("20") @QueryParam("size") int size) {

        User user = userService.findByEmail(securityContext.getUserPrincipal().getName());
        return postService.findAll(user, start, size);
    }

    /***
     * Get a post by id
     * @param id of the post
     * @return A post object with matching id or a statuscode 404
     * when no post is found.
     */
    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") long id) {
        Post post = postService.findById(id);
        if (post == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok(post).build();
    }

    /***
     * Get post(s) by query
     * @param query string to search the posts by
     * @param start of the list
     * @param size lof the list
     * @return A list of posts matching the query,
     * the fields being searched are description, title, email
     * and name.
     */
    @GET
    @Path("/query")
    public Response getByQuery(@QueryParam("query") String query,
                               @DefaultValue("0") @QueryParam("start") int start,
                               @DefaultValue("20") @QueryParam("size") int size) {

        User user = userService.findByEmail(securityContext.getUserPrincipal().getName());
        List<Post> posts = postService.findByQuery(user, query, start, size);

        if (posts == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok(posts).build();
    }

    /***
     * Create a post for the authenticated user
     * @param title
     * @param description
     * @return The created post
     * @throws Exception when invalid parameters are given for the
     * post.
     */
    @POST
    public Response create(@QueryParam("title") String title,
                           @QueryParam("description") String description) throws Exception {

        User user = userService.findByEmail(securityContext.getUserPrincipal().getName());
        Post post = postService.create(new Post(user, title, description));

        if (post == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return Response.ok(post).build();
    }

    /***
     * Set a post of the authenticated user to done
     * @param id of the post
     * @return a statuscode indicating success or failure.
     * @throws Exception when an invalid user makes the request.
     */
    @PUT
    @Path("/{id}")
    public Response setDone(@PathParam("id") long id) throws Exception {
        User user = userService.findByEmail(securityContext.getUserPrincipal().getName());
        Post post = postService.findById(id);

        if (user.getId() == post.getUser().getId()) {
            postService.setDone(post, true);
            return Response.noContent().build();
        }

        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    /***
     * Delete a post if the user created the post
     * @param id of the post
     * @return A response with statuscode indicating success or failure
     * @throws Exception when the post object failed to get deleted.
     */
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") long id) throws Exception {
        User user = userService.findByEmail(securityContext.getUserPrincipal().getName());
        Post post = postService.findById(id);

        if (user.getId() == post.getUser().getId()) {
            postService.delete(id);
            return Response.noContent().build();
        }

        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
