package com.springsecurity.class4.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider
        implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // Here the authentication logic is implemented

        //If the request is authenticated a fully Authentication instance is returned

        //If the request is not authenticated you should throw AuthenticationException

        //If the Authentication is not supported by this AP -> return null

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if(userDetails != null){
            if(passwordEncoder.matches(password, userDetails.getPassword())){
                return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
            }
        }

        throw new BadCredentialsException("Error!");
    }

    @Override
    public boolean supports(Class<?> authType) {  //Type os Authentication
        return UsernamePasswordAuthenticationToken.class.equals(authType);
    }
}
