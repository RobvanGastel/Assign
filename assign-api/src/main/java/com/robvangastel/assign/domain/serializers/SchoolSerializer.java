package com.robvangastel.assign.domain.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.robvangastel.assign.domain.School;
import com.robvangastel.assign.domain.Study;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Rob van Gastel
 */

public class SchoolSerializer extends StdSerializer<School> {

    public SchoolSerializer() {
        this(null);
    }

    protected SchoolSerializer(Class<School> t) {
        super(t);
    }

    @Override
    public void serialize(School school, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {

        List<Study> studies = school.getStudies();

        jgen.writeStartObject();
        jgen.writeNumberField("id", school.getId());
        jgen.writeStringField("name", school.getName());
        jgen.writeStringField("schoolCode", school.getSchoolCode());
        jgen.writeStringField("DateCreated", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(school.getDateCreated()));

        if (!school.getStudies().isEmpty()) {
            jgen.writeFieldName("studies");
            jgen.writeStartArray();

            for (Study s : studies) {
                jgen.writeString(s.getName());
            }

            jgen.writeEndArray();
        } else {
            jgen.writeFieldName("studies");
            jgen.writeStartArray();
            jgen.writeEndArray();
        }

        jgen.writeEndObject();
    }
}
