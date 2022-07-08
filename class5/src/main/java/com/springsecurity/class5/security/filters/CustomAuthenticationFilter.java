package com.springsecurity.class5.security.filters;

import com.springsecurity.class5.security.authentication.CustomAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    public AuthenticationManager authenticationManager;

    @Override
    public void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws IOException, ServletException {

        String authorization = request.getHeader("Authorization");

        CustomAuthentication authentication = new CustomAuthentication(authorization,null);
        try{
            Authentication result = authenticationManager.authenticate(authentication);

            if (result.isAuthenticated()){
                SecurityContextHolder.getContext().setAuthentication(result);
                filterChain.doFilter(request, response);
            }
            //response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }  catch (AuthenticationException e){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
