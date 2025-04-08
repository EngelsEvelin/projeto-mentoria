package com.example.login_app.controllers;

import com.example.login_app.domain.user.User;
import com.example.login_app.dto.LoginRequestDTO;
import com.example.login_app.dto.RegisterRequestDTO;
import com.example.login_app.infra.security.TokenService;
import com.example.login_app.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements com.example.login_app.services.AuthService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @Override
    public String login(LoginRequestDTO body) {
        User user = (User) repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(body.password(), user.getPassword())) {
            return tokenService.generateToken(user);
        }
        throw new RuntimeException("Invalid credentials");
    }

    @Override
    public String register(RegisterRequestDTO body) {
        Optional<Object> existingUser = repository.findByEmail(body.email());
        if (existingUser.isEmpty()) {
            User newUser = new User();
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setEmail(body.email());
            newUser.setName(body.name());
            repository.save(newUser);
            return tokenService.generateToken(newUser);
        }
        throw new RuntimeException("Email already in use");
    }
}
