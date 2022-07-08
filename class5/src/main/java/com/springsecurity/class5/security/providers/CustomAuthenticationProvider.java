package com.springsecurity.class5.security.providers;

import com.springsecurity.class5.security.authentication.CustomAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Value("${key}")
    private String key;

    @Override
    public Authentication authenticate(Authentication authentication) {
        String requestKey = authentication.getName();
        if (requestKey.equals(this.key)){
            return new CustomAuthentication(
                    null,
                    null,
                    null
            );

        } else {
            throw new BadCredentialsException("Bad credentials!");
        }
    }

    @Override
    public boolean supports(Class<?> authenticationType) {
        return CustomAuthentication.class.equals(authenticationType);
    }
}
