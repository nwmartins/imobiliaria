package com.example.norto.imobiliaria.models;

public class Cliente {
    private int id;
    private Pessoa pessoa;
    private String email;
    private Endereco enderco;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Endereco getEnderco() {
        return enderco;
    }

    public void setEnderco(Endereco enderco) {
        this.enderco = enderco;
    }

    @Override
    public String toString() {
        return id + " - " + pessoa.getNome();
    }
}