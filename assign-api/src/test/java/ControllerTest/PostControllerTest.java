package ControllerTest;

import com.robvangastel.assign.security.IdToken;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 * @author Rob van Gastel
 *
 * TODO create a non-static server
 * TODO Improve tests to include parsing response
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

    private static String baseUrl = "http://localhost:9080/assign/api";
    private static String authorizationHeader = "Bearer ";

    /***
     * The beforeClass handles the authentication token to make
     * requests to the PostController.
     *
     * TODO add valid implementation to retrieve auth header
     * TODO Add valid implementation to create a post request
     */
    @BeforeClass
    public static void beforeClass() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUrl + "/auth")
                .queryParam("email", "admin@mail.nl")
                .queryParam("password", "admin");
        Response response = target.request().get();

        try
        {
            IdToken token = (IdToken) response.getEntity();
            authorizationHeader += token.getToken();
        }
        finally
        {
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
        WebTarget target = client.target(baseUrl + "/posts/")
                .queryParam("start", 0)
                .queryParam("size", 10);
        Response response = target.request()
                .header("Authorization", authorizationHeader)
                .buildPost();

        try
        {
            Assert.assertEquals(200, response.getStatus());
        }
        finally
        {
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
    public void getTest2() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUrl + "/posts/")
                .queryParam("start", 0)
                .queryParam("size", 10);
        Response response = target.request()
                .header("Authorization", authorizationHeader)
                .buildPost();

        try
        {
            Assert.assertEquals(200, response.getStatus());
        }
        finally
        {
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
        WebTarget target = client.target(baseUrl + "/posts/" + 1);
        Response response = target.request()
                .header("Authorization", authorizationHeader)
                .get();

        try
        {
            Assert.assertEquals(200, response.getStatus());
        }
        finally
        {
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

        try
        {
            Assert.assertEquals(404, response.getStatus());
        }
        finally
        {
            response.close();
            client.close();
        }
    }

    /***
     * getByQuery(@QueryParam("query") String query,
                  @DefaultValue("0") @QueryParam("start") int start,
                  @DefaultValue("20") @QueryParam("size") int size)
     *
     * Case:
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
        WebTarget target = client.target(baseUrl + "/posts/");
        Response response = target.request()
                .header("Authorization", authorizationHeader)
                .get();

        try
        {
            Assert.assertEquals(404, response.getStatus());
        }
        finally
        {
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
        WebTarget target = client.target(baseUrl + "/posts/");
        Response response = target.request()
                .header("Authorization", authorizationHeader)
                .get();

        try
        {
            Assert.assertEquals(404, response.getStatus());
        }
        finally
        {
            response.close();
            client.close();
        }
    }
}
