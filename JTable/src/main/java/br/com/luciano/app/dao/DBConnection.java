package br.com.luciano.app.dao;

import java.sql.*;


public class DBConnection {
	private static final String URL = "jdbc:mysql://localhost/Livro";
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String USER = "root";
	private static final String PASS = "Kamenriderv3";
	
	public static Connection getConnection() throws SQLException {
		
		System.out.println("Conectando ao Banco de Dados");
		try {
			Class.forName(DRIVER);
			
			return DriverManager.getConnection(URL, USER, PASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}
	
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (conn!=null) {
				conn.close();
			}
			if (stmt!=null) {
				stmt.close();
			}
			if (rs!=null) {
				rs.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void createTable() throws SQLException {
		Connection connection = getConnection();
		PreparedStatement stmt = null;
		String sql = "CREATE TABLE IF NOT EXISTS `Biblioteca` ("
				+ "`ID` bigint(20) NOT NULL AUTO_INCREMENT,"
				+ "`EDITORA` varchar(50) NOT NULL,"
				+ "`TITULO` varchar(50) NOT NULL,"
				+ "`ISBN` varchar(50) NOT NULL,"
				+ "PRIMARY KEY(`ID`)"
				+ ")";
		
		try {
			stmt = connection.prepareStatement(sql);
			stmt.execute();
			System.out.println("Create table Ok!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(connection, stmt, null);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}