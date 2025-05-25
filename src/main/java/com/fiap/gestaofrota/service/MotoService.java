package com.fiap.gestaofrota.service;

import com.fiap.gestaofrota.entity.MotoEntity;
import com.fiap.gestaofrota.repository.MotoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class MotoService {
    private final MotoRepository repository;

    public MotoService(MotoRepository repository) {
        this.repository = repository;
    }

    @Cacheable("motos")
    public Page<MotoEntity> listar(Pageable pageable, String placa) {
        if (placa != null && !placa.isEmpty()) {
            return repository.findByPlacaContainingIgnoreCase(placa, pageable);
        }
        return repository.findAll(pageable);
    }

    public MotoEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Moto com o código " + id + " não encontrada."));
    }

    @CacheEvict(value = "motos", allEntries = true)
    public MotoEntity salvar(MotoEntity moto) {
        return repository.save(moto);
    }


    @CacheEvict(value = "motos", allEntries = true)
    public MotoEntity atualizar(Long id, MotoEntity motoAtualizada) {
        MotoEntity existente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Moto com o ID " + id + " não encontrada."));

        existente.setMarca(motoAtualizada.getMarca());
        existente.setModelo(motoAtualizada.getModelo());
        existente.setPlaca(motoAtualizada.getPlaca());
        existente.setAno(motoAtualizada.getAno());
        existente.setNumeroIot(motoAtualizada.getNumeroIot());
        existente.setPatio(motoAtualizada.getPatio());

        return repository.save(existente);
    }

    @CacheEvict(value = "motos", allEntries = true)
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
