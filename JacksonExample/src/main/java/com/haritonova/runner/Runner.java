package com.haritonova.runner;

import com.haritonova.entity.Gender;
import com.haritonova.entity.Name;
import com.haritonova.entity.User;
import com.haritonova.service.DataBindingJsonConverter;

import java.io.IOException;

/**
 * Created by Veronica_Haritonova on 3/11/2016.
 */
public class Runner {
    public static void main(String[] args) {
        try {
            readJson();
            writeJson();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
