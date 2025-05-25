package com.fiap.gestaofrota.dto;

import com.fiap.gestaofrota.enums.Marcas;
import com.fiap.gestaofrota.enums.Modelos;

public class MotoDTO {
    private Long id;
    private Marcas marca;
    private Modelos modelo;
    private String placa;
    private Integer ano;
    private Long numeroIot;
    private Long patioId;

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

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Long getNumeroIot() {
        return numeroIot;
    }

    public void setNumeroIot(Long numeroIot) {
        this.numeroIot = numeroIot;
    }

    public Long getPatioId() {
        return patioId;
    }

    public void setPatioId(Long patioId) {
        this.patioId = patioId;
    }
}
