package com.springsecurity.class6.config;

import com.springsecurity.class6.security.filter.UsernamePasswordAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class ProjectConfig {


    private final UsernamePasswordAuthFilter usernamePasswordAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.addFilterAt(usernamePasswordAuthFilter,
                BasicAuthenticationFilter.class);
        return httpSecurity.build();

    }
}
