package Lumo.lumo_backend.global.security.filter;

import Lumo.lumo_backend.global.security.jwt.JWTProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTProvider jwtProvider;

    private String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer: ")) {
            return bearerToken.substring(8);
        }
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = resolveToken(request);

        /// jwtProvider 에서 인증 조회 + 토큰 검증이 필요!
        if(accessToken != null && jwtProvider.validateToken(accessToken)){
            // 비었거나, 올바르지 않거나
            Authentication authentication = jwtProvider.getAuthentication(accessToken);
            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                log.info("유효하지 않은 토큰입니다, 인증 정보를 저장하지 않습니다.");
            }
        }
        else {
            log.info("비로그인 사용자 요청입니다.");
        }
        filterChain.doFilter(request, response);


    }
}
