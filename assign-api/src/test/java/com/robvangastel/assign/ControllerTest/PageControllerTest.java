package com.robvangastel.assign.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robvangastel.assign.TestConfig;
import com.robvangastel.assign.domain.Post;
import com.robvangastel.assign.security.IdToken;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author Rob van Gastel
 */
public class PageControllerTest {

    // EXAMPLE DESCRIPTION
    /***
     * TEST CASE METHOD
     *
     * Case: STATE TEST CASE
     *
     * Method:
     * STATE METHOD DESCRIPTION
     */

    private static String baseUrl = new TestConfig().getBaseUrl();
    private static String jsonString = "{}";
    private static String authorizationHeader = "Bearer ";
    private static List<Post> posts;

    /***
     * The beforeClass handles the authentication token to make
     * requests to the PostController for the posts.
     */
    @BeforeClass
    public static void beforeClass() {
        Client client = ClientBuilder.newClient();
        ObjectMapper mapper = new ObjectMapper();

        WebTarget target = client.target(baseUrl + "/auth")
                .queryParam("email", "max@mail.nl")
                .queryParam("password", "max");
        Response response = target.request().post(Entity.json(jsonString));

        try {

            String tokenString = response.readEntity(String.class);
            IdToken token = mapper.readValue(tokenString, IdToken.class);
            authorizationHeader += token.getToken();

            client = ClientBuilder.newClient();
            target = client.target(baseUrl + "/posts")
                    .queryParam("start", 0)
                    .queryParam("size", 10);
            response = target.request()
                    .header("Authorization", authorizationHeader)
                    .get();

            posts = response.readEntity(new GenericType<List<Post>>() {});


        } catch (Exception e) {

        } finally {
            response.close();
            client.close();
        }
    }

    /***
     * public Response getByUrl(@QueryParam("url") String url)
     *
     * Case: Get post by url.
     *
     * Method:
     * Get a post by url
     * @param url of the post
     * @return A post object with matching id or a statuscode 404
     * when no post is found.
     */
    @Test
    public void getByUrlTest1() {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUrl + "/page/post")
                .queryParam("url", posts.get(0).getUrl());
        Response response = target.request().get();

        try {
            Assert.assertEquals(200, response.getStatus());
        } finally {
            response.close();
            client.close();
        }
    }

    /***
     * public Response getByUrl(@QueryParam("url") String url)
     *
     * Case: Get post by not existing url.
     *
     * Method:
     * Get a post by url
     * @param url of the post
     * @return A post object with matching id or a statuscode 404
     * when no post is found.
     */
    @Test
    public void getByUrlTest2() {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUrl + "/page/post")
                .queryParam("url", posts.get(0).getUrl() + posts.get(1).getUrl());
        Response response = target.request().get();

        try {
            Assert.assertEquals(500, response.getStatus());
        } finally {
            response.close();
            client.close();
        }
    }

    /***
     * public Response getByUrl(@QueryParam("url") String url)
     *
     * Case: Check if no private data is exposed.
     *
     * Method:
     * Get a post by url
     * @param url of the post
     * @return A post object with matching id or a statuscode 404
     * when no post is found.
     */
    @Test
    public void getByUrlTest3() {

        Post post;

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUrl + "/page/post")
                .queryParam("url", posts.get(0).getUrl());
        Response response = target.request().get();

        try {

            post = response.readEntity(Post.class);
            Assert.assertEquals(200, response.getStatus());
        } finally {
            response.close();
            client.close();
        }

        Assert.assertNull(post.getUser());
    }
}
