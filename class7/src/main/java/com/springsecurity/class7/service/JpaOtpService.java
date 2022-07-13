package com.springsecurity.class7.service;

import com.springsecurity.class7.entities.Otp;
import com.springsecurity.class7.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JpaOtpService {

    @Autowired
    private OtpRepository otpRepository;

    public Otp loadOtpByUsername(String username){
        Optional<Otp> optional = otpRepository.findOtpByUsername(username);
        return optional.orElseThrow(() -> new IllegalArgumentException("Otp not found!"));

    }

    public Otp saveOtp(Otp otp){
        return otpRepository.save(otp);
    }
}
