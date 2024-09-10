package com.example.todo.service;

import java.util.List;

import com.example.todo.dto.UserDto;

public interface UserService {
    UserDto save(UserDto userDto);

    List<UserDto> getAll();

    UserDto getByLogin(String token);

    UserDto update(Long id, UserDto userDto);
}
