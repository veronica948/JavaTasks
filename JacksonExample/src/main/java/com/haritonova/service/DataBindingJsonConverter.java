package com.haritonova.service;

import com.haritonova.entity.User;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * Created by Veronica_Haritonova on 3/11/2016.
 */
public class DataBindingJsonConverter {
    public User toObject(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(new File("src" + File.separator + "main" +
                File.separator + "resources" + File.separator + filename), User.class);
        return user;
    }

    public void toJson(User user) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("src" + File.separator + "main" +
                File.separator + "resources" + File.separator  + "user-modified.json"), user);
    }
}
