package com.springsecurity.class7.security.provider;

import com.springsecurity.class7.TokenManager.TokenManager;
import com.springsecurity.class7.security.authentication.TokenAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenAuthProvider implements AuthenticationProvider {

    private final TokenManager tokenManager;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String) authentication.getName();
        Boolean exists = tokenManager.contains(token);
        if(exists){
            return new TokenAuthentication(token, null, null);
        }
        throw new BadCredentialsException("Bad credentials!");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return TokenAuthProvider.class.equals(aClass);
    }
}
