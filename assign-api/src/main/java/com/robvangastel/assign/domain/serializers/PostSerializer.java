package com.robvangastel.assign.domain.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.robvangastel.assign.domain.Post;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Rob van Gastel
 */
public class PostSerializer extends StdSerializer<Post> {

    public PostSerializer() {
        this(null);
    }

    protected PostSerializer(Class<Post> t) {
        super(t);
    }

    @Override
    public void serialize(Post post, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {

        List<String> tags = post.getTags();

        jgen.writeStartObject();
        jgen.writeNumberField("id", post.getId());
        jgen.writeStringField("url", post.getUrl());
        jgen.writeStringField("title", post.getTitle());
        jgen.writeStringField("description", post.getDescription());
        jgen.writeStringField("dateCreated", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(post.getDateCreated()));

        if (!post.getTags().isEmpty()) {
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

        jgen.writeFieldName("user");
        jgen.writeStartObject();
        jgen.writeNumberField("id", post.getUser().getId());
        jgen.writeStringField("name", post.getUser().getName());
        jgen.writeStringField("email", post.getUser().getEmail());
        jgen.writeStringField("profileImage", post.getUser().getProfileImage());
        jgen.writeStringField("dateCreated", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(post.getUser().getDateCreated()));
        jgen.writeEndObject();

        jgen.writeBooleanField("done", post.isDone());
        jgen.writeEndObject();

    }
}
