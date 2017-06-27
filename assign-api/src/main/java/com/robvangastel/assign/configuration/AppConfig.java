package com.robvangastel.assign.configuration;

import com.robvangastel.assign.controllers.AuthController;
import com.robvangastel.assign.controllers.ImageController;
import com.robvangastel.assign.controllers.PostController;
import com.robvangastel.assign.controllers.UserController;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

/**
 *
 * @author Rob van Gastel
 */

@ApplicationPath("/api")
public class AppConfig extends Application {

    public AppConfig() {
        // Swagger.io configuration
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0"); // Keep updated to POM.xml version
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("84.26.134.115:8080"); // Localhost:8080 for local deployment
        beanConfig.setBasePath("/assign/api");
        beanConfig.setResourcePackage("com.robvangastel.assign");
        beanConfig.setScan(true);
    }

    @Override
    public Set<Class<?>> getClasses() {
        // Add Swagger classes to JAX-RS
        Set<Class<?>> resources = new java.util.HashSet<>();
        resources.add(ApiListingResource.class);
        resources.add(SwaggerSerializers.class);
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        // Add API controllers to JAX-RS
        resources.add(AuthController.class);
        resources.add(ImageController.class);
        resources.add(PostController.class);
        resources.add(UserController.class);
    }
}
