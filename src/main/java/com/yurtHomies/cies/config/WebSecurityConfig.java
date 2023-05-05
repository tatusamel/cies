package com.yurtHomies.cies.config;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Resource
    UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        return http
                .csrf( csrf -> csrf.disable() )
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/").permitAll();
                    auth.requestMatchers("/auth/**").permitAll();
                    try {
                        auth.requestMatchers("auth/login").permitAll()
                        .and().rememberMe().tokenValiditySeconds(2592000);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }


                })
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
