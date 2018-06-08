package br.com.farmacia.ConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

	private static final String USUARIO = "root";
	private static final String SENHA = "root";
	private static final String URL = "jdbc:mysql://localhost:3306/farmacia";

	public static Connection getConnection() throws SQLException {

		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		
		Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);

		return conexao;
	}
	public static void main(String[] args) {
		try {
			Connection conexao = ConexaoFactory.getConnection();
			System.out.println("Conexao Sucesso!");
		} catch (Exception e) {
			System.out.println("Conexão Falhou!");
		}
	}
}
