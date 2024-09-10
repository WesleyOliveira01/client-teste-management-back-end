package com.example.todo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.todo.dto.UserDto;
import com.example.todo.entity.UserEntity;
import com.example.todo.repository.UserRepository;
import com.example.todo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthServiceImpl authService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDto save(UserDto userDto) {
        var hasPassword = passwordEncoder.encode(userDto.password());
        UserEntity user = new UserEntity(userDto);
        user.setPassword(hasPassword);
        userRepository.save(user);
        return new UserDto(user);
    }

    @Override
    public List<UserDto> getAll() {
        try {
            List<UserEntity> users = userRepository.findAll();
            return users.stream().map(UserDto::new).toList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public UserDto getByLogin(String token) {
        try {
            var login = authService.validateToken(token);
            UserEntity user = userRepository.findByLogin(login);
            return new UserDto(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public UserDto update(Long id, UserDto userDto) {
        try {
            UserEntity user = userRepository.findById(id).orElse(null);
            user.setName(userDto.name());
            user.setLogin(userDto.login());
            user.setPassword(passwordEncoder.encode(userDto.password()));
            user.setRole(userDto.role());
            userRepository.save(user);
            return new UserDto(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
