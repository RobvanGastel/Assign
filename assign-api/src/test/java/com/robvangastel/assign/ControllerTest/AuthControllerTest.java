package com.robvangastel.assign.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robvangastel.assign.TestConfig;
import com.robvangastel.assign.security.IdToken;
import org.junit.Assert;
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
 */
public class AuthControllerTest {

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

    /***
     * authenticate(@QueryParam("email") String username,
     *              @QueryParam("password") String password)
     *
     * Case: Create a valid authentication
     *
     * Method:
     * Authenticate the user by creating a JWT token.
     * @param username
     * @param password
     * @return Response with the JWT token
     * @throws Exception when invalid credentials are given.
     */
    @Test
    public void authenticationTest1() {
        Client client = ClientBuilder.newClient();
        ObjectMapper mapper = new ObjectMapper();

        WebTarget target = client
                .target(baseUrl + "/auth")
                .queryParam("email", "admin@mail.nl")
                .queryParam("password", "admin");
        Response response = target.request().post(Entity.json(jsonString));

        try {
            String tokenString = response.readEntity(String.class);
            mapper.readValue(tokenString, IdToken.class);

            Assert.assertEquals(200, response.getStatus());
        } catch (Exception e) {

        } finally {
            response.close();
            client.close();
        }
    }


    /***
     * authenticate(@QueryParam("email") String username,
     *              @QueryParam("password") String password)
     *
     * Case: Create a invalid authentication
     *
     * Method:
     * Authenticate the user by creating a JWT token.
     * @param username
     * @param password
     * @return Response with the JWT token
     * @throws Exception when invalid credentials are given.
     */
    @Test
    public void authenticationTest2() {
        Client client = ClientBuilder.newClient();
        ObjectMapper mapper = new ObjectMapper();

        WebTarget target = client
                .target(baseUrl + "/auth")
                .queryParam("email", "admin@mail.nl")
                .queryParam("password", "invalidpassword");
        Response response = target.request().post(Entity.json(jsonString));

        try {
            String tokenString = response.readEntity(String.class);
            mapper.readValue(tokenString, IdToken.class);

            Assert.assertEquals(500, response.getStatus());
        } catch (Exception e) {

        } finally {
            response.close();
            client.close();
        }
    }
}
