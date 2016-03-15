package com.haritonova.service;

import com.haritonova.entity.User;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;

import java.io.File;
import java.io.IOException;

/**
 * Created by Veronica_Haritonova on 3/14/2016.
 */
public class TreeModelConverter {
    private static String ROOT_PATH = "src" + File.separator + "main" +
            File.separator + "resources" + File.separator;
    public String getObjectField(String filename, String field) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(new File(ROOT_PATH + filename));
        JsonNode node = rootNode.path(field);
        return node.getTextValue();
    }

    public void toJson(User user) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode userRoot = mapper.createObjectNode();
        ObjectNode nameOb = userRoot.putObject("name");
        nameOb.put("first", user.getName().getFirst());
        nameOb.put("last", user.getName().getLast());
        userRoot.put("gender", user.getGender().toString());
        userRoot.put("verified", user.isVerified());
        mapper.writeValue(new File(ROOT_PATH + "user-modified2.json"), userRoot);
    }
}
