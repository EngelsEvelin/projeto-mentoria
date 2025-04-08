package com.example.login_app.services;

import com.example.login_app.domain.user.User;
import com.example.login_app.dto.LoginRequestDTO;
import com.example.login_app.dto.RegisterRequestDTO;

public interface AuthService {
    String login(LoginRequestDTO body);
    String register(RegisterRequestDTO body);
}
