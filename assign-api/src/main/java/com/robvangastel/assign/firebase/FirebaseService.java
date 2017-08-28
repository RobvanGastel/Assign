package com.robvangastel.assign.firebase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robvangastel.assign.dao.IFirebaseDao;
import com.robvangastel.assign.dao.IUserDao;
import com.robvangastel.assign.domain.Firebase;
import com.robvangastel.assign.domain.User;
import com.robvangastel.assign.firebase.domain.Body;
import com.robvangastel.assign.firebase.domain.Notification;
import com.robvangastel.assign.firebase.domain.Operations;
import com.robvangastel.assign.firebase.domain.Payload;

import feign.Feign;
import feign.FeignException;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import org.json.simple.JSONObject;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.HttpMethod;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
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

    RegisterClient registerClient;

    @Inject
    private IFirebaseDao firebaseDao;

    @Inject
    private IUserDao userDao;

    @PostConstruct
    public void initialize() {

        registerClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(RegisterClient.class, URL_REGISTER);

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
     * @throws FeignException When Firebase gives a invalid statuscode
     */
    public void removeRegistrationId(Body body, Long id) throws FeignException {
        body.setOperation(Operations.remove.toString());
        registerClient.register(API_KEY, SENDER_ID, body);

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
     * @throws FeignException When Firebase gives a invalid statuscode
     */
    public void addRegistrationId(Body body, Long id) throws FeignException {
        body.setOperation(Operations.add.toString());
        registerClient.register(API_KEY, SENDER_ID, body);

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
     * @throws FeignException When Firebase gives a invalid statuscode
     */
    public void createNotificationkey(Body body, Long id) throws FeignException {
        body.setOperation(Operations.create.toString());
        Body response = registerClient.register(API_KEY, SENDER_ID, body);

        User user = userDao.findById(id);

        Firebase firebase = user.getFirebase();
        firebase.setNotificationKey(response.getNotification_key());
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
     * @throws FeignException When Firebase gives a invalid statuscode
     */
    public void sendNotification(Payload payload, Long id) throws Exception {

        JSONObject jsonPayload = payloadToJsonObject(payload);
        HttpURLConnection connection = createURLConnection(URL_SEND, API_KEY);
        OutputStream os = connection.getOutputStream();
        os.write(jsonPayload.toJSONString().getBytes());
        System.out.println(connection.getResponseCode() + " " + connection.getResponseMessage() + " " + connection.getContent().toString() );
        connection.disconnect();

        // TODO Persist in database
    }

    /**
     * TODO Implement Topic Notifications
     * @param topic
     */
    public void sendNotification(String topic) {

    }

    private HttpURLConnection createURLConnection(String url, String apiKey) throws IOException {
        URL u = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) u.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod(HttpMethod.POST);
        connection.setRequestProperty( "Authorization", "key=" + apiKey);
        connection.setRequestProperty( "Content-Type", "application/json");
        return connection;
    }

    private JSONObject payloadToJsonObject(Payload payload) {
        ObjectMapper oMapper = new ObjectMapper();
        JSONObject json = new JSONObject();

        Map<String, Object> notification = oMapper.convertValue(payload.getNotification(), Map.class);
        Map<String, Object> data = oMapper.convertValue(payload.getData(), Map.class);

        json.put("to", payload.getTo());
        json.put("notification", notification);
        json.put("data", data);

        return json;
    }
}