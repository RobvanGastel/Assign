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
public class PostControllerTest {

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
    private static List<Post> posts; // Retrieved post to test endpoints with

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
     * get(@DefaultValue("0") @QueryParam("start") int start,
     *     @DefaultValue("20") @QueryParam("size") int size)
     *
     * Case: Get posts for user with a start parameter of 0
     * and a size parameter of 10.
     *
     * Method:
     * Get all the posts for the authenticated user
     * @param start of the list
     * @param size of the list
     * @return A list of the Post objects or statuscode 404
     * when no posts are found.
     */
    @Test
    public void getTest1() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUrl + "/posts")
                .queryParam("start", 0)
                .queryParam("size", 10);
        Response response = target.request()
                .header("Authorization", authorizationHeader)
                .get();

        try {
            // Check for expected response code
            Assert.assertEquals(200, response.getStatus());

            // Check for expected response body
            Assert.assertNotNull(response.readEntity(new GenericType<List<Post>>() {}));

        } finally {
            response.close();
            client.close();
        }
    }

    /***
     * get(@DefaultValue("0") @QueryParam("start") int start,
     *     @DefaultValue("20") @QueryParam("size") int size)
     *
     * Case: Get all existing users with start as a invalid
     * parameter and a size of 10.
     *
     * Method:
     * Get all the posts for the authenticated user
     * @param start of the list
     * @param size of the list
     * @return A list of the Post objects or statuscode 404
     * when no posts are found.
     */
    @Test
    public void getTest2() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUrl + "/posts")
                .queryParam("start", "a")
                .queryParam("size", 10);
        Response response = target.request()
                .header("Authorization", authorizationHeader)
                .get();

        try {
            // Check for expected response code
            Assert.assertEquals(500, response.getStatus());
        } finally {
            response.close();
            client.close();
        }
    }

    /***
     * getById(@PathParam("id") long id)
     *
     * Case: Get post by a valid id.
     *
     * Method:
     * Get a post by id
     * @param id of the post
     * @return A post object with matching id or a statuscode 404
     * when no post is found.
     */
    @Test
    public void getByIdTest1() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUrl + "/posts/" + posts.get(0).getId());
        Response response = target.request()
                .header("Authorization", authorizationHeader)
                .get();

        try {
            // Check for expected response code
            Assert.assertEquals(200, response.getStatus());

            // Check for expected response body
            Assert.assertNotNull(response.readEntity(new GenericType<Post>() {}));
        } finally {
            response.close();
            client.close();
        }
    }

    /***
     * getById(@PathParam("id") long id)
     *
     * Case: Get post by an invalid id.
     *
     * Method:
     * Get a post by id
     * @param id of the post
     * @return A post object with matching id or a statuscode 404
     * when no post is found.
     */
    @Test
    public void getByIdTest2() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUrl + "/posts/" + "a");
        Response response = target.request()
                .header("Authorization", authorizationHeader)
                .get();

        try {
            // Check for expected response code
            Assert.assertEquals(500, response.getStatus());

        } finally {
            response.close();
            client.close();
        }
    }

    /***
     * getByQuery(@QueryParam("query") String query,
     * @DefaultValue("0") @QueryParam("start") int start,
     * @DefaultValue("20") @QueryParam("size") int size)
     *
     * Case: Get posts by a valid query.
     *
     * Method:
     * Get post(s) by query
     * @param query string to search the posts by
     * @param start of the list
     * @param size lof the list
     * @return A list of posts matching the query,
     * the fields being searched are description, title, email
     * and name.
     */
    @Test
    public void getByQueryTest1() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUrl + "/posts")
                .queryParam("query", posts.get(0).getDescription());
        Response response = target.request()
                .header("Authorization", authorizationHeader)
                .get();

        try {
            // Check for expected response code
            Assert.assertEquals(200, response.getStatus());

            // Check for expected response body
            Assert.assertNotNull(response.readEntity(new GenericType<List<Post>>() {}));
        } finally {
            response.close();
            client.close();
        }
    }

    /***
     * getByQuery(@QueryParam("query") String query,
     * @DefaultValue("0") @QueryParam("start") int start,
     * @DefaultValue("20") @QueryParam("size") int size)
     *
     * Case: Get posts by an invalid query
     *
     * Method:
     * Get post(s) by query
     * @param query string to search the posts by
     * @param start of the list
     * @param size lof the list
     * @return A list of posts matching the query,
     * the fields being searched are description, title, email
     * and name.
     */
    @Test
    public void getByQueryTest2() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUrl + "/posts/query")
                .queryParam("query", "nopostscontainsthisstring");
        Response response = target.request()
                .header("Authorization", authorizationHeader)
                .get();

        try {
            // Check for expected response code
            Assert.assertEquals(200, response.getStatus());
        } finally {
            response.close();
            client.close();
        }
    }

    /***
     * create(@QueryParam("title") String title,
     *        @QueryParam("description") String description)
     *
     * Case: Create a post with valid parameters.
     *
     * Method:
     * Create a post for the authenticated user
     * @param title
     * @param description
     * @return The created post
     * @throws Exception when invalid parameters are given for the
     * post.
     */
    @Test
    public void createTest1() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUrl + "/posts/")
                .queryParam("title", "Werkt het aanmaken van een post?")
                .queryParam("description", "Lorem ipsum blaa blaa en meer tekst");
        Response response = target.request()
                .header("Authorization", authorizationHeader)
                .post(Entity.json(jsonString));

        try {
            // Check for expected response code
            Assert.assertEquals(200, response.getStatus());
        } finally {
            response.close();
            client.close();
        }
    }

    /***
     * create(@QueryParam("title") String title,
     *        @QueryParam("description") String description)
     *
     * Case: Create a post with invalid parameters.
     *
     * Method:
     * Create a post for the authenticated user
     * @param title
     * @param description
     * @return The created post
     * @throws Exception when invalid parameters are given for the
     * post.
     */
    @Test
    public void createTest2() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUrl + "/posts/")
                .queryParam("title", "Werkt het aanmaken van een post zonder description?");
        Response response = target.request()
                .header("Authorization", authorizationHeader)
                .post(Entity.json(jsonString));

        try {
            // Check for expected response code
            Assert.assertEquals(500, response.getStatus());
        } finally {
            response.close();
            client.close();
        }
    }

    /***
     * setDone(@PathParam("id") long id)
     *
     * Case:
     *
     * Method:
     *
     */

    /***
     * delete(@PathParam("id") long id)
     *
     * Case:
     *
     * Method:
     *
     */
}
