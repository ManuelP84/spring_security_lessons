package com.springsecurity.class7.security.filter;

import com.springsecurity.class7.TokenManager.TokenManager;
import com.springsecurity.class7.entities.Otp;
import com.springsecurity.class7.security.authentication.OtpAuthentication;
import com.springsecurity.class7.security.authentication.UsernamePasswordAuthentication;
import com.springsecurity.class7.service.JpaOtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

@Component
public class UsernamePasswordAuthFilter extends OncePerRequestFilter {

    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;
    @Autowired
    private JpaOtpService jpaOtpService;
    @Autowired
    private TokenManager tokenManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        var username = request.getHeader("username");
        var password = request.getHeader("password");
        var otp = request.getHeader("otp");

        //Step 1: username & password
        //Step 2: username & otp

        if(otp == null) {
            //Step 1
            Authentication auth = new UsernamePasswordAuthentication(username, password);
            auth = authenticationManager.authenticate(auth);

            // Generate an Otp
            String code = String.valueOf(new Random().nextInt(9999)+1000);
            Otp newOtp = new Otp();
            newOtp.setUsername(username);
            newOtp.setOtp(code);
            response.setHeader("Otp", code);
            jpaOtpService.saveOtp(newOtp);

        }else {

            //Step 2
            Authentication auth = new OtpAuthentication(username, otp);
            auth = authenticationManager.authenticate(auth);

            // Generate a token
            var token = UUID.randomUUID().toString();
            tokenManager.add(token); // Normally is stored in a database
            response.setHeader("Authorization", token);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request)
            throws ServletException {
        return !request.getServletPath().equals("/login");
    }
}
