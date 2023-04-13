package com.ucsal.estacionamento.model;

public class Estadia {
    private String identificador;
    private Veiculo veiculo;
    private Entrada entrada;

    public Estadia(String identificador, Veiculo veiculo, Entrada entrada) {
        this.identificador = identificador;
        this.veiculo = veiculo;
        this.entrada = entrada;
    }
    
    public Estadia() {
      
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Entrada getEntrada() {
        return entrada;
    }

    public void setEntrada(Entrada entrada) {
        this.entrada = entrada;
    }

    @Override
    public String toString() {
        return "Estadia{" +
                "identificador='" + identificador + '\'' +
                ", veiculo=" + veiculo +
                ", entrada=" + entrada +
                '}';
    }
}
