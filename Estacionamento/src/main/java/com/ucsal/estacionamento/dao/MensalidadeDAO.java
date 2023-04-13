package com.ucsal.estacionamento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ucsal.estacionamento.model.ConexaoBD;
import com.ucsal.estacionamento.model.Mensalidade;

public class MensalidadeDAO {

    private Connection connection;

    public MensalidadeDAO() {
        connection = ConexaoBD.conectar();
    }

    public void adicionar(Mensalidade mensalidade) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO mensalidade(identificador, data_inicio, data_fim, cliente_cpf, estacionamento_identificador) VALUES (?, ?, ?, ?, ?)");
            // Configura os parâmetros do PreparedStatement
            preparedStatement.setString(1, mensalidade.getIdentificador());
            preparedStatement.setDate(2, new java.sql.Date(mensalidade.getDataInicio().getTime()));
            preparedStatement.setDate(3, new java.sql.Date(mensalidade.getDataFim().getTime()));
            preparedStatement.setString(4, mensalidade.getCliente().getCpf());
            preparedStatement.setString(5, mensalidade.getEstacionamento().getIdentificador());
            // Executa o PreparedStatement
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Mensalidade mensalidade) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE mensalidade SET data_inicio=?, data_fim=?, cliente_cpf=?, estacionamento_identificador=? WHERE identificador=?");
            // Configura os parâmetros do PreparedStatement
            preparedStatement.setDate(1, new java.sql.Date(mensalidade.getDataInicio().getTime()));
            preparedStatement.setDate(2, new java.sql.Date(mensalidade.getDataFim().getTime()));
            preparedStatement.setString(3, mensalidade.getCliente().getCpf());
            preparedStatement.setString(4, mensalidade.getEstacionamento().getIdentificador());
            preparedStatement.setString(5, mensalidade.getIdentificador());
            // Executa o PreparedStatement
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remover(String identificador) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM mensalidade WHERE identificador=?");
            // Configura os parâmetros do PreparedStatement
            preparedStatement.setString(1, identificador);
            // Executa o PreparedStatement
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Mensalidade> buscarPorIdentificador(String identificador) {
        Mensalidade mensalidade = null;
        ClienteDAO clienteDAO = new ClienteDAO();
        EstacionamentoDAO estacionamentoDAO =  new EstacionamentoDAO();
        List<Mensalidade> mensalidades = new ArrayList<Mensalidade>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM mensalidade WHERE identificador=?");
            // Configura os parâmetros do PreparedStatement
            preparedStatement.setString(1, identificador);
            // Executa a consulta
            ResultSet resultSet = preparedStatement.executeQuery();
            // Verifica se há resultados
            if (resultSet.next()) {
                // Cria um objeto Mensalidade com os dados do ResultSet
                mensalidade = new Mensalidade();
                mensalidade.setIdentificador(resultSet.getString("identificador"));
                mensalidade.setDataInicio(resultSet.getDate("data_inicio"));
                mensalidade.setDataFim(resultSet.getDate("data_fim"));
                mensalidade.setCliente(clienteDAO.buscarPorCPF(resultSet.getString("cliente_cpf")));
                mensalidade.setEstacionamento(estacionamentoDAO.buscarPorIdentificador(resultSet.getString("estacionamento_identificador")));
                mensalidades.add(mensalidade);
                }
                } catch (SQLException e) {
                e.printStackTrace();
                }
                return mensalidades;
                }
   
}
