package br.com.lpd.serialization.serializer;

import br.com.lpd.model.Gender;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class GenderSerializer
    extends
        JsonSerializer<Gender> {

    @Override
    public void serialize(Gender s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if(s == null) {
            jsonGenerator.writeNull();
        }
        else {
            jsonGenerator.writeString(s.getValue());
        }
    }
}
