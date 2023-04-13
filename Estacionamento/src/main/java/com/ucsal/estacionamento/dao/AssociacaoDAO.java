package com.ucsal.estacionamento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ucsal.estacionamento.model.Associacao;
import com.ucsal.estacionamento.model.ConexaoBD;

public class AssociacaoDAO {
    
    private Connection connection;
    
    public AssociacaoDAO() {
        connection = ConexaoBD.conectar();
    }
    
    public void adicionar(Associacao associacao) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO associacao(identificador, cliente_cpf, estacionamento_identificador) VALUES (?, ?, ?)");
            
            preparedStatement.setString(1, associacao.getIdentificador());
            preparedStatement.setString(2, associacao.getClienteCpf());
            preparedStatement.setString(3, associacao.getEstacionamentoIdentificador());
            
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void atualizar(Associacao associacao) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE associacao SET cliente_cpf=?, estacionamento_identificador=? WHERE identificador=?");
            
            preparedStatement.setString(1, associacao.getClienteCpf());
            preparedStatement.setString(2, associacao.getEstacionamentoIdentificador());
            preparedStatement.setString(3, associacao.getIdentificador());
            
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void remover(String identificador) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM associacao WHERE identificador=?");
            preparedStatement.setString(1, identificador);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Associacao> listar() {
        List<Associacao> associacoes = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM associacao");
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                Associacao associacao = new Associacao();
                associacao.setIdentificador(resultSet.getString("identificador"));
                associacao.setClienteCpf(resultSet.getString("cliente_cpf"));
                associacao.setEstacionamentoIdentificador(resultSet.getString("estacionamento_identificador"));
                
                associacoes.add(associacao);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return associacoes;
    }
    
    public Associacao buscarPorIdentificador(String identificador) {
        Associacao associacao = new Associacao();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM associacao WHERE identificador=?");
            preparedStatement.setString(1, identificador);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                associacao.setIdentificador(resultSet.getString("identificador"));
                associacao.setClienteCpf(resultSet.getString("cliente_cpf"));
                associacao.setEstacionamentoIdentificador(resultSet.getString("estacionamento_identificador"));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return associacao;
    }

}
