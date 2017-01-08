package com.robvangastel.assign.api.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *
 * @author Rob
 */

@Configuration
@EnableWebMvc
@ComponentScan( basePackages = "com.robvangastel.assign.api" )
public class ApplicationConfiguration {
    
}