package com.daneshnaik.clasess_fetch;

public class Users {
    String id;
    String name;
    String profile_image;
    String email;
    String password;

    public Users(String id, String name, String profile_image, String email, String password) {
        this.id = id;
        this.name = name;
        this.profile_image = profile_image;
        this.email = email;
        this.password = password;
    }

    public Users() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
