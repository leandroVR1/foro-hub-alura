package com.alura.forohub.infra.security;

import com.alura.forohub.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    // Inyectamos el secreto desde application.properties por seguridad
    @Value("${api.security.secret}")
    private String secret;

    /**
     * Genera un token JWT firmado para un usuario específico.
     */
    public String generarToken(Usuario usuario) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("forohub")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(fechaExpiracion())
                    .sign(algoritmo);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error al generar el token JWT", exception);
        }
    }

    /**
     * Valida el token y extrae el 'subject' (nombre de usuario).
     * Este método será usado por el SecurityFilter en cada petición.
     */
    public String getSubject(String token) {
        if (token == null || token.isBlank()) {
            throw new RuntimeException("Token nulo o vacío");
        }
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("forohub")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido o expirado!");
        }
    }

    /**
     * Define la expiración del token (2 horas después de su creación).
     * Ajustado a la zona horaria de Colombia (-05:00).
     */
    private Instant fechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}