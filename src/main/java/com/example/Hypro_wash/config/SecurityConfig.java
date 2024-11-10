package com.example.Hypro_wash.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public interface SecurityConfig {
    void configure(AuthenticationManagerBuilder auth) throws Exception;

    @Bean
    AuthenticationManager authenticationManagerBean() throws Exception;

    void configure(HttpSecurity httpSecurity) throws Exception;
}
