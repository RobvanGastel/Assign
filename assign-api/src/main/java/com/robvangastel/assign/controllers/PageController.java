package com.robvangastel.assign.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.robvangastel.assign.domain.Post;
import com.robvangastel.assign.domain.User;
import com.robvangastel.assign.services.PostService;
import lombok.Data;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Rob van Gastel
 */

@Stateless
@Path("/page")
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

        PostWithoutSocial postWithoutSocial = new PostWithoutSocial(post.getId(), post.getUrl(),
                post.getTitle(), post.getDescription(), post.getDateCreated(), post.getTags(),
                post.getUser().getName(), post.isDone(), post.getUser().getProfileImage());

        return Response.ok(postWithoutSocial).build();
    }

    @Data
    public class PostWithoutSocial {

        private Long id;
        private String url;
        private String title;
        private String description;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        private Timestamp dateCreated;

        private List<String> tags;
        private String name;
        private Boolean done;
        private String profileImage;

        public PostWithoutSocial(Long id, String url, String title, String description,
                                 Timestamp dateCreated, List<String> tags, String name,
                                 Boolean done, String profileImage) {

            this.id = id;
            this.url = url;
            this.title = title;
            this.description = description;
            this.dateCreated = dateCreated;
            this.tags = tags;
            this.name = name;
            this.profileImage = profileImage;
            this.done = done;
        }
    }
}
