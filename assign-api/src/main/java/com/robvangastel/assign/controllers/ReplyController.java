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
@Path("/replies")
@Secured({Role.USER})
@Produces({MediaType.APPLICATION_JSON})
public class ReplyController {

    @Inject
    private ReplyService replyService;

    @Inject
    private UserService userService;

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
     * Set the boolean helped on a reply of the user.
     * @param id of the reply
     * @return A matching statuscode indicating success or failure
     * @throws Exception when a unauthorized User makes the request.
     */
    @PUT
    @Path("{id}")
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
