package com.robvangastel.assign.controllers;

import com.robvangastel.assign.domain.Role;
import com.robvangastel.assign.domain.User;
import com.robvangastel.assign.security.Secured;
import com.robvangastel.assign.services.UserService;
import io.swagger.annotations.Api;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

/**
 *
 * @author Rob van Gastel
 */

@RequestScoped
@Path("/users")
@Api(tags = {"users"}, value = "/users", description = "Operations about users")
@Produces({MediaType.APPLICATION_JSON})
public class UserController {

	@Inject
	private UserService userService;

	@Context
	private SecurityContext securityContext;

	/***
	 * get all the users
	 * @return An array of Users
	 */
	@GET
	public List<User> get() {
		return userService.findAll();
	}

	/***
	 * Get user by id
	 * @param id of the user
	 * @return the User object with matching id
	 */
	@GET
	@Path("/{id}")
	public Response getById(@PathParam("id") long id) {
		User user = userService.findById(id);
		if(user == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return Response.ok(user).build();
	}

	/***
	 * get user by email
	 * @param email of the user
	 * @return the User object with matching email
	 */
	@GET
	@Path("/email")
	public Response getByEmail(@QueryParam("email") String email) {
		User user = userService.findByEmail(email);
		if(user == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return Response.ok(user).build();
	}

	/***
	 * Register a user
	 * @param email
	 * @param password
	 * @param name
	 * @param code
	 * @return the created user
	 * @throws Exception
	 */
	@POST
	public Response create(@QueryParam("email") String email,
					 @QueryParam("password") String password,
					 @QueryParam("name") String name,
					 @QueryParam("code") String code) throws Exception {
		User user = userService.create(new User(email, password, name, code));
		if(user == null) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}
		return Response.ok(user).build();
	}

	/***
	 * Update a user
	 * @param location
	 * @param websiteURL
	 * @param bio
	 * @return A matching status code to indicate success or failure
	 * @throws Exception
	 */
	@PUT
	@Secured({Role.USER})
	public Response update(@QueryParam("location") String location,
					   @QueryParam("websiteURL") String websiteURL,
					   @QueryParam("bio") String bio) throws Exception {
		//TODO Update function
		User user = userService.findByEmail(securityContext.getUserPrincipal().getName());
		if(user == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return Response.noContent().build();
	}

	/***
	 * Delete a user by id
	 * @param id of the user
	 * @return A matching status code to indicate success or failure
	 * @throws Exception
	 */
	@DELETE
	@Path("/{id}")
	@Secured({Role.ADMIN})
	public Response delete(@PathParam("id") long id) throws Exception {
		userService.delete(id);
		return Response.noContent().build();
	}
}
