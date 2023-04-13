package com.ucsal.estacionamento.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ucsal.estacionamento.model.ConexaoBD;
import com.ucsal.estacionamento.model.Estacionamento;
import com.ucsal.estacionamento.model.Preco;

public class PrecoDAO {

	private Connection connection;

	public PrecoDAO(Connection connection) {
		connection = ConexaoBD.conectar();
	}

	public void adicionar(Preco preco) throws SQLException {
		String sql = "INSERT INTO precos (identificador, data_inicio, data_fim, preco_mensalista, preco_horista, estacionamento_identificador) VALUES (?, ?, ?, ?, ?, ?)";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, preco.getIdentificador());
			statement.setDate(2, java.sql.Date.valueOf(preco.getDataInicio()));
			statement.setDate(3, java.sql.Date.valueOf(preco.getDataFim()));
			statement.setBigDecimal(4, preco.getPrecoMensalista());
			statement.setBigDecimal(5, preco.getPrecoHorista());
			statement.setString(6, preco.getEstacionamento().getIdentificador());

			statement.executeUpdate();
		}
	}

	public void atualizar(Preco preco) throws SQLException {
		String sql = "UPDATE precos SET data_inicio = ?, data_fim = ?, preco_mensalista = ?, preco_horista = ?, estacionamento_identificador = ? WHERE identificador = ?";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setDate(1, java.sql.Date.valueOf(preco.getDataInicio()));
			statement.setDate(2, java.sql.Date.valueOf(preco.getDataFim()));
			statement.setBigDecimal(3, preco.getPrecoMensalista());
			statement.setBigDecimal(4, preco.getPrecoHorista());
			statement.setString(5, preco.getEstacionamento().getIdentificador());
			statement.setString(6, preco.getIdentificador());

			statement.executeUpdate();
		}
	}

	public void remover(String identificador) throws SQLException {
		String sql = "DELETE FROM precos WHERE identificador = ?";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, identificador);

			statement.executeUpdate();
		}
	}

	public Preco buscarPorIdentificador(String identificador) throws SQLException {
		String sql = "SELECT * FROM precos WHERE identificador = ?";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, identificador);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					Preco preco = new Preco();
					preco.setIdentificador(resultSet.getString("identificador"));
					preco.setDataInicio(resultSet.getDate("data_inicio").toLocalDate());
					preco.setDataFim(resultSet.getDate("data_fim").toLocalDate());
					preco.setPrecoMensalista(resultSet.getBigDecimal("preco_mensalista"));
					preco.setPrecoHorista(resultSet.getBigDecimal("preco_horista"));
					preco.setEstacionamento(new Estacionamento(resultSet.getString("estacionamento_identificador")));

					return preco;
				}
			}
		}

		return null;
	}

	public List<Preco> listarTodos() throws SQLException {
		EstacionamentoDAO estacionamentoDAO = new EstacionamentoDAO();
		List<Preco> precos = new ArrayList<>();
		String sql = "SELECT * FROM precos";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			Preco preco = new Preco();
			preco.setIdentificador(resultSet.getString("identificador"));
			preco.setDataInicio(resultSet.getDate("data_inicio").toLocalDate());
			preco.setDataFim(resultSet.getDate("data_fim").toLocalDate());
			preco.setPrecoMensalista(resultSet.getBigDecimal("preco_mensalista"));
			preco.setPrecoHorista(resultSet.getBigDecimal("preco_horista"));
			preco.setEstacionamento(
					estacionamentoDAO.buscarPorIdentificador(resultSet.getString("estacionamento_identificador")));
			precos.add(preco);
		}

		return precos;
	}

}
