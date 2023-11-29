package com.atenea.ecoevent.models;

import java.util.Date;

public class User {

    private String username;
    private String email;
    private String password;
    private Date birthday;

    public User(String username, String email, String password, Date birthday) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

}
