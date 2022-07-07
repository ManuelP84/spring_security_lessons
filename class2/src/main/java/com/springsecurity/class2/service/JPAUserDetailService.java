package com.springsecurity.class2.service;

import com.springsecurity.class2.entity.SecurityUser;
import com.springsecurity.class2.entity.User;
import com.springsecurity.class2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;


public class JPAUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository
                .findUserByUsername(username);
        User u = user.orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        return new SecurityUser(u);
    }
}
