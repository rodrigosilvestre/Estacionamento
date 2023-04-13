package com.ucsal.estacionamento.model;

import com.ucsal.estacionamento.dao.ClienteDAO;
import com.ucsal.estacionamento.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class TestePovoarCliente {

    public static void main(String[] args) {
        List<Cliente> clientes = new ArrayList<>();

        // Adiciona clientes na lista
//        clientes.add(new Cliente("12345678910", "Fulano", "Rua A, 123"));
//        clientes.add(new Cliente("10987654321", "Ciclano", "Rua B, 456"));
//        clientes.add(new Cliente("98765432101", "Beltrano", "Rua C, 789"));

        // Insere os clientes no banco de dados
        ClienteDAO clientedao = new ClienteDAO();
        clientedao.adicionar(clientes);
        
        clientes = clientedao.buscarTodos();
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }

    }
}
