package com.robvangastel.assign.controllers;

import javax.ejb.Stateless;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Rob on 23-4-2017.
 */

@Stateless
@Path("/user")
@Produces({MediaType.APPLICATION_JSON})
public class PostController {
}
