package com.ucsal.estacionamento.model;

import java.sql.Date;

public class Mensalidade {
    private String identificador;
    private Date dataInicio;
    private Date dataFim;
    private Cliente cliente;
    private Estacionamento estacionamento;

    public Mensalidade(String identificador, Date dataInicio, Date dataFim, Cliente cliente, Estacionamento estacionamento) {
        this.identificador = identificador;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.cliente = cliente;
        this.estacionamento = estacionamento;
    }
    
    public Mensalidade() {
       
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Estacionamento getEstacionamento() {
        return estacionamento;
    }

    public void setEstacionamento(Estacionamento estacionamento) {
        this.estacionamento = estacionamento;
    }

    @Override
    public String toString() {
        return "Mensalidade{" +
                "identificador='" + identificador + '\'' +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                ", cliente=" + cliente +
                ", estacionamento=" + estacionamento +
                '}';
    }
}
