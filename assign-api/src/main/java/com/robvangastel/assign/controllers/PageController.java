package com.robvangastel.assign.controllers;

import com.robvangastel.assign.domain.Post;
import com.robvangastel.assign.services.PostService;
import io.swagger.annotations.Api;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Rob van Gastel
 */

@Stateless
@Path("/page")
@Api(tags = {"page"}, value = "/page", description = "Operations about posts")
@Produces({MediaType.APPLICATION_JSON})
public class PageController {

    @Inject
    private PostService postService;

    /***
     * Get a post by url
     * @param url of the post
     * @return A post object with matching id or a statuscode 404
     * when no post is found.
     */
    @GET
    @Path("/post")
    public Response getByUrl(@QueryParam("url") String url) {
        Post post = postService.findByUrl(url);

        if (post == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return Response.ok(post)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .header("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, Content-Length, Authentication, Authorization")
                .build();
    }
}
