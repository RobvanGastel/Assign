package com.robvangastel.assign.controllers;

import com.robvangastel.assign.domain.Role;
import com.robvangastel.assign.domain.School;
import com.robvangastel.assign.domain.Study;
import com.robvangastel.assign.domain.User;
import com.robvangastel.assign.security.Secured;
import com.robvangastel.assign.services.SchoolService;

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

@RequestScoped // Request scoped for the Filters
@Path("/schools")
//@Secured({Role.ADMIN})
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
     * @return a list of all the school objects
     */
    @GET
    public Response get(
            @DefaultValue("0") @QueryParam("start") int start,
            @DefaultValue("20") @QueryParam("size") int size) {
        return Response.ok(schoolService.findAll(start, size))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .header("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, Content-Length, Authentication, Authorization")
                .build();
    }

    /***
     * Get a post by id
     * @param id of the post
     * @return A School object with matching id or statuscode 404
     * when no schools are found.
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
     * @param start of the list
     * @param size  of the list
     * @return a list of Study objects or or statuscode 404
     * when no studies are found.
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
     * @param id of the school
     * @param start of the list
     * @param size of the list
     * @return A list of User objects or statuscode 404
     * when no users are found.
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
     * get users by school and study
     * @param id of the school
     * @param study name of the study
     * @param start of the list
     * @param size of the list
     * @return A list of the User objects or statuscode 404
     * when no users are found.
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
     * Create a School
     * @param name of the school
     * @return the created school
     * @throws Exception when a invalid name parameter is given.
     */
    @POST
    public Response create(@QueryParam("name") String name) throws Exception {

        if (!name.equals(null)) {
            schoolService.create(new School(name));
            return Response.ok().build();

        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }

    /***
     * Add a study to a school
     * @param id of the school
     * @param name of the study
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

    /***
     * delete school by id
     * @param id of the school
     * @return a statuscode indicating success or failure
     * @throws Exception when the School object failed to get deleted.
     */
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") long id) throws Exception {
        schoolService.delete(id);
        return Response.noContent().build();
    }
}
