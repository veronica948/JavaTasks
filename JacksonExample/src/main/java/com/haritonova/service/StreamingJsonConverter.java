package com.haritonova.service;

import com.haritonova.entity.Gender;
import com.haritonova.entity.Name;
import com.haritonova.entity.User;
import org.codehaus.jackson.*;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Veronica_Haritonova on 3/14/2016.
 */
public class StreamingJsonConverter {
    private static String ROOT_PATH = "src" + File.separator + "main" +
            File.separator + "resources" + File.separator;
    public User toObject(String filename) throws IOException {
        JsonFactory f = null;
        JsonParser jp = null;
        try {
            f = new JsonFactory();
            jp = f.createJsonParser(new File(ROOT_PATH + filename));
            User user = new User();
            jp.nextToken(); // will return JsonToken.START_OBJECT (verify?)
            while (jp.nextToken() != JsonToken.END_OBJECT) {
                String fieldname = jp.getCurrentName();
                jp.nextToken(); // move to value, or START_OBJECT/START_ARRAY
                if ("name".equals(fieldname)) { // contains an object
                    Name name = new Name();
                    while (jp.nextToken() != JsonToken.END_OBJECT) {
                        String namefield = jp.getCurrentName();
                        jp.nextToken(); // move to value
                        if ("first".equals(namefield)) {
                            name.setFirst(jp.getText());
                        } else if ("last".equals(namefield)) {
                            name.setLast(jp.getText());
                        } else {
                            throw new IllegalStateException("Unrecognized field '" + fieldname + "'!");
                        }
                    }
                    user.setName(name);
                } else if ("gender".equals(fieldname)) {
                    user.setGender(Gender.valueOf(jp.getText()));
                } else if ("verified".equals(fieldname)) {
                    user.setVerified(jp.getCurrentToken() == JsonToken.VALUE_TRUE);
                } else {
                    throw new IllegalStateException("Unrecognized field '" + fieldname + "'!");
                }
            }
            return user;
        } finally {
            if(jp != null) {
                jp.close();
            }
        }
    }

    public void toJson(User user) throws IOException {
        JsonFactory f = new JsonFactory();
        JsonGenerator g = f.createJsonGenerator(new File("user2.json"), JsonEncoding.UTF8);
        g.writeStartObject();
        g.writeObjectFieldStart("name");
        g.writeStringField("first", user.getName().getFirst());
        g.writeStringField("last", user.getName().getLast());
        g.writeEndObject(); // for field 'name'
        g.writeStringField("gender", user.getGender().toString());
        g.writeBooleanField("verified", user.isVerified());
        g.writeEndObject();
        g.close();
    }

    public List<User> arrayJsonToObject() throws IOException {
        JsonFactory f = new JsonFactory();
        String json = "[{\"name\" : { \"first\" : \"Joe\", \"last\" : \"Smith\" }," +
           " \"gender\" : \"MALE\",  \"verified\" : false}," +
        "{ \"name\" : { \"first\" : \"Sam\", \"last\" : \"Smith\" }, \"gender\" : \"MALE\", \"verified\" : false }]";
        JsonParser jp = f.createJsonParser(json);
        jp.nextToken();
        ObjectMapper mapper = new ObjectMapper();
        List<User> userList = new ArrayList<User>();
        while (jp.nextToken() == JsonToken.START_OBJECT) {
            User user = mapper.readValue(jp, User.class);
            userList.add(user);
        }
        return userList;
    }
}
