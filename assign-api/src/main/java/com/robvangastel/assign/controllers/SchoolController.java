package com.robvangastel.assign.controllers;

import com.robvangastel.assign.domain.Role;
import com.robvangastel.assign.domain.School;
import com.robvangastel.assign.domain.Study;
import com.robvangastel.assign.domain.User;
import com.robvangastel.assign.security.Secured;
import com.robvangastel.assign.services.SchoolService;
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
 * @author Rob van Gastel
 */

@RequestScoped
@Secured({Role.ADMIN})
@Path("/schools")
@Api(tags = {"schools"}, value = "/schools", description = "Operations about schools")
@Produces({MediaType.APPLICATION_JSON})
public class SchoolController {

    @Inject
    private SchoolService schoolService;

    @Context
    private SecurityContext securityContext;

    /***
     * Get all schools
     * @param start of the list
     * @param size of the list
     * @return all the schools
     */
    @GET
    public List<School> get(
            @DefaultValue("0") @QueryParam("start") int start,
            @DefaultValue("20") @QueryParam("size") int size) {
        return schoolService.findAll(start, size);
    }

    /***
     * Get a post by id
     * @param id of the post
     * @return A School object with matching id
     */
    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") long id) {
        School school = schoolService.findById(id);
        if (school == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok(school).build();
    }

    /**
     * Get studies by school
     *
     * @param id    of the school
     * @param start
     * @param size
     * @return
     */
    @GET
    @Path("/{id}/studies")
    public Response getStudiesBySchool(@PathParam("id") long id,
                                       @DefaultValue("0") @QueryParam("start") int start,
                                       @DefaultValue("20") @QueryParam("size") int size) {
        List<Study> studies = schoolService.findStudiesBySchool(id, start, size);
        return Response.ok(studies).build();
    }

    /***
     * Get users by school
     * @param id
     * @param start
     * @param size
     * @return
     */
    @GET
    @Path("/{id}/users")
    public Response getUsersBySchool(@PathParam("id") long id,
                                     @DefaultValue("0") @QueryParam("start") int start,
                                     @DefaultValue("20") @QueryParam("size") int size) {
        List<User> users = schoolService.findUsersBySchool(id, start, size);
        return Response.ok(users).build();
    }

    /***
     * get users By school and study
     * @param id
     * @param study
     * @param start
     * @param size
     * @return
     */
    @GET
    @Path("/{id}/users/study")
    public Response getUsersBySchoolAndStudy(@PathParam("id") long id,
                                             @QueryParam("study") String study,
                                             @DefaultValue("0") @QueryParam("start") int start,
                                             @DefaultValue("20") @QueryParam("size") int size) {
        List<User> users = schoolService.findUsersBySchoolAndStudy(study, id, start, size);
        return Response.ok(users).build();
    }

    /***
     * Create a new School reccord
     * @param name of the school
     * @return the created school
     * @throws Exception
     */
    @POST
    public Response create(@QueryParam("name") String name) throws Exception {

        if (!name.equals(null)) {
            School school = schoolService.create(new School(name));
            return Response.ok(school).build();

        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }

    /***
     * Add a study to a school
     * @param id of the school
     * @param name of the study
     * @return the created study
     */
    @PUT
    @Path("/{id}")
    public Response addStudy(@PathParam("id") long id, @QueryParam("name") String name) {
        School school = schoolService.findById(id);

        if (school == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        Study study = schoolService.addStudy(school, name);
        return Response.ok(study).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") long id) throws Exception {
        schoolService.delete(id);
        return Response.noContent().build();
    }
}
