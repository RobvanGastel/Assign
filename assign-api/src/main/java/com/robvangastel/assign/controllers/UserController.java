package com.robvangastel.assign.controllers;

import com.robvangastel.assign.domain.User;
import com.robvangastel.assign.security.Credentials;
import com.robvangastel.assign.security.Secured;
import com.robvangastel.assign.services.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

/**
 * Created by Rob on 23-4-2017.
 */

@Stateless
@Path("/user")
@Produces({MediaType.APPLICATION_JSON})
public class UserController {

	@Inject
	private UserService userService;

	@GET
	public List<User> get() {
		return userService.findAll();
	}

	@GET
	@Path("/{id}")
	public User getById(@PathParam("id") long id) {
		User user = userService.findById(id);
		if(user == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return user;
	}

	@GET
	@Path("/username")
	public User getByUsername(@QueryParam("username") String username) {
		User user = userService.findByUsername(username);
		if(user == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return user;
	}

	@GET
	@Path("/email")
	public User getByEmail(@QueryParam("email") String email) {
		User user = userService.findByEmail(email);
		if(user == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return user;
	}

	@POST
	public User post(@QueryParam("email") String email,
					 @QueryParam("username") String username,
					 @QueryParam("password") String password) throws Exception {
		User user = userService.create(new User(email, username, password));
		if(user == null) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}
		return user;
	}

	@POST
	@Path("/authenticate")
	public void post(@QueryParam("username") String username,
					 @QueryParam("password") String password) throws Exception {
		userService.authenticate(new Credentials(username, password));
	}

	@PUT
	@Secured
	public void update(@QueryParam("id") long id,
					   @QueryParam("location") String location,
					   @QueryParam("websiteURL") String websiteURL,
					   @QueryParam("bio") String bio) throws Exception {
		User user = userService.findById(id);
		if(user == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		userService.update(user);
	}

	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") long id) throws Exception {
		userService.delete(id);
	}
}
