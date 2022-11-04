package com.example.sudoku;

public class User {
    private String username;
    private String email;
    private String id;
    private static final User user = new User();

    public static User getInstance() {
        return user;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(String id) {
        this.id = id;
    }
}
