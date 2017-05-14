package com.robvangastel.assign.controllers;

import com.robvangastel.assign.domain.Post;
import com.robvangastel.assign.domain.User;
import com.robvangastel.assign.services.PostService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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

    @GET
    public List<Post> get() {
        return postService.findAll();
    }
}
