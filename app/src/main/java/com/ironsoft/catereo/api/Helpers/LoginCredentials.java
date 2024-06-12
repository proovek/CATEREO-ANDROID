package com.ironsoft.catereo.api.Helpers;

public class LoginCredentials {
    private String username;
    private String password;

    // Konstruktor
    public LoginCredentials(String username, String password) {
        this.username = username;
        this.password = password;
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

    // Gettery i settery
    // ...
}