package com.example.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.dto.UserDto;
import com.example.todo.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    private ResponseEntity<?> getAll(@RequestHeader("Authorization") String token) {
        System.out.println(token);
        try {
            List<UserDto> users = userService.getAll();
            return ResponseEntity.ok().body(users);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            UserDto user = userService.getById(id);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    private ResponseEntity<?> save(@RequestBody UserDto userDto) {
        try {
            UserDto user = userService.save(userDto);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/findByLogin")
    private ResponseEntity<?> getByLogin(@RequestHeader("Authorization") String token) {
        try {
            UserDto user = userService.getByLogin(token);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> update(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @RequestBody UserDto userDto) {
        try {
            UserDto user = userService.update(id, userDto);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/disable")
    private ResponseEntity<?> enable(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        try {
            userService.disable(id);
            return ResponseEntity.ok().body("User disable");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
