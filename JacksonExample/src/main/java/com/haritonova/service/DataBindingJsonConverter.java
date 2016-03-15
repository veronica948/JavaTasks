package com.haritonova.service;

import com.haritonova.entity.User;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Veronica_Haritonova on 3/11/2016.
 */
public class DataBindingJsonConverter {
    private static String ROOT_PATH = "src" + File.separator + "main" +
            File.separator + "resources" + File.separator;
    public User toObject(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(new File(ROOT_PATH + filename), User.class);
        return user;
    }

    public void toJson(User user) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(ROOT_PATH + "user-modified.json"), user);
    }

    public Map<String, Object> toMap(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> result = mapper.readValue(new File(ROOT_PATH + filename), Map.class);
        return result;
    }

    public void writeFromMap(Map<String,Object> userData) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(ROOT_PATH + "user-modified1.json"), userData);
    }

    public List<User> readArray(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<User> userList = mapper.readValue(new File(ROOT_PATH + filename), mapper.getTypeFactory().constructCollectionType(List.class, User.class));
        return userList;
    }
}
