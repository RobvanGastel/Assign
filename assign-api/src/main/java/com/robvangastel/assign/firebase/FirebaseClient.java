package com.robvangastel.assign.firebase;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

import javax.json.JsonObject;

/**
 * @author Rob van Gastel
 */


public interface FirebaseClient {

    @RequestLine("POST /")
    @Headers({"Content-Type: application/json", "Authorization: key={api_key}", "project_id: {sender_id}"})
    JsonObject registation(@Param("api_key") String apiKey,
                           @Param("sender_id") String senderId, Body body);
}
