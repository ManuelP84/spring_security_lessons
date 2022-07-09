package com.springsecurity.class6.security.provider;

import com.springsecurity.class6.entities.Otp;
import com.springsecurity.class6.security.authentication.OtpAuthentication;
import com.springsecurity.class6.service.JpaOtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OtpAuthenticationProvider implements AuthenticationProvider {

    private final JpaOtpService jpaOtpService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String otp = (String)authentication.getCredentials();
        Otp o = jpaOtpService.loadOtpByUsername(username);

        if(o != null){
            return new OtpAuthentication(username, otp, List.of(() -> "read"));
        }
        throw new BadCredentialsException("Bad credentials!");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return OtpAuthenticationProvider.class.equals(aClass);
    }
}