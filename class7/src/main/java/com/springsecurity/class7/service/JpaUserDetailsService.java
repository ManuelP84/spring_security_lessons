package com.springsecurity.class7.service;

import com.springsecurity.class7.entities.User;
import com.springsecurity.class7.repository.UserRepository;
import com.springsecurity.class7.security.model.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        Optional<User> optional =  userRepository.findUserByUsername(username);
        User user = optional.orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        return new SecurityUser(user);
    }
}
