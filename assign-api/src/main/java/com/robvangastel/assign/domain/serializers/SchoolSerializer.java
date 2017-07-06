package com.robvangastel.assign.domain.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.robvangastel.assign.domain.School;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 *
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

        

        jgen.writeStartObject();
        jgen.writeNumberField("id", school.getId());
        jgen.writeStringField("name", school.getName());
        jgen.writeStringField("schoolCode", school.getSchoolCode());
        jgen.writeStringField("DateCreated", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(school.getDateCreated()));
        jgen.writeEndObject();
    }
}
