package Lumo.lumo_backend.global.security.jwt;

import Lumo.lumo_backend.global.security.userDetails.CustomUserDetailsService;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;

@Component
public class JWTProvider {

    private final Key key;
    private final CustomUserDetailsService customUserDetailsService;

    public JWTProvider(@Value("${jwt.secret.key}") String key, CustomUserDetailsService customUserDetailsService) {
        byte[] encodeKey = Base64.getEncoder().encode(key.getBytes());
        this.key = Keys.hmacShaKeyFor(encodeKey);
        this.customUserDetailsService = customUserDetailsService; // 커스텀 UserDetailsService 를 통한 DB 조회
    }

    public boolean validateToken(String accessToken) {
        return false;
    }

    public Authentication getAuthentication(String accessToken) {
        return null;
    }
}
