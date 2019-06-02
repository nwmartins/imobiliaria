package com.example.norto.imobiliaria.models;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.orm.dsl.NotNull;
import com.orm.dsl.Unique;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Corretor extends SugarRecord implements Serializable {
    @Unique
    private int codigo;
    @NotNull
    private String nome;
    @NotNull
    private String rg;
    @NotNull
    private String cpf;
    private String email;
    @NotNull
    private int creci;
    @Ignore
    private ArrayList<Imovel> imovelList;

    public Corretor() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCreci() {
        return creci;
    }

    public void setCreci(int creci) {
        this.creci = creci;
    }

    public ArrayList<Imovel> getImovelList() {
        return imovelList;
    }

    public void setImovelList(ArrayList<Imovel> imovelList) {
        this.imovelList = imovelList;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Corretor)) return false;
        Corretor corretor = (Corretor) o;
        return codigo == corretor.codigo;
    }

    @Override
    public int hashCode() {

        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return codigo + " - " + nome + " - " + creci;
    }
}
