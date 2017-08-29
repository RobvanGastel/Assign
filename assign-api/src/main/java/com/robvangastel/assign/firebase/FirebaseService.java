package com.robvangastel.assign.firebase;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.robvangastel.assign.dao.IFirebaseDao;
import com.robvangastel.assign.dao.IUserDao;
import com.robvangastel.assign.domain.Firebase;
import com.robvangastel.assign.domain.User;
import com.robvangastel.assign.firebase.domain.Body;
import com.robvangastel.assign.firebase.domain.Operations;
import com.robvangastel.assign.firebase.domain.Payload;
import org.json.JSONObject;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Rob van Gastel
 */
@Stateless
public class FirebaseService implements Serializable {

    private final static String URL_REGISTER = FirebaseProperties.getInstance().getValue("url.register");
    private final static String URL_SEND = FirebaseProperties.getInstance().getValue("url.send");
    private final static String SENDER_ID = FirebaseProperties.getInstance().getValue("senderid");
    private final static String API_KEY = FirebaseProperties.getInstance().getValue("apikey");

    @Inject
    private IFirebaseDao firebaseDao;

    @Inject
    private IUserDao userDao;

    @PostConstruct
    public void initialize() {
        Unirest.setObjectMapper(new com.mashape.unirest.http.ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    /**
     * Removes a registration id from Firebase.
     *
     * Body variables:
     * "operation": "remove",
     * "notification_key_name": user.notification_key_name
     * "notification_key": user.notification_key
     * "registration_ids": registration_ids
     *
     * @param body
     * @throws Exception When Firebase gives a invalid statuscode
     */
    public void removeRegistrationId(Body body, Long id) throws Exception {
        body.setOperation(Operations.remove.toString());
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "key=" + API_KEY);
        headers.put("project_id", "key=" + SENDER_ID);

        HttpResponse<Body> response = Unirest.post(URL_REGISTER)
                .headers(headers)
                .asObject(Body.class);
//        Body response = response.getBody();

        Firebase firebase = firebaseDao.findById(id);
        firebase.getRegisterIds().removeAll(body.getRegistration_ids());
        firebaseDao.update(firebase);
    }

    /**
     * Add a registration id to Firebase.
     *
     * Body variables:
     * "operation": "add",
     * "notification_key_name": user.notification_key_name
     * "notification_key": user.notification_key
     * "registration_ids": registration_ids
     *
     * @param body
     * @throws Exception When Firebase gives a invalid statuscode
     */
    public void addRegistrationId(Body body, Long id) throws Exception {
        body.setOperation(Operations.add.toString());
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "key=" + API_KEY);
        headers.put("project_id", "key=" + SENDER_ID);

        HttpResponse<Body> response = Unirest.post(URL_REGISTER)
                .headers(headers)
                .asObject(Body.class);

        Firebase firebase = firebaseDao.findById(id);
        firebase.getRegisterIds().addAll(body.getRegistration_ids());
        firebaseDao.update(firebase);
    }

    /**
     * Creates a Notification Key in Firebase when the user first opens the
     * app on a mobile device.
     *
     * Body variables:
     * "operation": "create"
     * "notification_key_name": user.notification_key_name
     * "registration_ids": user.registration_ids
     *
     * @param body
     * @param id The user Id
     * @return The created register key
     * @throws Exception When Firebase gives a invalid statuscode
     */
    public void createNotificationkey(Body body, Long id) throws Exception {
        body.setOperation(Operations.create.toString());
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "key=" + API_KEY);
        headers.put("project_id", "key=" + SENDER_ID);

        HttpResponse<Body> response = Unirest.post(URL_REGISTER)
                .headers(headers)
                .asObject(Body.class);

        Body responseBody = response.getBody();

        User user = userDao.findById(id);
        Firebase firebase = user.getFirebase();
        firebase.setNotificationKey(responseBody.getNotification_key());
        firebase.setRegisterIds(body.getRegistration_ids());
        firebaseDao.update(firebase);
    }

    /**
     * Send Notification to Device group.
     *
     * Notification Body:
     * {
     * "to" : "bk3RNwTe3H0:CI2k_HHwgIpoDKCIZvvDMExUdFQ3P1...",
     * "priority" : "normal",
     *  "notification" : {
     *  "body" : "This week’s edition is now available.",
     *  "title" : "NewsMagazine.com",
     *  "icon" : "new",
     *  },
     *  "data" : {
     *  "volume" : "3.21.15",
     *  "contents" : "http://www.news-magazine.com/world-week/21659772"
     *  }
     * }
     *
     * @param payload
     * @param id of the User
     * @throws Exception When Firebase gives a invalid statuscode
     */
    public void sendNotification(Payload payload, Long id) throws Exception {

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "key=" + API_KEY);

        HttpResponse response = Unirest.post(URL_SEND)
                .headers(headers)
                .body(payloadToJsonObject(payload))
                .asJson();

        if( response.getStatus() <= 200 && response.getStatus() < 300) {

            // TODO Persist in database
        } else {
            // TODO Throw error
        }

    }

    /**
     * TODO Implement Topic Notifications
     * @param topic
     */
    public void sendNotification(String topic) {

    }

    private JSONObject payloadToJsonObject(Payload payload) {
        com.fasterxml.jackson.databind.ObjectMapper oMapper = new com.fasterxml.jackson.databind.ObjectMapper();
        JSONObject json = new JSONObject();

        Map<String, Object> notification = oMapper.convertValue(payload.getNotification(), Map.class);
        Map<String, Object> data = oMapper.convertValue(payload.getData(), Map.class);

        json.put("to", payload.getTo());
        json.put("notification", notification);
        json.put("data", data);

        return json;
    }
}