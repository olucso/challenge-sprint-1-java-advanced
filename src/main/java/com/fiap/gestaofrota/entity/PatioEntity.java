package com.fiap.gestaofrota.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "PATIO")
public class PatioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String endereco;

    @NotNull
    private Integer telefone;

    @OneToMany(mappedBy = "patio", cascade = CascadeType.ALL)
    private List<MotoEntity> motos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank String getNome() {
        return nome;
    }

    public void setNome(@NotBlank String nome) {
        this.nome = nome;
    }

    public @NotBlank String getEndereco() {
        return endereco;
    }

    public void setEndereco(@NotBlank String endereco) {
        this.endereco = endereco;
    }

    public @NotNull Integer getTelefone() {
        return telefone;
    }

    public void setTelefone(@NotNull Integer telefone) {
        this.telefone = telefone;
    }

    public List<MotoEntity> getMotos() {
        return motos;
    }

    public void setMotos(List<MotoEntity> motos) {
        this.motos = motos;
    }
}
