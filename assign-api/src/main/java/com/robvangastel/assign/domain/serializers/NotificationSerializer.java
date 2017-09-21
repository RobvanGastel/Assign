package com.robvangastel.assign.domain.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.robvangastel.assign.domain.Notification;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Created by robvangastel on 07/09/2017.
 */
public class NotificationSerializer extends StdSerializer<Notification> {

    public NotificationSerializer() {
        this(null);
    }

    protected NotificationSerializer(Class<Notification> t) {
        super(t);
    }

    @Override
    public void serialize(Notification notification, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {

        jgen.writeStartObject();
        jgen.writeNumberField("id", notification.getId());
        jgen.writeStringField("title", notification.getTitle());
        jgen.writeStringField("body", notification.getBody());

        jgen.writeFieldName("sender");
        jgen.writeStartObject();
        jgen.writeNumberField("id", notification.getUser().getId());
        jgen.writeStringField("name", notification.getUser().getName());
        jgen.writeStringField("email", notification.getUser().getEmail());
        jgen.writeStringField("specialisation", notification.getUser().getStudyName());
        jgen.writeStringField("profileImage", notification.getUser().getProfileImage());
        jgen.writeStringField("dateCreated", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(notification.getUser().getDateCreated()));

        if (notification.getUser().getSocialLink() != null) {
            jgen.writeObjectFieldStart("socialLink");
            jgen.writeStringField("twitter", notification.getUser().getSocialLink().getTwitter());
            jgen.writeStringField("facebook", notification.getUser().getSocialLink().getFacebook());
            jgen.writeStringField("phonenumber", notification.getUser().getSocialLink().getPhonenumber());
            jgen.writeEndObject();
        } else {
            jgen.writeObjectFieldStart("socialLink");
            jgen.writeEndObject();
        }
        jgen.writeEndObject();

        jgen.writeBooleanField("readNotification", notification.getReadNotification());
        jgen.writeNumberField("postId", notification.getPostId());
        jgen.writeStringField("dateCreated", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(notification.getDateCreated()));
        jgen.writeEndObject();
    }
}