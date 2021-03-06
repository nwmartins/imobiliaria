package com.example.norto.imobiliaria.models;

import com.orm.SugarRecord;
import com.orm.dsl.NotNull;

import java.io.Serializable;
import java.util.Objects;

public class Endereco extends SugarRecord implements Serializable {
    private int codigo;
    @NotNull
    private String logradouro;
    @NotNull
    private String bairro;
    @NotNull
    private int numero;
    private String complemento;

    public Endereco() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Endereco)) return false;
        Endereco endereco = (Endereco) o;
        return codigo == endereco.codigo;
    }

    @Override
    public int hashCode() {

        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return codigo + " - " + logradouro + " - " + numero + " - " + bairro;
    }
}
