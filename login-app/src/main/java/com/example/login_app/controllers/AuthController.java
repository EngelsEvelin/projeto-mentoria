package com.example.login_app.controllers;

import com.example.login_app.dto.LoginRequestDTO;
import com.example.login_app.dto.RegisterRequestDTO;
import com.example.login_app.dto.ResponseDTO;
import com.example.login_app.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginRequestDTO body) {
        try {
            String token = authService.login(body);
            return ResponseEntity.ok(new ResponseDTO("Success", token));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ResponseDTO(e.getMessage(), null));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@RequestBody RegisterRequestDTO body) {
        try {
            String token = authService.register(body);
            return ResponseEntity.ok(new ResponseDTO("Success", token));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ResponseDTO(e.getMessage(), null));
        }
    }
}
