package com.shopping.aswini;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManagement {
		public Connection getConnection() throws SQLException {
		    final Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopapp", 
		    		"root", "N@tt@r99");
		    return connection;
	    }

}

