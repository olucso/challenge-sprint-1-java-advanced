package com.fiap.gestaofrota.controller;

import com.fiap.gestaofrota.dto.PatioDTO;
import com.fiap.gestaofrota.entity.PatioEntity;
import com.fiap.gestaofrota.mapper.PatioMapper;
import com.fiap.gestaofrota.service.PatioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/patios")
public class PatioController {
    private final PatioService patioService;

    public PatioController(PatioService patioService) {
        this.patioService = patioService;
    }

    @GetMapping
    public List<PatioDTO> listar() {
        return patioService.listarTodos().stream().map(PatioMapper::toPatioDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatioDTO> buscarPorId(@PathVariable Long id) {
        PatioEntity entity = patioService.buscarPorId(id);
        return ResponseEntity.ok(PatioMapper.toPatioDTO(entity));
    }

    @PostMapping
    public ResponseEntity<PatioDTO> criar(@RequestBody @Valid PatioDTO patio) {
        PatioEntity entity = PatioMapper.toPatioEntity(patio);
        return ResponseEntity.ok(PatioMapper.toPatioDTO(patioService.salvar(entity)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatioDTO> atualizar(@PathVariable Long id, @RequestBody @Valid PatioDTO patioDTO) {
        PatioEntity atualizado = PatioMapper.toPatioEntity(patioDTO);
        PatioEntity atualizadoEntity = patioService.atualizar(id, atualizado);
        return ResponseEntity.ok(PatioMapper.toPatioDTO(atualizadoEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        patioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
