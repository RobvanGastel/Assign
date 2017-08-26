package com.robvangastel.assign.firebase;

import com.robvangastel.assign.firebase.domain.Body;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import javax.json.JsonObject;

/**
 * @author Rob van Gastel
 */
public interface RegisterClient {

    /***
     * HTTP Call to Firebase regarding Notification content.
     * @param apiKey Firebase API key
     * @param senderId Firebase Project id
     * @param body JSON body with mutation values
     * @return A valid JSON response or an exception on a invalid statuscode
     */
    @RequestLine("POST /")
    @Headers({"Content-Type: application/json", "Authorization: key={api_key}", "project_id: {sender_id}"})
    Body register(@Param("api_key") String apiKey,
                    @Param("sender_id") String senderId, Body body);
}