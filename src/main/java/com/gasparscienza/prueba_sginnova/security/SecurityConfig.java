package com.gasparscienza.prueba_sginnova.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.gasparscienza.prueba_sginnova.jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> // medida de seguridad para agregar a las solicitudes post una autenticacion
                              // basada en un token csrfvalue
                csrf
                        .disable())
                .authorizeHttpRequests(authRequest -> authRequest
                        .requestMatchers("/auth/**").permitAll() // Permito todas las endpoint /auth/**
                        .requestMatchers(HttpMethod.GET).hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers(
                                "/sport/**",
                                "/characteristic/**",
                                "/city/**",
                                "/client/**",
                                "/company/**",
                                "/province/**",
                                "/subscription/**").hasAuthority("ADMIN")
                        .requestMatchers("/court/**", "/turn/**").hasAnyAuthority("ADMIN")
                        .requestMatchers("/reservation/**").hasAnyAuthority("USER", "ADMIN")
                // .anyRequest().authenticated() las demas solicito autenticacion con un login
                // por defecto
                )
                .sessionManagement(sessioManager -> sessioManager
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))// Inhabilitamos las sesiones, no
                                                                                // utilice las politicas de creacion de
                                                                                // sesiones
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
