package com.robvangastel.assign.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robvangastel.assign.CodeGenerator;
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
 * TODO Improve tests to include parsing response
 */
public class SchoolControllerTest {

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
                .queryParam("email", "admin@mail.nl")
                .queryParam("password", "admin");
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
     * Case: Get posts for user with a start parameter of 0
     * and a size parameter of 10.
     *
     * Method:
     * Get all schools
     * @param start of the list
     * @param size of the list
     * @return a list of all the school objects
     */
    @Test
    public void getTest1() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUrl + "/schools/")
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
     * Get all schools
     * @param start of the list
     * @param size of the list
     * @return a list of all the school objects
     */
    @Test
    public void getTest2() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUrl + "/schools/")
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
     * Get a post by id
     * @param id of the post
     * @return A School object with matching id or statuscode 500
     * when no schools are found.
     */
    @Test
    public void getByIdTest1() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUrl + "/schools/" + 1);
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
     * Get a post by id
     * @param id of the post
     * @return A School object with matching id or statuscode 500
     * when no schools are found.
     */
    @Test
    public void getByIdTest2() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUrl + "/schools/" + "a");
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
     * getStudiesBySchool(@PathParam("id") long id,
     *                    @DefaultValue("0") @QueryParam("start") int start,
     *                    @DefaultValue("20") @QueryParam("size") int size)
     *
     * Case: Get by a valid id
     *
     * Method:
     * Get studies by school
     * @param id of the school
     * @param start of the list
     * @param size of the list
     * @return a list of Study objects or or statuscode 500
     * when no studies are found.
     */
    @Test
    public void getStudiesBySchoolTest1() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUrl + "/schools/" + 1 + "/studies");
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
     * getStudiesBySchool(@PathParam("id") long id,
     *                    @DefaultValue("0") @QueryParam("start") int start,
     *                    @DefaultValue("20") @QueryParam("size") int size)
     *
     * Case: Get by a invalid id
     *
     * Method:
     * Get studies by school
     * @param id of the school
     * @param start of the list
     * @param size of the list
     * @return a list of Study objects or or statuscode 500
     * when no studies are found.
     */
    @Test
    public void getStudiesBySchoolTest2() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUrl + "/schools/" + "a" + "/studies");
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
     * getUsersBySchool(@PathParam("id") long id,
     *                  @DefaultValue("0") @QueryParam("start") int start,
     *                  @DefaultValue("20") @QueryParam("size") int size)
     *
     * Case: Get by a valid id
     *
     * Method:
     * Get users by school
     * @param id of the school
     * @param start of the list
     * @param size of the list
     * @return A list of User objects or statuscode 500
     * when no users are found.
     */
    @Test
    public void getUsersBySchoolTest1() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUrl + "/schools/" + 1 + "/users");
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
     * getUsersBySchool(@PathParam("id") long id,
     *                  @DefaultValue("0") @QueryParam("start") int start,
     *                  @DefaultValue("20") @QueryParam("size") int size)
     *
     * Case: Get by a valid id
     *
     * Method:
     * Get users by school
     * @param id of the school
     * @param start of the list
     * @param size of the list
     * @return A list of User objects or statuscode 500
     * when no users are found.
     */
    @Test
    public void getUsersBySchoolTest2() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUrl + "/schools/" + "a" + "/users");
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
     * getUsersBySchoolAndStudy(@PathParam("id") long id,
     *                          @QueryParam("study") String study,
     *                          @DefaultValue("0") @QueryParam("start") int start,
     *                          @DefaultValue("20") @QueryParam("size") int size)
     *
     * Case: Get by a valid id and study
     *
     * Method:
     * get users by school and study
     * @param id of the school
     * @param study name of the study
     * @param start of the list
     * @param size of the list
     * @return A list of the User objects or statuscode 500
     * when no users are found.
     */

    /***
     * create(@QueryParam("name") String name)
     *
     * Case: Create school with valid name
     *
     * Method:
     * Create a School
     * @param name of the school
     * @return the created school
     * @throws Exception when a invalid name parameter is given.
     */
    @Test
    public void createTest1() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUrl + "/schools/")
                .queryParam("name", "Fontys " + new CodeGenerator().getCode(5));
        Response response = target.request()
                .header("Authorization", authorizationHeader)
                .post(Entity.json(jsonString));

        try {
            Assert.assertEquals(200, response.getStatus());
        } finally {
            response.close();
            client.close();
        }
    }

    /***
     * create(@QueryParam("name") String name)
     *
     * Case: Create school with duplicate name
     *
     * Method:
     * Create a School
     * @param name of the school
     * @return the created school
     * @throws Exception when a invalid name parameter is given.
     */
    @Test
    public void createTest2() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUrl + "/schools/")
                .queryParam("name", "Fontys Tilburg");
        Response response = target.request()
                .header("Authorization", authorizationHeader)
                .post(Entity.json(jsonString));

        try {
            Assert.assertEquals(500, response.getStatus());
        } finally {
            response.close();
            client.close();
        }
    }

    /***
     * addStudy(@PathParam("id") long id,
     *                     @QueryParam("name") String name)
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
