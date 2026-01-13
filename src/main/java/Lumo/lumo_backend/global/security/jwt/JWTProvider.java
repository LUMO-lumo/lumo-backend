package Lumo.lumo_backend.global.security.jwt;

import org.springframework.security.core.Authentication;

public class JWTProvider {
    public boolean validateToken(String accessToken) {
        return false;
    }

    public Authentication getAuthentication(String accessToken) {
        return null;
    }
}
