package com.example.norto.imobiliaria.models;

import java.util.Date;
import java.util.Objects;

public class Contrato {
    private int id;
    private Imovel imovel;
    private Corretor corretor;
    private Cliente cliente;
    private TipoContrato tpContrato;
    private Double valor;
    private int meses;
    private Date dtContrato;

    public Contrato() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public Corretor getCorretor() {
        return corretor;
    }

    public void setCorretor(Corretor corretor) {
        this.corretor = corretor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public TipoContrato getTpContrato() {
        return tpContrato;
    }

    public void setTpContrato(TipoContrato tpContrato) {
        this.tpContrato = tpContrato;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public int getMeses() {
        return meses;
    }

    public void setMeses(int meses) {
        this.meses = meses;
    }

    public Date getDtContrato() {
        return dtContrato;
    }

    public void setDtContrato(Date dtContrato) {
        this.dtContrato = dtContrato;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contrato)) return false;
        Contrato contrato = (Contrato) o;
        return id == contrato.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id + " - " + imovel.getDescricao() + " - " + corretor.getNome() + " - " + cliente.getNome();
    }
}
