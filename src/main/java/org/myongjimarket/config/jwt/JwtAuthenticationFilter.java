package org.myongjimarket.config.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // 1. Request Header -> 토큰 추출
        String token = jwtProvider.resolveToken(request);

        // 2. validateToken -> 토큰 유효성 검사
        if (StringUtils.hasText(token) && jwtProvider.validateToken(token))
            SecurityContextHolder.getContext().setAuthentication(jwtProvider.getAuthentication(token));

        filterChain.doFilter(request, response);
    }
}
