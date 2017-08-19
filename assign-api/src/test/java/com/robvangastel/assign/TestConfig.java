package com.robvangastel.assign;

/**
 * @author Rob van Gastel
 */
public class TestConfig {

    private static String baseUrl;

    // TODO create properties file for config

    /***
     * Deployment url:
     * http://84.26.134.115:8080/assign/api
     *
     * Local url:
     * http://localhost:9080/assign/api
     */
    public TestConfig() {
        setBaseUrl("http://***REMOVED***:8080/assign/api");
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static void setBaseUrl(String baseUrl) {
        TestConfig.baseUrl = baseUrl;
    }
}