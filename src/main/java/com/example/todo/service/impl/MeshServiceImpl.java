package com.example.todo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.todo.dto.MeshDto;
import com.example.todo.entity.MeshEntity;
import com.example.todo.enums.statusEnum;
import com.example.todo.repository.MeshRepository;
import com.example.todo.service.MeshService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MeshServiceImpl implements MeshService {

    private final MeshRepository repository;

    public List<MeshDto> getAll() {
        List<MeshEntity> entities = repository.findAll();
        if (entities.isEmpty()) {
            throw new Error("Clients not found");
        }
        repository.saveAll(entities);
        return entities.stream().map(MeshDto::new).toList();
    }

    public MeshDto getByName(String name) {
        MeshEntity entity = repository.findByName(name);
        return new MeshDto(entity);
    }

    public MeshDto create(MeshDto dto) {
        if (dto == null)
            throw new Error("Body cannot be null");
        MeshEntity entity = new MeshEntity(dto);
        repository.save(entity);
        return new MeshDto(entity);
    }

    public MeshDto update(Long id, MeshDto dto) {
        MeshEntity entity = repository.findById(id).orElse(null);
        entity.setId_client(dto.getId_client());
        entity.setName(dto.getName());
        entity.setPrazoFinal(dto.getPrazoFinal());
        repository.save(entity);
        return new MeshDto(entity);
    }

    public void updateStatus(Long id, statusEnum status) {
        MeshEntity entity = repository.findById(id).orElse(null);
        entity.setStatus(status);
        repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
