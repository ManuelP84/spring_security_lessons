package com.springsecurity.class7.TokenManager;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class TokenManager {

    private Set<String> tokens = new HashSet<>();

    public void add(String token){
        tokens.add(token);
    }

    public Boolean contains(String token){
        return tokens.contains(token);
    }
}
