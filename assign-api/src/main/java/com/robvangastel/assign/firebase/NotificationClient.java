package com.robvangastel.assign.firebase;

import com.robvangastel.assign.firebase.domain.Notification;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

import javax.json.JsonObject;

/**
 * @author Rob van Gastel
 */
public interface NotificationClient {

    /***
     * HTTP Call to Firebase to register for notifications.
     * @param apiKey Firebase API key
     * @param body JSON body with mutation values
     * @return A valid JSON response or an exception on a invalid statuscode
     */
    @RequestLine("POST /")
    @Headers({"Content-Type: application/json", "Authorization: key={api_key}"})
    JsonObject send(@Param("api_key") String apiKey, Notification body);
}

