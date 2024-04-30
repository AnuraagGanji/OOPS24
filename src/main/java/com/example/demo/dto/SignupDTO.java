package com.example.demo.dto;

public class SignupDTO {
    private final String email;
    private final String name;
    private final String password;

    public SignupDTO(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
