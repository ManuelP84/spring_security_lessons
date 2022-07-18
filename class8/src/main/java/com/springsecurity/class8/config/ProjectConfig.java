package com.springsecurity.class8.config;

import com.springsecurity.class8.security.filter.TokenAuthFilter;
import com.springsecurity.class8.security.filter.UsernamePasswordAuthFilter;
import com.springsecurity.class8.security.provider.OtpAuthenticationProvider;
import com.springsecurity.class8.security.provider.TokenAuthProvider;
import com.springsecurity.class8.security.provider.UsernamePasswordAuthProvider;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableAsync
public class ProjectConfig {

    @Autowired
    private UsernamePasswordAuthFilter usernamePasswordAuthFilter;
    @Autowired
    private TokenAuthFilter           tokenAuthFilter;
    @Autowired
    private OtpAuthenticationProvider    otpAuthenticationProvider;
    @Autowired
    private UsernamePasswordAuthProvider usernamePasswordAuthProvider;
    @Autowired
    private TokenAuthProvider tokenAuthProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.addFilterAt(usernamePasswordAuthFilter,
                BasicAuthenticationFilter.class)
                .addFilterAt(tokenAuthFilter, BasicAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
        /*
         * Use to add multiple Authentication Providers to the Authentication Manager
         * */
    public AuthenticationManager authenticationManager() {
        final List<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(otpAuthenticationProvider);
        providers.add(usernamePasswordAuthProvider);
        providers.add(tokenAuthProvider);

        return new ProviderManager(providers);
    }

    @Bean
    public InitializingBean initializingBean(){
        return () -> {
            SecurityContextHolder.setStrategyName(
                    SecurityContextHolder.MODE_INHERITABLETHREADLOCAL
            );
        };
    }


}
