package com.javaweb.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	public static Connection getConnection() throws SQLException {
		Connection connection = null;
		// Khai báo driver sử dụng cho JDBC (Lên Mạng Search)
		try {
			String urlString ="jdbc:mysql://localhost:3307/estatebasic";
			String username="root";
			String passsword ="090902";
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(urlString,username,passsword);
		} catch (ClassNotFoundException e) {
			System.out.println("Không thể kết nối cơ sở dữ liệu"+ e.getMessage());
			
		}
		
		return connection;
	}
}
