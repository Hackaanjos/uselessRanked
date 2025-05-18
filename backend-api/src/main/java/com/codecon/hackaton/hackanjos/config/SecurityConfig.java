package com.codecon.hackaton.hackanjos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private static final String FRONTEND_URL = "http://localhost:4200";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        requestCache.setMatchingRequestParameterName(null);

        http
            .cors().and()
            .csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/oauth2/**", "/login/**", "/swagger-ui/**", "/api-docs/**").permitAll()
                .requestMatchers("/", "/error", "/api/auth/**", "/api/error/**", "/api/ranking/**").permitAll()
                .anyRequest().authenticated()
            )
            .exceptionHandling(exception -> exception
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.sendRedirect(FRONTEND_URL + "/error?message=Acesso negado");
                })
                .authenticationEntryPoint((request, response, authException) -> {
                    response.sendRedirect(FRONTEND_URL + "/error?message=Não autorizado");
                })
                .defaultAuthenticationEntryPointFor(
                        (request, response, authException) -> response.sendRedirect(FRONTEND_URL + "/error?message=Não encontrado"),
                        request -> true
                )
            )
            .oauth2Login(oauth2 -> oauth2
                .defaultSuccessUrl("/api/auth/success", true)
            )
            .requestCache(cache -> cache
                .requestCache(requestCache)
            )
            .headers().frameOptions().disable();

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(FRONTEND_URL));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
