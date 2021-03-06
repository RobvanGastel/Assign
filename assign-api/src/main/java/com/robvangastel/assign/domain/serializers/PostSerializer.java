package com.robvangastel.assign.domain.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.robvangastel.assign.domain.Post;
import com.robvangastel.assign.domain.Reply;

import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Rob van Gastel
 */

@Provider
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
        List<Reply> replies = post.getReplies();

        jgen.writeStartObject();
        jgen.writeNumberField("id", post.getId());
        jgen.writeStringField("url", post.getUrl());
        jgen.writeStringField("title", post.getTitle());
        jgen.writeStringField("description", post.getDescription());
        jgen.writeStringField("dateCreated", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(post.getDateCreated()));

        // Tags serializer
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

        // Replies serializer
        if (!post.getReplies().isEmpty()) {
            jgen.writeFieldName("replies");
            jgen.writeStartArray();

            for (Reply reply : replies) {
                jgen.writeNumber(reply.getUser().getId());
            }

            jgen.writeEndArray();
        } else {

            jgen.writeFieldName("replies");
            jgen.writeStartArray();
            jgen.writeEndArray();
        }

        jgen.writeFieldName("user");
        jgen.writeStartObject();
        jgen.writeNumberField("id", post.getUser().getId());
        jgen.writeStringField("name", post.getUser().getName());
        jgen.writeStringField("email", post.getUser().getEmail());
        jgen.writeStringField("specialisation", post.getUser().getStudyName());
        jgen.writeStringField("profileImage", post.getUser().getProfileImage());
        jgen.writeStringField("dateCreated", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(post.getUser().getDateCreated()));

        if (post.getUser().getSocialLink() != null) {
            jgen.writeObjectFieldStart("socialLink");
            jgen.writeStringField("twitter", post.getUser().getSocialLink().getTwitter());
            jgen.writeStringField("facebook", post.getUser().getSocialLink().getFacebook());
            jgen.writeStringField("phonenumber", post.getUser().getSocialLink().getPhonenumber());
            jgen.writeEndObject();
        } else {
            jgen.writeObjectFieldStart("socialLink");
            jgen.writeEndObject();
        }

        jgen.writeEndObject();

        jgen.writeBooleanField("done", post.isDone());
        jgen.writeEndObject();

    }
}
