package com.example.norto.imobiliaria.models;

import java.util.ArrayList;

public class Corretor {
    private int id;
    private Pessoa pessoa;
    private int creci;
    private ArrayList<Imovel> imovelList;

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

    @Override
    public String toString() {
        return id + " - " + pessoa.getNome() + " - " + creci;
    }
}
