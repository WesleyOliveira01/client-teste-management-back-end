package com.example.todo.dto;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.example.todo.entity.MeshEntity;
import com.example.todo.enums.statusEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MeshDto {

    private Long id;

    private Long id_client;

    private String name;

    private Date prazoFinal;

    private statusEnum status;

    public MeshDto(MeshEntity entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
