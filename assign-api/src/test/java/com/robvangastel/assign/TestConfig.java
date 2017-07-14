package com.robvangastel.assign;

/**
 * @author Rob van Gastel
 */
public class TestConfig {

    private static String baseUrl;

    // TODO create properties file for config
    public TestConfig() {
        setBaseUrl("http://localhost:9080/assign/api");
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static void setBaseUrl(String baseUrl) {
        TestConfig.baseUrl = baseUrl;
    }
}
