package com.example.login_app.dto;

public record RegisterRequestDTO (String name, String email, String password) {
    public String getEmail() {
        return "email";
    }

    public CharSequence getPassword() {
        return null;
    }
}
