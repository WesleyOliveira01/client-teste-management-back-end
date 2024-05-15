package com.example.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todo.entity.MeshEntity;

@Repository
public interface MeshRepository extends JpaRepository<MeshEntity, Long> {
    MeshEntity findByName(String name);
}
