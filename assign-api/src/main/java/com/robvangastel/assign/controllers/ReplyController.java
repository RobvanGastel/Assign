package com.robvangastel.assign.controllers;

import com.robvangastel.assign.domain.Post;
import com.robvangastel.assign.domain.Reply;
import com.robvangastel.assign.domain.Role;
import com.robvangastel.assign.domain.User;
import com.robvangastel.assign.security.Secured;
import com.robvangastel.assign.services.PostService;
import com.robvangastel.assign.services.ReplyService;
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
@Path("/replies")
@Api(tags = {"replies"}, value = "/replies", description = "Operations about replies")
@Produces({MediaType.APPLICATION_JSON})
public class ReplyController {

    @Inject
    private ReplyService replyService;

    @Inject
    private UserService userService;

    @Inject
    private PostService postService;

    @Context
    private SecurityContext securityContext;

    /***
     * Get user by id
     * @param id of the user
     * @return the User object with matching id
     */
    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") long id) {
        Reply reply = replyService.findById(id);
        if (reply == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok(reply).build();
    }

    /***
     * Get all the replies of a user
     * @param id
     * @param start
     * @param size
     * @return
     */
    @GET
    @Path("/users/{id}")
    public Response getByUser(@PathParam("id") long id,
                              @DefaultValue("0") @QueryParam("start") int start,
                              @DefaultValue("20") @QueryParam("size") int size) {
        List<Reply> replies = replyService.findByUser(id, start, size);
        return Response.ok(replies).build();
    }

    /***
     * Get all the replies of a post
     * @param id
     * @param start
     * @param size
     * @return
     */
    @GET
    @Path("/posts/{id}")
    public Response getByPost(@PathParam("id") long id,
                              @DefaultValue("0") @QueryParam("start") int start,
                              @DefaultValue("20") @QueryParam("size") int size) {
        List<Reply> replies = replyService.findByPost(id, start, size);
        return Response.ok(replies).build();
    }

    /***
     * Create a new Reply
     * @param id of the post
     * @return the new reply
     * @throws Exception
     */
    @POST
    @Path("/posts/{id}")
    @Secured({Role.USER})
    public Response create(@PathParam("id") long id) throws Exception {
        User user = userService.findByEmail(securityContext.getUserPrincipal().getName());
        Post post = postService.findById(id);
        Reply reply;

        if (user.getId() != post.getUser().getId() && post != null) {
            // Check if the user creating reply isnt replying to his own post
            reply = replyService.create(new Reply(user, post));
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        return Response.ok(reply).build();
    }


    /***
     * Set helped out on a User
     * @param id of the reply
     * @return A matching response code
     * @throws Exception
     */
    @PUT
    @Path("{id}")
    @Secured({Role.USER})
    public Response setHelped(@PathParam("id") long id) throws Exception {
        User user = userService.findByEmail(securityContext.getUserPrincipal().getName());
        Reply reply = replyService.findById(id);

        // The user can only set replies of his own post to helped
        if (user.getId() == reply.getPost().getUser().getId()) {
            replyService.setHelped(reply, true);
            return Response.noContent().build();
        }

        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
