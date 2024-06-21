package com.arbc.development.mvc.auth;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.Arrays;

import org.keycloak.adapters.authorization.PolicyEnforcer;
import org.keycloak.adapters.authorization.integration.jakarta.ServletPolicyEnforcerFilter;
import org.keycloak.adapters.authorization.spi.ConfigurationResolver;
import org.keycloak.representations.adapters.config.PolicyEnforcerConfig;
import org.keycloak.util.JsonSerialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.arbc.development.mvc.auth.filters.JwtAuthFilter;
import com.arbc.development.mvc.auth.filters.JwtValidFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    String jwkSetUri;

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*@Bean
    AuthenticationManager authenticationManager() throws Exception
    {
        return authenticationConfiguration.getAuthenticationManager();
    }*/

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        /* return http.authorizeHttpRequests()
            .requestMatchers(HttpMethod.GET, "/users", "/hotels", "/hotels/{city}", "/cities", "/flights", "/flights/{city}", "/packages","/packages/date/{date}","/packages/price/{priceInitial}/{priceFinal}", "/packages/city/{city}").permitAll()
            .requestMatchers(HttpMethod.POST,"/peak/reportSale", "/users").permitAll()
            .requestMatchers(HttpMethod.GET, "/users/{id}").hasAnyRole("USER", "ADMIN")
            .requestMatchers(HttpMethod.POST, "/packages").hasAnyRole("USER","ADMIN")
            .requestMatchers(HttpMethod.GET, "/peak", "/off").hasRole("ADMIN")
            //.requestMatchers(HttpMethod.POST, ).hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/packages/{id}").hasAnyRole("USER", "ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/users/{id}").hasRole("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/packages").hasAnyRole("USER", "ADMIN")
            .requestMatchers(HttpMethod.PUT, "/users/{id}").hasRole("ADMIN")
            .anyRequest().authenticated()
            .and()
            .addFilter(new JwtAuthFilter(authenticationConfiguration.getAuthenticationManager()))
            .addFilter(new JwtValidFilter(authenticationConfiguration.getAuthenticationManager()))
            .csrf(config -> config.disable())
            .oauth2ResourceServer(oauth -> {
                oauth.jwt(jwt -> {});
            })
            .sessionManagement(managment -> managment.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .build(); */

            return httpSecurity
                    .csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(http -> http.anyRequest().authenticated())
                    .oauth2ResourceServer(oauth -> {
                        oauth.jwt(jwt -> {});
                    })
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .build();
    }

    private ServletPolicyEnforcerFilter createPolicyEnforcerFilter() {
        PolicyEnforcerConfig config;

        try {
            config = JsonSerialization.readValue(getClass().getResourceAsStream("/policy-enforcer.json"), PolicyEnforcerConfig.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ServletPolicyEnforcerFilter(new ConfigurationResolver() {
            @Override
            public PolicyEnforcerConfig resolve(org.keycloak.adapters.authorization.spi.HttpRequest request) {
                return config;
            }
        });
    }

    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(this.jwkSetUri).build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:5173", "https://adrianbedon.github.io/"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Bean
    FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource()));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
