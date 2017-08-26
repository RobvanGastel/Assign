package com.robvangastel.assign.firebase;

import com.robvangastel.assign.dao.IFirebaseDao;
import com.robvangastel.assign.dao.IUserDao;
import com.robvangastel.assign.domain.Firebase;
import com.robvangastel.assign.domain.User;
import com.robvangastel.assign.firebase.domain.Body;
import com.robvangastel.assign.firebase.domain.Notification;
import com.robvangastel.assign.firebase.domain.Operations;
import feign.Feign;
import feign.FeignException;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import java.io.Serializable;

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
    NotificationClient notificationClient;

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

        notificationClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(NotificationClient.class, URL_SEND);
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
        JsonObject response = registerClient.register(API_KEY, SENDER_ID, body);

        User user = userDao.findById(id);

        Firebase firebase = user.getFirebase();
        firebase.setNotificationKey(response.getString("notification_key"));
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
     * @param notification
     * @throws FeignException When Firebase gives a invalid statuscode
     */
    public void sendNotification(Notification notification) throws FeignException {
        JsonObject response = notificationClient.send(API_KEY, notification);

        // TODO Persist in database
    }

    /**
     * TODO Implement Topic Notifications
     * @param topic
     */
    public void sendNotification(String topic) {

    }
}