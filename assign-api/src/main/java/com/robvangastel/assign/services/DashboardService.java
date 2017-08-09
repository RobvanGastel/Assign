package com.robvangastel.assign.services;

import com.robvangastel.assign.dao.IUserDao;
import com.robvangastel.assign.domain.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Rob van Gastel
 */
@RequestScoped // Request scoped for the Filters
@Path("/users")
@Produces({MediaType.APPLICATION_JSON})
public class DashboardService {

    @Inject
    private IUserDao userDao;

    @GET
    @Path("/users")
    public List<User> findAllUsers(int start, int size) {
        return userDao.findAll(start, size);
    }
}
