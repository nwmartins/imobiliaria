package com.example.norto.imobiliaria.models;

public enum TipoContrato {
    ALUGUEL(1), VENDA(2);

    private int valor;

    TipoContrato(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
