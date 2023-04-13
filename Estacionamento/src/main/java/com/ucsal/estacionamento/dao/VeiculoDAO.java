package com.ucsal.estacionamento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ucsal.estacionamento.model.ConexaoBD;
import com.ucsal.estacionamento.model.Veiculo;

public class VeiculoDAO {

	private Connection connection;

	public VeiculoDAO() {
		connection = ConexaoBD.conectar();
	}

	public void adicionar(Veiculo veiculo) {
		String sql = "INSERT INTO veiculo (placa, nome_dono, telefone) VALUES (?, ?, ?)";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, veiculo.getPlaca());
			statement.setString(2, veiculo.getNomeDono());
			statement.setString(3, veiculo.getTelefone());

			statement.executeUpdate();
			System.out.println("Veículo adicionado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro ao adicionar veículo: " + e.getMessage());
		}
	}

	public void atualizar(Veiculo veiculo) {
		String sql = "UPDATE veiculo SET nome_dono = ?, telefone = ? WHERE placa = ?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, veiculo.getNomeDono());
			statement.setString(2, veiculo.getTelefone());
			statement.setString(3, veiculo.getPlaca());

			statement.executeUpdate();
			System.out.println("Veículo atualizado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar veículo: " + e.getMessage());
		}
	}

	public void remover(String placa) {
		String sql = "DELETE FROM veiculo WHERE placa = ?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, placa);

			statement.executeUpdate();
			System.out.println("Veículo removido com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro ao remover veículo: " + e.getMessage());
		}
	}

	public Veiculo buscarPorPlaca(String placa) {
		String sql = "SELECT * FROM veiculo WHERE placa = ?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, placa);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				Veiculo veiculo = new Veiculo();
				veiculo.setPlaca(resultSet.getString("placa"));
				veiculo.setNomeDono(resultSet.getString("nome_dono"));
				veiculo.setTelefone(resultSet.getString("telefone"));

				return veiculo;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar veículo por placa: " + e.getMessage());
		}

		return null;
	}

	public List<Veiculo> listar() {
		List<Veiculo> veiculos = new ArrayList<>();
		String sql = "SELECT * FROM veiculo";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Veiculo veiculo = new Veiculo();
				veiculo.setPlaca(resultSet.getString("placa"));
				veiculo.setNomeDono(resultSet.getString("nome_dono"));
				veiculo.setTelefone(resultSet.getString("telefone"));

				veiculos.add(veiculo);
			}

			return veiculos;
		} catch (SQLException e) {
			System.out.println("Erro ao listar veículos: " + e.getMessage());
		}

		return null;
	}

}
