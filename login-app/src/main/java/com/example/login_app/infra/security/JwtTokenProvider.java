package com.example.login_app.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String secretKey = "mySecretKey"; // Chave secreta para assinar o JWT

    // Método para gerar o token JWT
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();  // Recupera o nome do usuário da autenticação

        // Criação do token JWT usando a biblioteca da Auth0
        return JWT.create()
                .withSubject(username)  // Define o nome do usuário no token
                .withIssuedAt(new Date())  // Define a data de emissão
                .withExpiresAt(new Date(System.currentTimeMillis() + 86400000))  // Define a expiração do token (24 horas)
                .sign(Algorithm.HMAC512(secretKey));  // Assina o token com a chave secreta usando HMAC-SHA512
    }

    // Método para validar o token JWT
    public boolean validateToken(String token) {
        try {
            // Verifica o token e decodifica usando a chave secreta
            JWT.require(Algorithm.HMAC512(secretKey))
                    .build()
                    .verify(token);  // Se o token for válido, não lança exceções
            return true;  // O token é válido
        } catch (Exception e) {
            return false;  // Se houver qualquer exceção, o token é inválido
        }
    }

    // Método para extrair o nome do usuário do token
    public String getUsernameFromToken(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(secretKey))
                .build()
                .verify(token);  // Verifica e decodifica o token
        return decodedJWT.getSubject();  // Retorna o nome do usuário (subject) do token
    }
}
