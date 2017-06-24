package com.robvangastel.assign.configuration;

import com.robvangastel.assign.controllers.AuthController;
import com.robvangastel.assign.controllers.ImageController;
import com.robvangastel.assign.controllers.PostController;
import com.robvangastel.assign.controllers.UserController;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

/**
 * Created by Rob on 29-5-2017.
 */

@ApplicationPath("/api")
public class AppConfig extends Application {

    public AppConfig() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/assign/api");
        beanConfig.setResourcePackage("com.robvangastel.assign");
        beanConfig.setScan(true);
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        resources.add(ApiListingResource.class);
        resources.add(SwaggerSerializers.class);
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(AuthController.class);
        resources.add(ImageController.class);
        resources.add(PostController.class);
        resources.add(UserController.class);
    }
}
