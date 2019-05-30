package com.example.norto.imobiliaria.models;

public class Imovel {
    private int id;
    private String descricao;
    private Endereco endereco;
    private int qteComodos;
    private Boolean mobiliada;
    private int tamTerreno;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public int getQteComodos() {
        return qteComodos;
    }

    public void setQteComodos(int qteComodos) {
        this.qteComodos = qteComodos;
    }

    public Boolean getMobiliada() {
        return mobiliada;
    }

    public void setMobiliada(Boolean mobiliada) {
        this.mobiliada = mobiliada;
    }

    public int getTamTerreno() {
        return tamTerreno;
    }

    public void setTamTerreno(int tamTerreno) {
        this.tamTerreno = tamTerreno;
    }

    @Override
    public String toString() {
        return id + " - " + descricao;
    }
}
