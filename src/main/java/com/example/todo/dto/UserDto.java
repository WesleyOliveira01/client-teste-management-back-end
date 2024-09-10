package com.example.todo.dto;

import com.example.todo.entity.UserEntity;
import com.example.todo.enums.UserRoles;

public record UserDto(
        String name,
        String login,
        String password,
        UserRoles role) {

    public UserDto(UserEntity user) {
        this(user.getName(), user.getLogin(), user.getPassword(), user.getRole());
    }
}
