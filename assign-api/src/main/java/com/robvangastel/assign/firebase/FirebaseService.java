package com.robvangastel.assign.firebase;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;

import javax.ejb.Stateless;
import javax.json.JsonObject;
import java.io.Serializable;
import java.util.Map;

/**
 * @author Rob van Gastel
 */
@Stateless
public class FirebaseService implements Serializable {

    // TODO replace with JSON config file
    private final static String URL = "https://android.googleapis.com/gcm/notification";
    private final static String SENDERID = "1080200886079";
    private final static String APIKEY = "***REMOVED***";

    FirebaseClient client;

    public FirebaseService() {
        client = new Feign.Builder()
                .client(new OkHttpClient())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(FirebaseClient.class, URL);
    }

//        "operation": "remove",
//        "notification_key_name": user.notification_key_name,
//        "notification_key": user.notification_key,
//        "registration_ids": registration_ids
    public boolean removeRegistrationId(Body body) {
        body.setOperation(Operations.remove.toString());

        JsonObject response = client.registation(APIKEY, SENDERID, body);

        return true;
    }

//        "operation": "add",
//        "notification_key_name": user.notification_key_name,
//        "notification_key": user.notification_key,
//        "registration_ids": registration_ids
    public JsonObject addRegistrationId(Body body) {
        body.setOperation(Operations.add.toString());

        return client.registation(APIKEY, SENDERID, body);
    }

//        "operation": "create",
//        "notification_key_name": user.notification_key_name,
//        "registration_ids": user.registration_ids
    public JsonObject createNotificationkey(Body body) {
        body.setOperation(Operations.create.toString());

        return client.registation(APIKEY, SENDERID, body);
    }

    public void sendNotification() {

    }

    public void sendNotification(String topic) {

    }
}