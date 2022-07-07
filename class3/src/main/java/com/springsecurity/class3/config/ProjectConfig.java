package com.springsecurity.class3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class ProjectConfig {

    @Bean
    public JdbcUserDetailsManager userDetailsService(){
        //InMemoryUserDetailsManager
        //JdbcUserDetailsManager
        return new JdbcUserDetailsManager(dataSource());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DataSource dataSource(){
        var dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/springsecurity");
        dataSource.setUsername("root");
        dataSource.setPassword("Manuel.pineda84");
        return dataSource;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.httpBasic();

        httpSecurity.csrf().disable();

        httpSecurity.authorizeRequests()
                .mvcMatchers("/user/**").permitAll()
                .anyRequest().authenticated();
        return httpSecurity.build();
    }
}
