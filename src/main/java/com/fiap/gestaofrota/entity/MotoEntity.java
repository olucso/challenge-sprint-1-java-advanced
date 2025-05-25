package com.fiap.gestaofrota.entity;

import com.fiap.gestaofrota.enums.Marcas;
import com.fiap.gestaofrota.enums.Modelos;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "MOTO")
public class MotoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Marcas marca;

    @Enumerated(EnumType.STRING)
    private Modelos modelo;

    @Column(unique = true, nullable = false)
    @NotBlank
    private String placa;

    @Min(2000)
    private Integer ano;

    @Column(name = "numero_iot", unique = true)
    private Long numeroIot;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patio_id", nullable = false)
    private PatioEntity patio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Marcas getMarca() {
        return marca;
    }

    public void setMarca(Marcas marca) {
        this.marca = marca;
    }

    public Modelos getModelo() {
        return modelo;
    }

    public void setModelo(Modelos modelo) {
        this.modelo = modelo;
    }

    public @NotBlank String getPlaca() {
        return placa;
    }

    public void setPlaca(@NotBlank String placa) {
        this.placa = placa;
    }

    public @Min(2000) Integer getAno() {
        return ano;
    }

    public void setAno(@Min(2000) Integer ano) {
        this.ano = ano;
    }

    public Long getNumeroIot() {
        return numeroIot;
    }

    public void setNumeroIot(Long numeroIot) {
        this.numeroIot = numeroIot;
    }

    public PatioEntity getPatio() {
        return patio;
    }

    public void setPatio(PatioEntity patio) {
        this.patio = patio;
    }
}
