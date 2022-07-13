package com.springsecurity.class6.config;

import com.springsecurity.class6.security.filter.UsernamePasswordAuthFilter;
import com.springsecurity.class6.security.provider.OtpAuthenticationProvider;
import com.springsecurity.class6.security.provider.UsernamePasswordAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ProjectConfig {

    @Autowired
    private UsernamePasswordAuthFilter usernamePasswordAuthFilter;

    @Autowired
    private OtpAuthenticationProvider otpAuthenticationProvider;

    @Autowired
    private UsernamePasswordAuthProvider usernamePasswordAuthProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.addFilterAt(usernamePasswordAuthFilter,
                BasicAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    /*
    * Use to add multiple Authentication Providers to the Authentication Manager
    * */
    AuthenticationManager authenticationManager() {
        final List<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(otpAuthenticationProvider);
        providers.add(usernamePasswordAuthProvider);
        return new ProviderManager(providers);
    }
}
