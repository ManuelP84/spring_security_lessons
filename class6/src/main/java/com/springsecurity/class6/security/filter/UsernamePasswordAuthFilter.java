package com.springsecurity.class6.security.filter;

import com.springsecurity.class6.entities.Otp;
import com.springsecurity.class6.security.authentication.UsernamePasswordAuthentication;
import com.springsecurity.class6.service.JpaOtpService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class UsernamePasswordAuthFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;
    private final JpaOtpService jpaOtpService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        var username = request.getHeader("username");
        var password = request.getHeader("password");
        var otp = request.getHeader("otp");

        // Step 1: username & password
        //Step 2: username & otp

        if(otp == null) {
            //Step 2
            Authentication auth = new UsernamePasswordAuthentication(username, password);
            authenticationManager.authenticate(auth);

            // Generate an Otp
            String code = String.valueOf(new Random().nextInt(9999)+1000);
            Otp newOtp = new Otp();
            newOtp.setUsername(username);
            newOtp.setOtp(code);
            response.setHeader("Otp", code);
            jpaOtpService.saveOtp(newOtp);

        }else {

            //Step 1
            Authentication auth = new UsernamePasswordAuthentication(username, otp);
            authenticationManager.authenticate(auth);

            // Generate a token
            response.setHeader("Autorization", UUID.randomUUID().toString());
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request)
            throws ServletException {
        return !request.getServletPath().equals("/login");
    }
}
