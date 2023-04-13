package com.ucsal.estacionamento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ucsal.estacionamento.model.ConexaoBD;
import com.ucsal.estacionamento.model.Estacionamento;


public class EstacionamentoDAO {

    private Connection connection;
    
    public EstacionamentoDAO() {
        connection = ConexaoBD.conectar();
    }
    
    public void adicionar(Estacionamento estacionamento) {
        try {
            String query = "INSERT INTO Estacionamento (Identificador, Endereco, Vagas) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, estacionamento.getIdentificador());
            preparedStatement.setString(2, estacionamento.getEndereco());
            preparedStatement.setInt(3, estacionamento.getVagas());
            preparedStatement.executeUpdate();
            System.out.println("Estacionamento adicionado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void atualizar(Estacionamento estacionamento) {
        try {
            String query = "UPDATE Estacionamento SET Endereco=?, Vagas=? WHERE Identificador=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, estacionamento.getEndereco());
            preparedStatement.setInt(2, estacionamento.getVagas());
            preparedStatement.setString(3, estacionamento.getIdentificador());
            preparedStatement.executeUpdate();
            System.out.println("Estacionamento atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void excluir(String identificador) {
        try {
            String query = "DELETE FROM Estacionamento WHERE Identificador=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, identificador);
            preparedStatement.executeUpdate();
            System.out.println("Estacionamento removido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Estacionamento buscarPorIdentificador(String identificador) {
        Estacionamento estacionamento = null;
        try {
            String query = "SELECT * FROM Estacionamento WHERE Identificador=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, identificador);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                estacionamento = new Estacionamento();
                estacionamento.setIdentificador(resultSet.getString("Identificador"));
                estacionamento.setEndereco(resultSet.getString("Endereco"));
                estacionamento.setVagas(resultSet.getInt("Vagas"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estacionamento;
    }
    
    public List<Estacionamento> buscarTodos() {
        List<Estacionamento> estacionamentos = new ArrayList<>();
        try {
            String query = "SELECT * FROM Estacionamento";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Estacionamento estacionamento = new Estacionamento();
                estacionamento.setIdentificador(resultSet.getString("Identificador"));
                estacionamento.setEndereco(resultSet.getString("Endereco"));
                estacionamento.setVagas(resultSet.getInt("Vagas"));
                estacionamentos.add(estacionamento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estacionamentos;
    }
    
}
