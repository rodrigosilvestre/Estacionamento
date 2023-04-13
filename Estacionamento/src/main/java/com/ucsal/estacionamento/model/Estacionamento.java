package com.ucsal.estacionamento.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Estacionamento")
public class Estacionamento {

    @Id
    @Column(name = "Identificador")
    private String identificador;

    @Column(name = "Endereco")
    private String endereco;

    @Column(name = "Telefone")
    private String telefone;

    @Column(name = "Responsavel")
    private String responsavel;

    @Column(name = "Vagas")
    private int vagas;

    public Estacionamento() {
    }

    public Estacionamento(String identificador, String endereco, String telefone, String responsavel, int vagas) {
        this.identificador = identificador;
        this.endereco = endereco;
        this.telefone = telefone;
        this.responsavel = responsavel;
        this.vagas = vagas;
    }

    public Estacionamento(String string) {
		// TODO Auto-generated constructor stub
	}

	public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }

    @Override
    public String toString() {
        return "Estacionamento{" +
                "identificador='" + identificador + '\'' +
                ", endereco='" + endereco + '\'' +
                ", telefone='" + telefone + '\'' +
                ", responsavel='" + responsavel + '\'' +
                ", vagas=" + vagas +
                '}';
    }
}
