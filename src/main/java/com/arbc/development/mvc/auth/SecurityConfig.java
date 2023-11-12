package com.arbc.development.mvc.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.arbc.development.mvc.auth.filters.JwtAuthFilter;

@Configuration
public class SecurityConfig {
    
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests()
            .requestMatchers(HttpMethod.GET, "/users").permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilter(new JwtAuthFilter(authenticationConfiguration.getAuthenticationManager()))
            .csrf(config -> config.disable())
            .sessionManagement(managment -> managment.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .build();
    }
}
