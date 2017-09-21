package com.robvangastel.assign.domain.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.robvangastel.assign.domain.Notification;

import java.io.IOException;

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
        jgen.writeEndObject();
    }
}