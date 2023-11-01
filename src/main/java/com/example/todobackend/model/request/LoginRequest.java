package com.example.todobackend.model.request;

public class LoginRequest {
    private String emailAddress;
    private String password;

    public String getEmail() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }
}
