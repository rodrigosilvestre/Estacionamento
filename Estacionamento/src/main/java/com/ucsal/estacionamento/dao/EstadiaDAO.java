package com.ucsal.estacionamento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ucsal.estacionamento.model.ConexaoBD;
import com.ucsal.estacionamento.model.Entrada;
import com.ucsal.estacionamento.model.Estadia;
import com.ucsal.estacionamento.model.Veiculo;

public class EstadiaDAO {

	private Connection connection;

	public EstadiaDAO() {
		connection = ConexaoBD.conectar();
	}

	public void adicionar(Estadia estadia) {
		String sql = "INSERT INTO estadia (identificador, veiculo_placa, entrada_identificador) VALUES (?, ?, ?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, estadia.getIdentificador());
			preparedStatement.setString(2, estadia.getVeiculo().getPlaca());
			preparedStatement.setString(3, estadia.getEntrada().getIdentificador());
			preparedStatement.executeUpdate();
			System.out.println("Estadia adicionada com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro ao adicionar estadia: " + e.getMessage());
		}
	}

	public void atualizar(Estadia estadia) {
		String sql = "UPDATE estadia SET veiculo_placa=?, entrada_identificador=? WHERE identificador=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, estadia.getVeiculo().getPlaca());
			preparedStatement.setString(2, estadia.getEntrada().getIdentificador());
			preparedStatement.setString(3, estadia.getIdentificador());
			preparedStatement.executeUpdate();
			System.out.println("Estadia atualizada com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar estadia: " + e.getMessage());
		}
	}

	public void remover(String identificador) {
		String sql = "DELETE FROM estadia WHERE identificador=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, identificador);
			preparedStatement.executeUpdate();
			System.out.println("Estadia removida com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro ao remover estadia: " + e.getMessage());
		}
	}

	public List<Estadia> listar() {
		List<Estadia> estadias = new ArrayList<Estadia>();
		String sql = "SELECT * FROM estadia";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Estadia estadia = new Estadia();
				estadia.setIdentificador(resultSet.getString("identificador"));
				Veiculo veiculo = new VeiculoDAO().buscarPorPlaca(resultSet.getString("veiculo_placa"));
				estadia.setVeiculo(veiculo);
				Entrada entrada = new EntradaDAO().buscarPorIdentificador(resultSet.getString("entrada_identificador"));
				estadia.setEntrada(entrada);
				estadias.add(estadia);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar estadias: " + e.getMessage());
		}
		return estadias;
	}

	public Estadia buscarPorIdentificador(String identificador) throws SQLException {
		Estadia estadia = null;
		VeiculoDAO veiculoDAO = new VeiculoDAO();
		EntradaDAO entradaDAO = new EntradaDAO();
				
		String sql = "SELECT * FROM estadia WHERE identificador = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, identificador);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			estadia = new Estadia();
			estadia.setIdentificador(resultSet.getString("identificador"));
			estadia.setVeiculo(veiculoDAO.buscarPorPlaca(resultSet.getString("placa_veiculo")));
			estadia.setEntrada(entradaDAO.buscarPorIdentificador(resultSet.getString("identificador_entrada")));
		}

		return estadia;
	}
}
