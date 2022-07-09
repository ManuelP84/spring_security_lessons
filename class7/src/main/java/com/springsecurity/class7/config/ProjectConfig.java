package com.springsecurity.class7.config;

import com.springsecurity.class7.security.filter.TokenAuthFilter;
import com.springsecurity.class7.security.filter.UsernamePasswordAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class ProjectConfig {


    private final UsernamePasswordAuthFilter usernamePasswordAuthFilter;
    private final TokenAuthFilter tokenAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.addFilterAt(usernamePasswordAuthFilter,
                BasicAuthenticationFilter.class)
                .addFilterAt(tokenAuthFilter, BasicAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
