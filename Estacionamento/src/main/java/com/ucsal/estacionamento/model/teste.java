package com.ucsal.estacionamento.model;


import java.util.List;

import com.ucsal.estacionamento.dao.*;

public class teste {
 
	    public static void main(String[] args) {
	        // Criando um objeto EstacionamentoDAO
	        EstacionamentoDAO estacionamentoDAO = new EstacionamentoDAO();
	        
	        // Criando um objeto Estacionamento para inserção no banco de dados
//	        Estacionamento estacionamento = new Estacionamento();
//	        estacionamento.setIdentificador("Estacionamento A");
//	        estacionamento.setEndereco("Rua X, 123");
//	        
//	        // Inserindo o estacionamento no banco de dados
//	        estacionamentoDAO.adicionar(estacionamento);
	        
	        List<Estacionamento> estacionameto = estacionamentoDAO.buscarTodos();
	        for (Estacionamento estacionametos : estacionameto) {
	            System.out.println(estacionametos);
	        }

	    }
	}
