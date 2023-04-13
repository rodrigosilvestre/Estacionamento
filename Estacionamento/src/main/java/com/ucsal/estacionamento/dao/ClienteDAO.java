package com.ucsal.estacionamento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ucsal.estacionamento.model.Cliente;
import com.ucsal.estacionamento.model.ConexaoBD;

public class ClienteDAO {
	
    private Connection connection;

    public ClienteDAO() {
        connection = ConexaoBD.conectar();
    }

    public void adicionar(List<Cliente> clientes) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO cliente (cpf, nome, endereco) VALUES (?, ?, ?)");
            for (Cliente cliente : clientes) {
                preparedStatement.setString(1, cliente.getCpf());
                preparedStatement.setString(2, cliente.getNome());
                preparedStatement.setString(3, cliente.getEndereco());
                preparedStatement.executeUpdate();
                System.out.println("Estacionamento adicionado com sucesso!");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void atualizar(Cliente cliente) {
        String sql = "UPDATE Cliente SET Nome = ?, Endereco = ? WHERE CPF = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getEndereco());
            statement.setString(3, cliente.getCpf());

            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar o cliente: " + ex.getMessage());
        }
    }

    public void excluir(String cpf) {
        String sql = "DELETE FROM Cliente WHERE CPF = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cpf);

            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao excluir o cliente: " + ex.getMessage());
        }
    }

    public List<Cliente> buscarTodos() {
        String sql = "SELECT CPF, Nome, Endereco FROM Cliente";
        List<Cliente> clientes = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setCpf(rs.getString("CPF"));
                cliente.setNome(rs.getString("Nome"));
                cliente.setEndereco(rs.getString("Endereco"));
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar todos os clientes: " + ex.getMessage());
        }

        return clientes;
    }

    public Cliente buscarPorCPF(String cpf) {
        String sql = "SELECT Nome, Endereco FROM Cliente WHERE CPF = ?";
        Cliente cliente = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cpf);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente();
                    cliente.setCpf(cpf);
                    cliente.setNome(rs.getString("Nome"));
                    cliente.setEndereco(rs.getString("Endereco"));
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar o cliente pelo CPF: " + ex.getMessage());
        }

        return cliente;
    }
    
}
