package model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Banco {

	private static final String DRIVER_MYSQL = "com.mysql.cj.jdbc.Driver";
	private static final String NOME_ESQUEMA = "db_seixas";
	private static final String URL_CONEXAO = "jdbc:mysql://localhost:3306/" + NOME_ESQUEMA + "?useTimezone=true&serverTimezone=UTC"+ "&useSSL=false";
	private static final String USUARIO = "root";
	private static final String SENHA = "adstito123";

	public static final int CODIGO_RETORNO_SUCESSO = 1;

	public static Connection getConnection() {

		try {
			Connection conn = null;
			Class.forName(DRIVER_MYSQL);
			conn = DriverManager.getConnection(URL_CONEXAO, USUARIO, SENHA);
			return conn;
		} catch (ClassNotFoundException e) {
			System.out.println("Classe do Driver n„o foi encontrada. \n" + e.getMessage());
			return null;
		} catch (SQLException e) {
			System.out.println("Erro ao obter a Connection.\n" + e.getMessage());
			return null;
		}
	}

	public static void closeConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("Problema no fechamento da conex√£o.");
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public static Statement getStatement(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			return stmt;
		} catch (SQLException e) {
			System.out.println("Erro ao obter o Statement.");
			System.out.println("Erro: " + e.getMessage());
			return null;
		}
	}

	public static void closeStatement(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			System.out.println("Problema no fechamento do Statement.");
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public static PreparedStatement getPreparedStatement(Connection conn, String sql) {
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			return stmt;
		} catch (Exception e) {
			System.out.println("Erro ao obter o PreparedStatement.");
			System.out.println("Erro: " + e.getMessage());
			return null;
		}
	}

	public static PreparedStatement getPreparedStatementWithGeneratedKeys(Connection conn, String sql) {
		try {
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			return stmt;
		} catch (Exception e) {
			System.out.println("Erro ao obter o PreparedStatement.");
			System.out.println("Erro: " + e.getMessage());
			return null;
		}
	}

	public static void closePreparedStatement(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			System.out.println("Problema no fechamento do PreparedStatement.");
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public static void closeResultSet(ResultSet result) {
		try {
			if (result != null) {
				result.close();
			}
		} catch (SQLException e) {
			System.out.println("Problema no fechamento do ResultSet");
			System.out.println("Erro: " + e.getMessage());
		}
	}
}
