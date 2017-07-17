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

@RequestScoped
@Path("/replies")
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
     * Get reply by id
     * @param id of the reply
     * @return the Reply object with matching id
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
     * @param id of the user
     * @param start of the list
     * @param size of the list
     * @return A list of all the replies of the User.
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
     * @param id of the Post
     * @param start of the list
     * @param size of the list
     * @return A list of all the replies of the Post.
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
     * Create a Reply
     * @param id of the post
     * @throws Exception when invalid information for the reply is given.
     */
    @POST
    @Path("/posts/{id}")
    @Secured({Role.USER})
    public Response create(@PathParam("id") long id) throws Exception {
        User user = userService.findByEmail(securityContext.getUserPrincipal().getName());
        Post post = postService.findById(id);

        if (user.getId() != post.getUser().getId() && post != null) {
            // Check if the user creating reply isnt replying to his own post
            replyService.create(new Reply(user, post));
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        return Response.ok().build();
    }


    /***
     * Set the boolean helped on a reply of the user.
     * @param id of the reply
     * @return A matching statuscode indicating success or failure
     * @throws Exception when a unauthorized User makes the request.
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
