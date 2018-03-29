package com.bo.tournament.database;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DatabaseConnectionChecker extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	 static final String DB_URL = "jdbc:mysql://tomcat-mysql:3306/tournament_bo";
	 Connection conn = null;
	 java.sql.Statement stmt = null;
	 static final String USER = "root";
	 static final String PASS = "root";
	   
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String sql;
		    sql = "select count(*) from bo_tournament_master";
		    stmt = conn.createStatement();
		    ResultSet rs = stmt.executeQuery(sql);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		super.doGet(req, resp);
	}

}
