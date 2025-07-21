package com.edwin.forohub.seguridad;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY = "tu_clave_jwt_ultra_secreta"; // Cámbiala por una clave segura real
    private static final long EXPIRATION_MS = 86400000; // 1 día

    public String generateToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public String getSubject(String token) {
        DecodedJWT decoded = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .build()
                .verify(token);
        return decoded.getSubject();
    }
}
