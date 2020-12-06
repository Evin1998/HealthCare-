package com.inti.student.healthcareordering.Model;

public class User {

    private int id;
    private String username;
    private String password;
    private String contact;

    // Empty Constructor
    public User(){

    }

    public User(String username, String password, String contact) {
        this.username = username;
        this.password = password;
        this.contact = contact;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
