package com.fiap.gestaofrota.controller;

import com.fiap.gestaofrota.dto.MotoDTO;
import com.fiap.gestaofrota.entity.MotoEntity;
import com.fiap.gestaofrota.entity.PatioEntity;
import com.fiap.gestaofrota.mapper.MotoMapper;
import com.fiap.gestaofrota.service.MotoService;
import com.fiap.gestaofrota.service.PatioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/motos")
public class MotoController {
    private final MotoService motoService;
    private final PatioService patioService;

    public MotoController(MotoService motoService, PatioService patioService) {
        this.motoService = motoService;
        this.patioService = patioService;
    }

    @GetMapping
    public List<MotoDTO> listar(@RequestParam(required = false) String placa,
                                @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return motoService.listar(pageable, placa).map(MotoMapper::toMotoDTO).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MotoDTO> buscarPorId(@PathVariable Long id) {
        MotoEntity entity = motoService.buscarPorId(id);
        return ResponseEntity.ok(MotoMapper.toMotoDTO(entity));
    }

    @PostMapping
    public ResponseEntity<MotoDTO> criar(@RequestBody @Valid MotoDTO moto) {
        Optional<PatioEntity> patio = Optional.ofNullable(patioService.buscarPorId(moto.getPatioId()));
        if (patio.isEmpty()) return ResponseEntity.badRequest().build();
        MotoEntity entity = MotoMapper.toMotoEntity(moto, patio.get());
        return ResponseEntity.ok(MotoMapper.toMotoDTO(motoService.salvar(entity)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MotoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid MotoDTO motoDto) {
        Optional<PatioEntity> patio = Optional.ofNullable(patioService.buscarPorId(motoDto.getPatioId()));
        if (patio.isEmpty()) return ResponseEntity.badRequest().build();

        MotoEntity entityAtualizada = MotoMapper.toMotoEntity(motoDto, patio.get());
        MotoEntity entityFinal = motoService.atualizar(id, entityAtualizada);

        return ResponseEntity.ok(MotoMapper.toMotoDTO(entityFinal));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        motoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
