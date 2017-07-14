package com.robvangastel.assign.domain.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.robvangastel.assign.domain.User;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Rob van Gastel
 */
public class UserSerializer extends StdSerializer<User> {

    public UserSerializer() {
        this(null);
    }

    protected UserSerializer(Class<User> t) {
        super(t);
    }

    @Override
    public void serialize(User user, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {

        List<String> tags = user.getTags();

        jgen.writeStartObject();
        jgen.writeNumberField("id", user.getId());
        jgen.writeStringField("name", user.getName());
        jgen.writeStringField("email", user.getEmail());

        jgen.writeStringField("specialisation", user.getStudyName());
        jgen.writeStringField("study", user.getStudy().getName());

        jgen.writeStringField("profileImage", user.getProfileImage());
        jgen.writeStringField("dateCreated", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(user.getDateCreated()));

        if (!user.getTags().isEmpty()) {
            jgen.writeFieldName("tags");
            jgen.writeStartArray();

            for (String tag : tags) {
                jgen.writeString(tag);
            }

            jgen.writeEndArray();
        } else {
            jgen.writeFieldName("tags");
            jgen.writeStartArray();
            jgen.writeEndArray();
        }

        if (user.getSocialLink() != null) {
            jgen.writeObjectFieldStart("socialLink");
            jgen.writeStringField("twitter", user.getSocialLink().getTwitter());
            jgen.writeStringField("facebook", user.getSocialLink().getFacebook());
            jgen.writeStringField("phonenumber", user.getSocialLink().getPhonenumber());

            jgen.writeEndObject();
        } else {
            jgen.writeObjectFieldStart("socialLink");
            jgen.writeEndObject();
        }

        jgen.writeEndObject();
    }
}
