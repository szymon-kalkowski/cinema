package com.example.cinema.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.security.config.Customizer.withDefaults;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${okta.oauth2.issuer}")
    private String issuer;
    @Value("${okta.oauth2.client-id}")
    private String clientId;

    private static final String[] URL_WHITELIST = {
            "/",
            "/style.css",
            "/repertoire",
            "/repertoire/**",
            "/movie/**",
            "/seance/**",
            "/my-orders",
            "/logout",
            "/api/orders/**",
            "/api/orders",
            "/api/movies",
            "/api/movies/**",
            "/api/repertoire/**",
            "/api/repertoire/seances/orders/**",
            "/api/genres"
    };

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .cors(corsConfigurer -> corsConfigurer
                        .configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(URL_WHITELIST).permitAll()
                        .anyRequest().authenticated())

                .oauth2ResourceServer(oauth2 -> oauth2.jwt(withDefaults()))
                .oauth2Login(withDefaults())

                .logout(logout -> logout
                        .addLogoutHandler(logoutHandler()));

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        // CorsConfiguration configuration = new CorsConfiguration();
        // configuration.addAllowedOrigin("http://localhost:4200");
        // configuration.addAllowedHeader("*");
        // configuration.addAllowedMethod("*");
        // configuration.addAllowedMethod(HttpMethod.POST);
        // configuration.addAllowedMethod(HttpMethod.GET);
        // configuration.addAllowedMethod(HttpMethod.OPTIONS);
        // configuration.setAllowCredentials(true);

        // UrlBasedCorsConfigurationSource source = new
        // UrlBasedCorsConfigurationSource();
        // source.registerCorsConfiguration("/**", configuration);

        // return source;
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        configuration.setExposedHeaders(Arrays.asList("Access-Control-Allow-Origin"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    private LogoutHandler logoutHandler() {
        return (request, response, authentication) -> {
            try {
                String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
                response.sendRedirect(issuer + "v2/logout?client_id=" + clientId + "&returnTo=" + baseUrl);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }

}
