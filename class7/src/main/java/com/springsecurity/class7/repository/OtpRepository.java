package com.springsecurity.class7.repository;

import com.springsecurity.class7.entities.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, Integer> {
    Optional<Otp> findOtpByUsername(String username);
}
