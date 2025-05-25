package com.fiap.gestaofrota.service;

import com.fiap.gestaofrota.entity.PatioEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PatioService {
    private final com.fiap.gestaofrota.repository.PatioRepository repository;

    public PatioService(com.fiap.gestaofrota.repository.PatioRepository repository) {
        this.repository = repository;
    }

    public java.util.List<PatioEntity> listarTodos() {
        return repository.findAll();
    }

    public PatioEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pátio com o ID " + id + " não encontrado."));
    }

    public PatioEntity salvar(PatioEntity patio) {
        return repository.save(patio);
    }

    public PatioEntity atualizar(Long id, PatioEntity atualizado) {
        PatioEntity existente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pátio com o ID " + id + " não encontrado."));

        existente.setNome(atualizado.getNome());
        existente.setEndereco(atualizado.getEndereco());
        existente.setTelefone(atualizado.getTelefone());

        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
