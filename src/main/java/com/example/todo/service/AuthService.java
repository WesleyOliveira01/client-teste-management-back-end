package com.example.todo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.todo.dto.AuthDto;

public interface AuthService extends UserDetailsService {
    String getToken(AuthDto authDto);
    String validateToken(String token);
}
