package com.ucsal.estacionamento.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Preco {
    private String identificador;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private BigDecimal precoMensalista;
    private BigDecimal precoHorista;
    private Estacionamento estacionamento;

    public Preco(String identificador, LocalDate dataInicio, LocalDate dataFim, BigDecimal precoMensalista, BigDecimal precoHorista, Estacionamento estacionamento) {
        this.identificador = identificador;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.precoMensalista = precoMensalista;
        this.precoHorista = precoHorista;
        this.estacionamento = estacionamento;
    }
    
    public Preco() {
     
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public BigDecimal getPrecoMensalista() {
        return precoMensalista;
    }

    public void setPrecoMensalista(BigDecimal precoMensalista) {
        this.precoMensalista = precoMensalista;
    }

    public BigDecimal getPrecoHorista() {
        return precoHorista;
    }

    public void setPrecoHorista(BigDecimal precoHorista) {
        this.precoHorista = precoHorista;
    }

    public Estacionamento getEstacionamento() {
        return estacionamento;
    }

    public void setEstacionamento(Estacionamento estacionamento) {
        this.estacionamento = estacionamento;
    }

    @Override
    public String toString() {
        return "Preco [identificador=" + identificador + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim
                + ", precoMensalista=" + precoMensalista + ", precoHorista=" + precoHorista + ", estacionamento="
                + estacionamento + "]";
    }
}
