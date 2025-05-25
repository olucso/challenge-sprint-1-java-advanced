package com.fiap.gestaofrota.mapper;

import com.fiap.gestaofrota.dto.MotoDTO;
import com.fiap.gestaofrota.entity.MotoEntity;
import com.fiap.gestaofrota.entity.PatioEntity;
import jakarta.validation.Valid;

public class MotoMapper {
    public static MotoDTO toMotoDTO(MotoEntity entity) {
        MotoDTO dto = new MotoDTO();
        dto.setId(entity.getId());
        dto.setMarca(entity.getMarca());
        dto.setModelo(entity.getModelo());
        dto.setPlaca(entity.getPlaca());
        dto.setAno(entity.getAno());
        dto.setNumeroIot(entity.getNumeroIot());
        dto.setPatioId(entity.getPatio() != null ? entity.getPatio().getId() : null);
        return dto;
    }

    public static MotoEntity toMotoEntity(@Valid MotoDTO dto, PatioEntity patio) {
        MotoEntity entity = new MotoEntity();
        entity.setId(dto.getId());
        entity.setMarca(dto.getMarca());
        entity.setModelo(dto.getModelo());
        entity.setPlaca(dto.getPlaca());
        entity.setAno(dto.getAno());
        entity.setNumeroIot(dto.getNumeroIot());
        entity.setPatio(patio);
        return entity;
    }
}
