package com.example.todo.service;

import java.util.List;

import com.example.todo.dto.MeshDto;
import com.example.todo.enums.statusEnum;

public interface MeshService {

    List<MeshDto> getAll();

    MeshDto getByName(String name);

    MeshDto create(MeshDto dto);

    MeshDto update(Long id, MeshDto dto);

    void updateStatus(Long id, statusEnum status);

    void delete(Long id);
}
