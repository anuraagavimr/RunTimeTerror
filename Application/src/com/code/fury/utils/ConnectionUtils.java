package com.code.fury.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {

private static Connection con;
	//DataBase Connection
	public static Connection getMySqlConnection() {
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "root@123");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
	
	public static void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
