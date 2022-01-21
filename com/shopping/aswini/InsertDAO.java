package com.shopping.aswini;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertDAO extends ConnectionManagement {
	public void insert(PojoInsert pi) throws SQLException {
		    final Connection connection = getConnection();
		
		    PreparedStatement p = connection.prepareStatement("Insert into pdts values(?,?,?,?,?,?,?,?,?)");
		    p.setString(1, pi.getProductName());
		    p.setString(2, pi.getBrandName());
		    p.setString(3, pi.getPrice());
		    p.setString(4, pi.getPerson());
		    p.setString(5, pi.getColour1());
		    p.setString(6, pi.getColour2());
		    p.setString(7, pi.getSize());
		    p.setString(8, pi.getQuantity());
		    p.setString(9, pi.getReview());
		    p.executeUpdate();
	    }
}
