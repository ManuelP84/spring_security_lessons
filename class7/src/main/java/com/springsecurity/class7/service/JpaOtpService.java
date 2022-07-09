package com.springsecurity.class7.service;

import com.springsecurity.class7.entities.Otp;
import com.springsecurity.class7.repository.OtpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JpaOtpService {

    private final OtpRepository otpRepository;

    public Otp loadOtpByUsername(String username){
        Optional<Otp> optional = otpRepository.findOtpByUsername(username);
        return optional.orElseThrow(() -> new IllegalArgumentException("Otp not found!"));

    }

    public Otp saveOtp(Otp otp){
        return otpRepository.save(otp);
    }
}
