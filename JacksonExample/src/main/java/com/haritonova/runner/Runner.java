package com.haritonova.runner;

import com.haritonova.entity.Gender;
import com.haritonova.entity.Name;
import com.haritonova.entity.User;
import com.haritonova.service.DataBindingJsonConverter;
import com.haritonova.service.StreamingJsonConverter;
import com.haritonova.service.TreeModelConverter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Veronica_Haritonova on 3/11/2016.
 */
public class Runner {
    public static void main(String[] args) {
        try {
            //dataBindingTest();
            //treeModelTest();
            //streamingTest();
            dataBindingTest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void dataBindingTest() throws IOException{
        readJson();
        writeJson();
        readToMapJson();
        writeFromMap();
        dataBindingReadArray();
    }

    public static void treeModelTest() throws IOException{
        readAsTree();
        writeAsTree();
    }

    public static void streamingTest() throws IOException{
        readAsStream();
        writeAsStream();
        readListAsStream();
    }

    public static void readJson() throws IOException {
        DataBindingJsonConverter converter = new DataBindingJsonConverter();
        User user = converter.toObject("user.json");
        System.out.println(user);
    }

    public static void writeJson() throws IOException {
        DataBindingJsonConverter converter = new DataBindingJsonConverter();
        User newUser = new User();
        Name name = new Name();
        name.setFirst("Veronica");
        name.setLast("Muller");
        newUser.setName(name);
        newUser.setGender(Gender.FEMALE);
        newUser.setVerified(true);
        converter.toJson(newUser);
    }

    public static void readToMapJson() throws IOException {
        DataBindingJsonConverter converter = new DataBindingJsonConverter();
        Map<String, Object> map = converter.toMap("user.json");
        System.out.println(map);
    }

    public static void writeFromMap() throws IOException {
        Map<String,Object> userData = new HashMap<String,Object>();
        Map<String,String> nameStruct = new HashMap<String,String>();
        nameStruct.put("first", "Joe");
        nameStruct.put("last", "Smith");
        userData.put("name", nameStruct);
        userData.put("gender", Gender.MALE);
        userData.put("verified", Boolean.FALSE);
        DataBindingJsonConverter converter = new DataBindingJsonConverter();
        converter.writeFromMap(userData);
    }

    public static void readAsTree() throws IOException {
        TreeModelConverter converter = new TreeModelConverter();
        String gender = converter.getObjectField("user.json", "gender");
        System.out.println(gender);
    }

    public static void writeAsTree() throws IOException {
        TreeModelConverter converter = new TreeModelConverter();
        User newUser = new User();
        Name name = new Name();
        name.setFirst("Ann");
        name.setLast("Muller");
        newUser.setName(name);
        newUser.setGender(Gender.FEMALE);
        newUser.setVerified(true);
        converter.toJson(newUser);
    }
    public static void readAsStream() throws IOException {
        StreamingJsonConverter converter = new StreamingJsonConverter();
        User user = converter.toObject("user.json");
        System.out.println(user);
    }

    public static void writeAsStream() throws IOException {
        StreamingJsonConverter converter = new StreamingJsonConverter();
        User newUser = new User();
        Name name = new Name();
        name.setFirst("Ann");
        name.setLast("Schneider");
        newUser.setName(name);
        newUser.setGender(Gender.FEMALE);
        newUser.setVerified(true);
        converter.toJson(newUser);
    }

    public static void readListAsStream() throws IOException {
        StreamingJsonConverter converter = new StreamingJsonConverter();
        List<User> userList = converter.arrayJsonToObject();
        System.out.println(userList);
    }

    public static void dataBindingReadArray() throws IOException {
        DataBindingJsonConverter converter = new DataBindingJsonConverter();
        List<User> userList = converter.readArray("user-list.json");
        System.out.println(userList);
    }
}
