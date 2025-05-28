package com.virellarent.backend.config;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;

@Data
@Configuration
@ConfigurationProperties(prefix = "application.jwt")
public class JwtConfig {

    private String secretKey;
    private String tokenPrefix;
    private Integer tokenExpirationAfterDays; // En días
    private Integer refreshTokenExpirationAfterDays; // En días

    // Métodos para obtener la expiración en milisegundos
    public long getTokenExpirationInMillis() {
        return tokenExpirationAfterDays * 24L * 60 * 60 * 1000;
    }

    public long getRefreshTokenExpirationInMillis() {
        return refreshTokenExpirationAfterDays * 24L * 60 * 60 * 1000;
    }

    @Bean
    SecretKey secretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }


}
