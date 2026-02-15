package Lumo.lumo_backend.global.security.filter;

import Lumo.lumo_backend.global.security.jwt.JWTProvider;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTProvider jwtProvider;

    private String resolveAccessToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7).trim(); // 앞 뒤 공백 제거, "Bearer ~~~" 형식으로 통일
        }
        return null;
    }

    private String resolveRefreshToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }

        return Arrays.stream(cookies)
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(null);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = resolveAccessToken(request);

        /// jwtProvider 에서 인증 조회 + 토큰 검증이 필요!
        try{
            if(accessToken != null && jwtProvider.validateToken(accessToken)){ // 비었거나, 올바르지 않거나
                Authentication authentication = jwtProvider.getAuthentication(accessToken);
                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    log.info("유효하지 않은 토큰입니다, 인증 정보를 저장하지 않습니다.");
                }
            }
            else {
                log.warn("토큰이 비어있는 요청입니다");
//            throw new GeneralException(ErrorCode.AUTH_UNAUTHORIZED);
            }
        }
        catch (ExpiredJwtException e){
            log.info("[JWTAuthenticationFilter] - AT expired, attempting to refresh token");
        }
        catch(JwtException | IllegalArgumentException e){
            log.info("[JWTAuthenticationFilter] - Invalid Refresh Token! ");
        }

        filterChain.doFilter(request, response);
    }
}
