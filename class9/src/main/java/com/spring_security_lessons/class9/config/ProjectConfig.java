package com.spring_security_lessons.class9.config;

import com.spring_security_lessons.class9.security.CsrfTokenLoggerFilter;
import com.spring_security_lessons.class9.security.CustomCsrfTokenRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;


@Configuration
public class ProjectConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .formLogin()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated();

        httpSecurity
                .csrf(c -> {
                    c.ignoringAntMatchers("/csrfdisabled/**"); // This is not often use.
                    c.csrfTokenRepository(new CustomCsrfTokenRepository());
                });

        httpSecurity
                .addFilterAfter(new CsrfTokenLoggerFilter(), CsrfFilter.class);

        return httpSecurity.build();
    }
}
