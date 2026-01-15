package Lumo.lumo_backend.global.security.jwt;

import Lumo.lumo_backend.global.security.userDetails.CustomUserDetailsService;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;

@Slf4j
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
        try{
            Jwts.parser()
                    .verifyWith((SecretKey) key)
                    .build()
                    .parseSignedClaims(accessToken);
            return true;
        }
        catch (SecurityException | MalformedJwtException e){
            log.warn("[JWTProvider-ValidateToken()] : 잘못된 토큰입니다 ", e);
        }
        catch (ExpiredJwtException e){ // 실패 응답을 통한 로그인 요청 로직
            log.warn("[JWTProvider-ValidateToken()] : 만료된 토큰입니다 ", e);
        }
        catch (UnsupportedJwtException e) {
            log.warn("[JWTProvider-ValidateToken()] : 지원되지 않는 토큰 형식입니다 ", e);
        }
        catch (IllegalArgumentException e) {
            log.warn("[JWTProvider-ValidateToken()] : JWT claims String이 비어있습니다. ", e);
        }
        return false;
    }

    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);

        Object authClaimObject = claims.get("auth") == null ? claims.get("auth") : "";
        String authoritiesString = (authClaimObject != null) ? authClaimObject.toString() : "";

        if (authoritiesString.isEmpty() || claims.get("auth") == null) {
            ///  GenerationException 으로 수정하기
            throw new RuntimeException("권한 정보가 없는 이상한 토큰입니다");
        }

        log.info("[JWTProvider - getAuthentication()] email: {}", claims.get("username", String.class));

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(claims.get("username", String.class));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private Claims parseClaims(String accessToken) {
        try{
            Claims claims = Jwts.parser()
                    .verifyWith((SecretKey) key)
                    .build()
                    .parseSignedClaims(accessToken)
                    .getPayload();

            log.info ("[JWTProvider - parseClaims()] claims 파싱 발생 -> {}, {}", claims.toString(), claims.getSubject());
            return claims;
        }
        catch (Exception e){
            ///  GenerationException 으로 수정하기
            throw new RuntimeException("파싱이 잘못되었습니다.");
        }
    }
}
