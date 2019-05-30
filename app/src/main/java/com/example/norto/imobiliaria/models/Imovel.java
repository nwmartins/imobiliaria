package com.example.norto.imobiliaria.models;

import java.util.Objects;

public class Imovel {
    private int codigo;
    private String descricao;
    private Endereco endereco;
    private int qteComodos;
    private Boolean mobiliada;
    private int tamTerreno;

    public Imovel() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Imovel)) return false;
        Imovel imovel = (Imovel) o;
        return codigo == imovel.codigo;
    }

    @Override
    public int hashCode() {

        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return codigo + " - " + descricao;
    }
}
