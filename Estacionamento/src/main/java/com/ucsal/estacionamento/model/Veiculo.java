package com.ucsal.estacionamento.model;

public class Veiculo {
    private String placa;
    private String nomeDono;
    private String telefone;

    public Veiculo() {}

    public Veiculo(String placa, String nomeDono, String telefone) {
        this.placa = placa;
        this.nomeDono = nomeDono;
        this.telefone = telefone;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getNomeDono() {
        return nomeDono;
    }

    public void setNomeDono(String nomeDono) {
        this.nomeDono = nomeDono;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "placa='" + placa + '\'' +
                ", nomeDono='" + nomeDono + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
