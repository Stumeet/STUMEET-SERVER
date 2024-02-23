package com.stumeet.server.common.auth.filter;

import com.stumeet.server.common.auth.model.AuthenticationHeader;
import com.stumeet.server.common.auth.service.JwtAuthenticationService;
import com.stumeet.server.common.token.JwtTokenProvider;
import com.stumeet.server.common.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAuthenticationService jwtAuthenticationService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context.getAuthentication() == null) {
            String token = JwtUtil.resolveToken(request.getHeader(AuthenticationHeader.ACCESS_TOKEN.getName()));

            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication auth = jwtAuthenticationService.getAuthentication(token);
                context.setAuthentication(auth);
            }
        }

        filterChain.doFilter(request, response);
    }

}
