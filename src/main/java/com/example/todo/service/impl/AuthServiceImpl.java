package com.example.todo.service.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.todo.dto.AuthDto;
import com.example.todo.entity.UserEntity;
import com.example.todo.repository.UserRepository;
import com.example.todo.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username);
    }

    @Override
    public String getToken(AuthDto authDto) {
        UserEntity user = userRepository.findByLogin(authDto.login());
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return createToken(user);
    }

    public String createToken(UserEntity user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
            return JWT.create()
                    .withIssuer("Auth api")
                    .withSubject(user.getLogin())
                    .withExpiresAt(generateExpiresAt())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private Instant generateExpiresAt() {
        return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-03:00"));
    }

    @Override
    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
            return JWT.require(algorithm)
                    .withIssuer("Auth api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return "";
        }
    }
}
