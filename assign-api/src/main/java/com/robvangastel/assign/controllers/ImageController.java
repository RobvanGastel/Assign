package com.robvangastel.assign.controllers;

import com.robvangastel.assign.domain.User;
import com.robvangastel.assign.services.UserService;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

/**
 * Created by Rob on 21-5-2017.
 */

@Stateless
@Path("img")
public class ImageController {

    /*
     * The maximum allowed file size in megabytes.
     * Files larger than this size will not be uploadable or downloadable.
     */
    private static final int MAX_SIZE_IN_MB = 1;

    /*
     * The directory where the images will be stored.
     * Make sure this directory exists before you run the service.
     */
    private static final java.nio.file.Path BASE_DIR = Paths.get(System.getProperty("user.home"), "img");

    @Inject
    private UserService userService;

    /*
     * Download a list of all image file names.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray getFileNames() throws IOException {

        // Filters out all non JPEG or PNG files, as well as files larger than the maximum allowed size.
        DirectoryStream.Filter<java.nio.file.Path> filter = entry -> {
            boolean sizeOk = Files.size(entry) <= 1024 * 1024 * MAX_SIZE_IN_MB;
            boolean extensionOk = entry.getFileName().toString().endsWith("jpg") || entry.getFileName().toString().endsWith("png");
            return sizeOk && extensionOk;
        };

        // Browse the filtered directory and list all the files.
        JsonArrayBuilder results = Json.createArrayBuilder();
        for (java.nio.file.Path entry : Files.newDirectoryStream(BASE_DIR, filter)) {
            results.add(entry.getFileName().toString());
        }
        return results.build();
    }

    @POST
    @Path("/{id}/user")
    public Response uploadProfile(@PathParam("id") long id,
                                  InputStream in,
                                  @HeaderParam("Content-Type") String fileType,
                                  @HeaderParam("Content-Length") long fileSize,
                                  @Context UriInfo uriInfo) throws Exception {

        //TODO Add file path to Object
        User u = userService.findById(id);

        // Make sure the file is not larger than the maximum allowed size.
        if (fileSize > 1024 * 1024 * MAX_SIZE_IN_MB) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity("Image is larger than " + MAX_SIZE_IN_MB + "MB").build());
        }

        //TODO Add better HASH for file upload
        // Generate a random file name based on the current time.
        // This probably isn't 100% safe but works fine for this example.
        String fileName = "" + System.currentTimeMillis();

        if (fileType.equals("image/jpeg")) {
            fileName += ".jpg";
        } else {
            fileName += ".png";
        }

        u.setProfileImage(fileName);
        userService.update(u);

        // Copy the file to its location.
        Files.copy(in, BASE_DIR.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(fileName);

        // Return a 201 Created response with the appropriate Location header.
        return Response.created(URI.create("img/" + fileName)).build();
    }

    /*
     * Download a JPEG file.
     */
    @GET
    @Path("{name}.jpg")
    @Produces("image/jpeg")
    public InputStream getJpegImage(@PathParam("name") String fileName) throws IOException {

        fileName += ".jpg";
        java.nio.file.Path dest = BASE_DIR.resolve(fileName);

        if (!Files.exists(dest)) {
            throw new WebApplicationException(Status.NOT_FOUND);
        }

        return Files.newInputStream(dest);
    }

    /*
     * Download a PNG file.
     */
    @GET
    @Path("{name}.png")
    @Produces("image/png")
    public InputStream getPngImage(@PathParam("name") String fileName) throws IOException {

        fileName += ".png";
        java.nio.file.Path dest = BASE_DIR.resolve(fileName);

        if (!Files.exists(dest)) {
            throw new WebApplicationException(Status.NOT_FOUND);
        }

        return Files.newInputStream(dest);
    }
}
