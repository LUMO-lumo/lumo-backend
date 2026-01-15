package Lumo.lumo_backend.global.security.jwt;

import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.core.Authentication;
import io.jsonwebtoken.*;

import java.security.Key;
import java.util.Base64;

@RequiredArgsConstructor
public class JWTProvider {

    private final Key key;

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
