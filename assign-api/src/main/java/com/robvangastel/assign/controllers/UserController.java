package com.robvangastel.assign.controllers;

import com.robvangastel.assign.domain.Role;
import com.robvangastel.assign.domain.User;
import com.robvangastel.assign.security.Secured;
import com.robvangastel.assign.services.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

/**
 * Created by Rob on 23-4-2017.
 */

@Stateless
@Path("/users")
@Produces({MediaType.APPLICATION_JSON})
public class UserController {
	//TODO Add correct auth levels

	@Inject
	private UserService userService;

	private static final String FILE_PATH = "c:\\myfile.txt";

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
		//TODO validate variables validator
		User user = userService.create(new User(email, password, firstName));
		if(user == null) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}
		return Response.ok(user).build();
	}

	@PUT
	@Path("/{id}")
	@Secured({Role.USER, Role.ADMIN, Role.MODERATOR})
	public Response update(@PathParam("id") long id,
					   @QueryParam("location") String location,
					   @QueryParam("websiteURL") String websiteURL,
					   @QueryParam("bio") String bio) throws Exception {
		//TODO add validation and correct variables
		User user = userService.findById(id);
		if(user == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") long id) throws Exception {
		userService.delete(id);
		return Response.noContent().build();
	}
}
