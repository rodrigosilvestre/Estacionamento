package com.ucsal.estacionamento.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoUtil {

    private static final String URL = "jdbc:postgresql://localhost:5432/estacionamento";
    private static final String USER = "postgres";
    private static final String PASSWORD = "8554692101";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
