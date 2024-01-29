
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//
//public class DBConnect {
//
//	private static Connection conn;
//	
//	public static Connection getConn() {
//		
//		try	{
//			Class.forName("com.mysql.cj.jdbc.Driver.");
//			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ebook-app","root","1234"); //url, username, password
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return conn;
//	}
//}


package com.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnect {
	
	private static Connection conn;
	public static Connection getConn() {
		String dburl="jdbc:mysql://localhost:3306/shopping-cart";
		String dbuser="root";
		String dbpassword="avioniks2126";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		    conn = DriverManager.getConnection(dburl, dbuser, dbpassword);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
}



