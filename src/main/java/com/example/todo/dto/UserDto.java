package com.example.todo.dto;

import com.example.todo.entity.UserEntity;
import com.example.todo.enums.UserRoles;

public record UserDto(
        Long id,
        String name,
        String login,
        String password,
        UserRoles role,
        boolean enabled) {

    public UserDto(UserEntity user) {
        this(user.getId(), user.getName(), user.getLogin(), user.getPassword(), user.getRole(), user.isEnabled());
    }
}
