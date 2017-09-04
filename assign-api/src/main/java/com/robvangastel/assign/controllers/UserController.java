package com.robvangastel.assign.controllers;

import com.robvangastel.assign.domain.Post;
import com.robvangastel.assign.domain.Reply;
import com.robvangastel.assign.domain.Role;
import com.robvangastel.assign.domain.User;
import com.robvangastel.assign.security.Secured;
import com.robvangastel.assign.services.PostService;
import com.robvangastel.assign.services.ReplyService;
import com.robvangastel.assign.services.UserService;

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

@RequestScoped // Request scoped for the Filters
@Path("/users")
@Produces({MediaType.APPLICATION_JSON})
public class UserController {

    @Inject
    private UserService userService;

    @Inject
    private PostService postService;

    @Inject
    private ReplyService replyService;

    @Context
    private SecurityContext securityContext;

    /***
     * get all the users
     * @param start of the list
     * @param size of the list
     * @return A list of the User objects or statuscode 404
     * when no users are found.
     */
    @GET
    @Secured({Role.ADMIN})
    public Response get(
            @DefaultValue("0") @QueryParam("start") int start,
            @DefaultValue("20") @QueryParam("size") int size) {
        return Response.ok(userService.findAll(start, size)).build();
    }

    /***
     * Get user by id
     * @param id of the user
     * @return the User object with matching id or statuscode 404
     * when no user is found.
     */
    @GET
    @Path("/{id}")
    @Secured({Role.USER})
    public Response getById(@PathParam("id") long id) {
        User user = userService.findById(id);

        if (user == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return Response.ok(user).build();
    }

    /***
     * get the posts by User id
     * @param id of the user
     * @param start of the list
     * @param size of the list
     * @return A list of Post objects of the users or statuscode 404
     * when no user is found.
     */
    @GET
    @Path("/{id}/posts")
    @Secured({Role.USER})
    public Response getPostsByUser(@PathParam("id") long id,
                                   @DefaultValue("0") @QueryParam("start") int start,
                                   @DefaultValue("20") @QueryParam("size") int size) {
        List<Post> posts = postService.findByUser(id, start, size);
        return Response.ok(posts).build();
    }

    /***
     * Get all the replies of a user
     * @param id of the user
     * @param start of the list
     * @param size of the list
     * @return A list of all the replies of the User.
     */
    @GET
    @Path("/{id}/replies")
    public Response getRepliesByUser(@PathParam("id") long id,
                                     @DefaultValue("0") @QueryParam("start") int start,
                                     @DefaultValue("20") @QueryParam("size") int size) {
        List<Reply> replies = replyService.findByUser(id, start, size);
        return Response.ok(replies).build();
    }

    /***
     * get user by email
     * @param email of the user
     * @return the User object with matching email or statuscode 404
     * when no user is found.
     */
    @GET
    @Path("/email")
    @Secured({Role.USER})
    public Response getByEmail(@QueryParam("email") String email) {
        User user = userService.findByEmail(email);

        if (user == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return Response.ok(user).build();
    }

    /***
     * Register a user
     * @param email
     * @param password
     * @param name
     * @param code
     * @throws Exception when used or invalid information is given for
     * creating the user.
     */
    @POST
    public Response create(@QueryParam("email") String email,
                           @QueryParam("password") String password,
                           @QueryParam("name") String name,
                           @QueryParam("code") String code) throws Exception {
        userService.create(new User(email, password, name));
        return Response.ok().build();
    }

    /***
     * TODO Implement uniform update method
     *
     * Update the authenticated user
     * @param location
     * @param websiteURL
     * @param bio
     * @return A matching status code to indicate success or failure.
     * @throws Exception when used or invalid information is given for
     * updating the user.
     */
    @PUT
    @Secured({Role.USER})
    public Response update(@QueryParam("location") String location,
                           @QueryParam("websiteURL") String websiteURL,
                           @QueryParam("bio") String bio) throws Exception {

        User user = userService.findByEmail(securityContext.getUserPrincipal().getName());
        if (user == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.noContent().build();
    }

    /***
     * Delete a user by id as administrator
     * @param id of the user
     * @return A matching status code to indicate success or failure.
     * @throws Exception when the user object failed to get deleted.
     */
    @DELETE
    @Path("/{id}")
    @Secured({Role.ADMIN})
    public Response delete(@PathParam("id") long id) throws Exception {
        userService.delete(id);
        return Response.noContent().build();
    }
}
