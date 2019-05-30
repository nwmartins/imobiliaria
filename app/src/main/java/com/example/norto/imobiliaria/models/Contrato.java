package com.example.norto.imobiliaria.models;

import com.orm.dsl.NotNull;
import com.orm.dsl.Unique;

import java.util.Date;
import java.util.Objects;

public class Contrato {
    @Unique
    private int codigo;
    @NotNull
    private Imovel imovel;
    @NotNull
    private Corretor corretor;
    @NotNull
    private Cliente cliente;
    @NotNull
    private TipoContrato tpContrato;
    @NotNull
    private Double valor;
    @NotNull
    private int meses;
    @NotNull
    private Date dtContrato;

    public Contrato() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
    public String toString() {
        return codigo + " - " + imovel.getDescricao() + " - " + corretor.getNome() + " - " + cliente.getNome();
    }
}
