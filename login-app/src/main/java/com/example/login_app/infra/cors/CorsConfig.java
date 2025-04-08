package com.example.login_app.infra.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permite todas as URLs do backend
                .allowedOrigins("http://localhost:3000") // Permite acesso apenas de localhost:3000 (mude para o domínio do seu front-end)
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Quais métodos HTTP são permitidos
                .allowedHeaders("*") // Permite todos os cabeçalhos
                .allowCredentials(true); // Permite enviar cookies/credenciais (se necessário)
    }
}
