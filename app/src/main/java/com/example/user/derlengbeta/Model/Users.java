package com.example.user.derlengbeta.Model;

/**
 * Created by user on 24-Nov-18.
 */

public class Users {
    private String Name;
    private String Password;
    private String Email;

    public Users(){};

    public Users(String name, String password, String email) {
        Name = name;
        Password = password;
        Email = email;
    }

    public Users(String name, String password) {
        Name = name;
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
