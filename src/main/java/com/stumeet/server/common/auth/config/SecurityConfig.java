package com.stumeet.server.common.auth.config;

import com.stumeet.server.common.auth.filter.JwtAuthenticationFilter;
import com.stumeet.server.common.auth.filter.OAuthAuthenticationFilter;
import com.stumeet.server.common.auth.handler.InvalidAuthenticationFailureHandler;
import com.stumeet.server.common.auth.handler.JwtLogoutHandler;
import com.stumeet.server.common.auth.handler.JwtLogoutSuccessHandler;
import com.stumeet.server.common.auth.handler.OAuthAuthenticationSuccessHandler;
import com.stumeet.server.common.auth.service.JwtAuthenticationService;
import com.stumeet.server.common.auth.service.OAuthAuthenticationProvider;
import com.stumeet.server.common.token.JwtTokenProvider;
import com.stumeet.server.member.domain.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final InvalidAuthenticationFailureHandler invalidAuthenticationFailureHandler;
    private final OAuthAuthenticationProvider authenticationProvider;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAuthenticationService jwtAuthenticationService;
    private final OAuthAuthenticationSuccessHandler oAuthAuthenticationSuccessHandler;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AccessDeniedHandler accessDeniedHandler;
    private final JwtLogoutHandler jwtLogoutHandler;
    private final JwtLogoutSuccessHandler jwtLogoutSuccessHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin(AbstractHttpConfigurer::disable);
        http.rememberMe(AbstractHttpConfigurer::disable);
        http.httpBasic(AbstractHttpConfigurer::disable);
        http.logout(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable);

        http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authenticationProvider(authenticationProvider);

        http.addFilterBefore(new OAuthAuthenticationFilter(invalidAuthenticationFailureHandler, authenticationManager, oAuthAuthenticationSuccessHandler), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, jwtAuthenticationService), UsernamePasswordAuthenticationFilter.class);

        http.logout(logout -> {
            logout.addLogoutHandler(jwtLogoutHandler);
            logout.logoutSuccessHandler(jwtLogoutSuccessHandler);
            logout.logoutRequestMatcher(new AntPathRequestMatcher("/api/v1/logout", HttpMethod.POST.name()));
        });

        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers(HttpMethod.POST, "/api/v1/oauth").permitAll();
            auth.requestMatchers(HttpMethod.POST, "/api/v1/tokens").permitAll();
            auth.requestMatchers(HttpMethod.POST, "/api/v1/dev/**").permitAll();
            auth.requestMatchers("/h2-console/**").permitAll();
            auth.requestMatchers("/docs/**").permitAll();
            auth.requestMatchers("/api/v1/signup").hasAnyAuthority(UserRole.FIRST_LOGIN.toString());
            auth.requestMatchers("/api/v1/professions").hasAnyAuthority(UserRole.FIRST_LOGIN.toString(), UserRole.MEMBER.toString());
            auth.requestMatchers("/api/v1/validate-nickname").hasAnyAuthority(UserRole.FIRST_LOGIN.toString(), UserRole.MEMBER.toString());
            auth.anyRequest().hasAnyAuthority(UserRole.MEMBER.toString());
        });

        http.securityContext(securityContext -> securityContext.securityContextRepository(securityContextRepository()));

        http.exceptionHandling(handler -> {
            handler.authenticationEntryPoint(authenticationEntryPoint);
            handler.accessDeniedHandler(accessDeniedHandler);
        });

        return http.build();
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new RequestAttributeSecurityContextRepository();
    }
}
