package com.robvangastel.assign.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robvangastel.assign.TestConfig;
import com.robvangastel.assign.security.IdToken;
import org.junit.BeforeClass;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 * @author Rob van Gastel
 */
public class NotificationControllerTest {

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

    /***
     * The beforeClass handles the authentication token to make
     * requests to the PostController.
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

        } catch (Exception e) {

        } finally {
            response.close();
            client.close();
        }
    }
}
