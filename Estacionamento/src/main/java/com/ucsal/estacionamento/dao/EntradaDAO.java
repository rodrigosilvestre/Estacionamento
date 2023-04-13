package com.ucsal.estacionamento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ucsal.estacionamento.model.ConexaoBD;
import com.ucsal.estacionamento.model.Entrada;

public class EntradaDAO {

	private Connection connection;

	public EntradaDAO() {
		connection = ConexaoBD.conectar();
	}

	public void adicionar(Entrada entrada) {
		try {
			// Prepara a query de inserção
			String sql = "INSERT INTO entrada (identificador, entrada, saida, placa_cpf, estacionamento_identificador) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, entrada.getIdentificador());
			statement.setTimestamp(2, new java.sql.Timestamp(entrada.getEntrada().getTime()));
			statement.setTimestamp(3, new java.sql.Timestamp(entrada.getSaida().getTime()));
			statement.setString(4, entrada.getPlacaCPF());
			statement.setString(5, entrada.getEstacionamentoIdentificador());

			// Executa a query
			statement.executeUpdate();
			System.out.println("Entrada adicionada com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro ao adicionar entrada: " + e.getMessage());
		}
	}

	public void atualizar(Entrada entrada) {
		try {
			// Prepara a query de atualização
			String sql = "UPDATE entrada SET entrada=?, saida=?, placa_cpf=?, estacionamento_identificador=? WHERE identificador=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setTimestamp(1, new java.sql.Timestamp(entrada.getEntrada().getTime()));
			statement.setTimestamp(2, new java.sql.Timestamp(entrada.getSaida().getTime()));
			statement.setString(3, entrada.getPlacaCPF());
			statement.setString(4, entrada.getEstacionamentoIdentificador());
			statement.setString(5, entrada.getIdentificador());

			// Executa a query
			statement.executeUpdate();
			System.out.println("Entrada atualizada com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar entrada: " + e.getMessage());
		}
	}

	public void remover(String identificador) {
		try {
			// Prepara a query de remoção
			String sql = "DELETE FROM entrada WHERE identificador=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, identificador);

			// Executa a query
			statement.executeUpdate();
			System.out.println("Entrada removida com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro ao remover entrada: " + e.getMessage());
		}
	}

	public List<Entrada> listar() {
		List<Entrada> entradas = new ArrayList<>();

		try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM entrada");
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				Entrada entrada = new Entrada();
				entrada.setIdentificador(resultSet.getString("identificador"));
				entrada.setEntrada(resultSet.getTimestamp("entrada"));
				entrada.setSaida(resultSet.getTimestamp("saida"));
				entrada.setPlacaCPF(resultSet.getString("placa_cpf"));
				entrada.setEstacionamentoIdentificador(resultSet.getString("estacionamento_identificador"));

				entradas.add(entrada);
			}
		} catch (SQLException e) {
			System.err.println("Erro ao listar entradas: " + e.getMessage());
		}

		return entradas;
	}

	public Entrada buscarPorIdentificador(String identificador) {
		Entrada entrada = null;
		String sql = "SELECT * FROM entrada WHERE identificador = ?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, identificador);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				entrada = new Entrada();
				entrada.setIdentificador(resultSet.getString("identificador"));
				entrada.setEntrada(resultSet.getTimestamp("entrada"));
				entrada.setSaida(resultSet.getTimestamp("saida"));
				entrada.setPlacaCPF(resultSet.getString("placa_cpf"));
				entrada.setEstacionamentoIdentificador(resultSet.getString("estacionamento_identificador"));
			}

			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			System.out.println("Erro ao buscar a entrada por identificador: " + e.getMessage());
		}

		return entrada;
	}

}
