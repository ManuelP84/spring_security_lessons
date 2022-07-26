package com.springsecurity.class10.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
public class ProjectConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
       httpSecurity
               .csrf()
               .disable();

        // CORS
       httpSecurity.cors(c -> {
           CorsConfigurationSource cs = r -> {
               CorsConfiguration cc = new CorsConfiguration();
               cc.setAllowedOrigins(List.of("*"));
               cc.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
               return cc;
           };
           c.configurationSource(cs);
       });

       httpSecurity
               .authorizeRequests()
               .anyRequest()
               .permitAll();

       return httpSecurity.build();
    }
}
