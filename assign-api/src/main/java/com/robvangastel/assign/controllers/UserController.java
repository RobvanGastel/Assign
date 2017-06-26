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
 * Created by Rob on 23-4-2017.
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

	@GET
	public List<User> get() {
		return userService.findAll();
	}

	@GET
	@Path("/{id}")
	public Response getById(@PathParam("id") long id) {
		User user = userService.findById(id);
		if(user == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return Response.ok(user).build();
	}

	@GET
	@Path("/email")
	public Response getByEmail(@QueryParam("email") String email) {
		User user = userService.findByEmail(email);
		if(user == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return Response.ok(user).build();
	}

	@POST
	public Response create(@QueryParam("email") String email,
					 @QueryParam("password") String password,
					 @QueryParam("firstName") String firstName) throws Exception {
		User user = userService.create(new User(email, password, firstName));
		if(user == null) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}
		return Response.ok(user).build();
	}

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

	@DELETE
	@Path("/{id}")
	@Secured({Role.ADMIN})
	public Response delete(@PathParam("id") long id) throws Exception {
		User user = userService.findByEmail(securityContext.getUserPrincipal().getName());
		if(user.getRole().equals(Role.ADMIN.toString())) {
			userService.delete(id);
			return Response.noContent().build();
		} else {
			throw new WebApplicationException(Response.Status.UNAUTHORIZED);
		}
	}
}