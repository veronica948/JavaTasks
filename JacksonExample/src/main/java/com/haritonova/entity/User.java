package com.haritonova.entity;

/**
 * Created by Veronica_Haritonova on 3/11/2016.
 */
public class User {
    private Name name;
    private Gender gender;
    private boolean isVerified;

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    @Override
    public String toString() {
        return "User{" +
                "name=" + name +
                ", gender=" + gender +
                ", isVerified=" + isVerified +
                '}';
    }
}
