package com.robvangastel.assign.firebase;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Rob van Gastel
 */
public class FirebaseProperties {

    private static FirebaseProperties instance = null;
    private Properties properties;

    protected FirebaseProperties() throws IOException {
        properties = new Properties();
        properties.load(getClass().getResourceAsStream("/META-INF/firebase.properties"));
    }

    public static FirebaseProperties getInstance() {
        if(instance == null) {
            try {
                instance = new FirebaseProperties();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public String getValue(String key) {
        return properties.getProperty(key);
    }
}
