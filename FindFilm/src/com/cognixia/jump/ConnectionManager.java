package com.cognixia.jump;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	
	private static final String URL = "jdbc:mysql://localhost:3306/sakila"; // ?serverTimezone=EST5EDT
	private static final String USERNAME = "root";
	private static final String PASSWORD = "milarose24"; // Windows: root, Mac/Linux: Root@123
	
	private static Connection conn = null;
	
	private static void makeConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection() {
		
		if(conn == null) {
			makeConnection();
		}
		
		return conn;
	}

}
