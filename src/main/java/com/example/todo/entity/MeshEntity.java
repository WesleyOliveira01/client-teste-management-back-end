package com.example.todo.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.example.todo.dto.MeshDto;
import com.example.todo.enums.statusEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mesh")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeshEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long id_client;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Date prazoFinal;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private statusEnum status;

    public MeshEntity(MeshDto dto) {
        BeanUtils.copyProperties(dto, this);
    }
}
