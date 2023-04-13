package com.ucsal.estacionamento.model;

public class Associacao {
    private String identificador;
    private String clienteCpf;
    private String estacionamentoIdentificador;

    public Associacao(String identificador, String clienteCpf, String estacionamentoIdentificador) {
        this.identificador = identificador;
        this.clienteCpf = clienteCpf;
        this.estacionamentoIdentificador = estacionamentoIdentificador;
    }
    
    public Associacao() {
 
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getClienteCpf() {
        return clienteCpf;
    }

    public void setClienteCpf(String clienteCpf) {
        this.clienteCpf = clienteCpf;
    }

    public String getEstacionamentoIdentificador() {
        return estacionamentoIdentificador;
    }

    public void setEstacionamentoIdentificador(String estacionamentoIdentificador) {
        this.estacionamentoIdentificador = estacionamentoIdentificador;
    }

    @Override
    public String toString() {
        return "Associacao [identificador=" + identificador + ", clienteCpf=" + clienteCpf + ", estacionamentoIdentificador="
                + estacionamentoIdentificador + "]";
    }
}
