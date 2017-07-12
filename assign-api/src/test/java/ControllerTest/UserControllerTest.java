package ControllerTest;

import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 * Created by robvangastel on 12/07/2017.
 */
public class UserControllerTest {

    private static String baseUrl = "http://localhost:9080/assign/api";

    /***
     * EXAMPLE UNIT TEST
     *
     * Case: STATE TEST CASE
     *
     * Method: STATE METHOD DESCRIPTION
     */
    @Test
    public void getByIdTest() {

        Client client = ClientBuilder.newClient();
		WebTarget target = client.target(baseUrl + "/users/" + 1);
		Response response = target.request().get();
		System.out.println(response.toString());

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

}
