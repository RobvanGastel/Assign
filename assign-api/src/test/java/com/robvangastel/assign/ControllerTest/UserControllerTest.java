package com.robvangastel.assign.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robvangastel.assign.TestConfig;
import com.robvangastel.assign.security.IdToken;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 * @author Rob van Gastel
 *         <p>
 *         TODO create a non-static server
 *         TODO Improve tests to include parsing response
 */
public class UserControllerTest {

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
     * Case: Get all existing users with a start parameter of 0
     * and a size parameter of 10.
     *
     * Method:
     * get all the users
     * @param start of the list
     * @param size of the list
     * @return A list of the User objects or statuscode 500
     * when no users are found.
     */
    @Test
    public void getTest1() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUrl + "/users/")
                .queryParam("start", 0)
                .queryParam("size", 10);
        Response response = target.request()
                .header("Authorization", authorizationHeader)
                .get();

        try {
            Assert.assertEquals(200, response.getStatus());
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
     * get all the users
     * @param start of the list
     * @param size of the list
     * @return A list of the User objects or statuscode 500
     * when no users are found.
     */
    @Test
    public void getTest2() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUrl + "/users/")
                .queryParam("start", "a")
                .queryParam("size", 10);
        Response response = target.request()
                .header("Authorization", authorizationHeader)
                .get();

        try {
            Assert.assertEquals(500, response.getStatus());
        } finally {
            response.close();
            client.close();
        }
    }

    /***
     * getById(@PathParam("id") long id)
     *
     * Case: Get by a valid id
     *
     * Method:
     * Get user by id
     * @param id of the user
     * @return the User object with matching id or statuscode 500
     * when no user is found.
     */
    @Test
    public void getByIdTest1() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUrl + "/users/" + 6);
        Response response = target.request()
                .header("Authorization", authorizationHeader)
                .get();

        try {
            Assert.assertEquals(200, response.getStatus());
        } finally {
            response.close();
            client.close();
        }
    }

    /***
     * getById(@PathParam("id") long id)
     *
     * Case: Get by a invalid id
     *
     * Method:
     * Get user by id
     * @param id of the user
     * @return the User object with matching id or statuscode 500
     * when no user is found.
     */
    @Test
    public void getByIdTest2() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUrl + "/users/" + -1);
        Response response = target.request()
                .header("Authorization", authorizationHeader)
                .get();

        try {
            Assert.assertEquals(500, response.getStatus());
        } finally {
            response.close();
            client.close();
        }
    }

    /***
     * getById(@PathParam("id") long id)
     *
     * Case: Get by a wrong datatype for id
     *
     * Method:
     * Get user by id
     * @param id of the user
     * @return the User object with matching id or statuscode 500
     * when no user is found.
     */
    @Test
    public void getByIdTest3() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUrl + "/users/" + "a");
        Response response = target.request()
                .header("Authorization", authorizationHeader)
                .get();

        try {
            Assert.assertEquals(500, response.getStatus());
        } finally {
            response.close();
            client.close();
        }
    }

    /***
     * getPostsByUser(@PathParam("id") long id,
     *                  @DefaultValue("0") @QueryParam("start") int start,
     * 				  @DefaultValue("20") @QueryParam("size") int size)
     *
     * Case: Get by a existing id
     *
     * Method:
     * get the posts by User id
     * @param id of the user
     * @param start of the list
     * @param size of the list
     * @return A list of Post objects of the users or statuscode 500
     * when no user is found.
     */
    @Test
    public void getPostsByUserTest1() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUrl + "/users/" + 6 + "/posts");
        Response response = target.request()
                .header("Authorization", authorizationHeader)
                .get();

        try {
            Assert.assertEquals(200, response.getStatus());
        } finally {
            response.close();
            client.close();
        }
    }

    /***
     * getPostsByUser(@PathParam("id") long id,
     *                  @DefaultValue("0") @QueryParam("start") int start,
     * 				  @DefaultValue("20") @QueryParam("size") int size)
     *
     * Case: Get by a non existing id
     *
     * Method:
     * get the posts by User id
     * @param id of the user
     * @param start of the list
     * @param size of the list
     * @return A list of Post objects of the users or statuscode 500
     * when no user is found.
     */
    @Test
    public void getPostsByUserTest2() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUrl + "/users/" + "a" + "/posts");
        Response response = target.request()
                .header("Authorization", authorizationHeader)
                .get();

        try {
            Assert.assertEquals(500, response.getStatus());
        } finally {
            response.close();
            client.close();
        }
    }

    /***
     * getByEmail(@QueryParam("email") String email)
     *
     * Case: Get by a existing email
     *
     * Method:
     * get user by email
     * @param email of the user
     * @return the User object with matching email or statuscode 500
     * when no user is found.
     */
    @Test
    public void getByEmailTest1() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUrl + "/users/email")
                .queryParam("email", "admin@mail.nl");
        Response response = target.request()
                .header("Authorization", authorizationHeader)
                .get();

        try {
            Assert.assertEquals(200, response.getStatus());
        } finally {
            response.close();
            client.close();
        }
    }

    /***
     * getByEmail(@QueryParam("email") String email)
     *
     * Case: Get by a non existing email
     *
     * Method:
     * get user by email
     * @param email of the user
     * @return the User object with matching email or statuscode 500
     * when no user is found.
     */
    @Test
    public void getByEmailTest2() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUrl + "/users/email")
                .queryParam("email", "bestaatniet@mail.nl");
        Response response = target.request()
                .header("Authorization", authorizationHeader)
                .get();

        try {
            Assert.assertEquals(500, response.getStatus());
        } finally {
            response.close();
            client.close();
        }
    }

    /***
     * create(@QueryParam("email") String email,
     *                      @QueryParam("password") String password,
     * 					  @QueryParam("name") String name,
     * 					  @QueryParam("code") String code)
     *
     * Case:
     *
     * Method:
     *
     */

    /***
     * update(@QueryParam("location") String location,
     *          @QueryParam("websiteURL") String websiteURL,
     * 		  @QueryParam("bio") String bio)
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
