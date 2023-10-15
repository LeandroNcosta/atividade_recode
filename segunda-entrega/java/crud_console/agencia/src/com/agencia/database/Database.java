package com.agencia.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.agencia.utils.Colors;

public class Database {
	private static final String URL = "jdbc:mysql://localhost:3306/agencia_viagem";
	private static final String USER = "root";
	private static final String PASSWORD = "1234";

	// throws SQLException
	public static Connection createConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println(Colors.GREEN + " Driver encontrado." + Colors.RESET);
		} catch (ClassNotFoundException e) {
			System.out.println(Colors.RED +  "Driver não encontrado! Mensagem: " + e.getMessage() + Colors.RESET);
		}

		try {
			Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println(" Conectando...");
			System.out.println(Colors.GREEN + " Conectado ao banco com sucesso!" + Colors.RESET);
			System.out.println("");

			return connection;
		} catch (SQLException e) {
			System.out.println(
					Colors.RED + " Não foi possível conectar ao banco! Mensagem: " + e.getMessage() + Colors.RESET);
			System.out.println("");

			return null;
		}
	}
}
