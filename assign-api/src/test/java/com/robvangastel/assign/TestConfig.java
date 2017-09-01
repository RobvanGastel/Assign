package com.robvangastel.assign;

import org.jboss.logging.Logger;

/**
 * @author Rob van Gastel
 */
public class TestConfig {

    private static final Logger LOG = Logger.getLogger(TestConfig.class.getSimpleName());

    private static String baseUrl;

    // TODO create properties file for config

    /***
     * Deployment url:
     * https://api.assignapp.nl/v0.1
     *
     * Local url:
     * http://localhost:9080/v0.1
     */
    public TestConfig() {
        setBaseUrl("http://localhost:9080/v0.1");
        LOG.info("The url used: " + baseUrl);
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static void setBaseUrl(String baseUrl) {
        TestConfig.baseUrl = baseUrl;
    }
}