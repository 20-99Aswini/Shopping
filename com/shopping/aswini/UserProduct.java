package com.shopping.aswini;

import java.sql.SQLException;

public interface UserProduct  {
	
	void showProducts() throws SQLException;
	
	void search() throws SQLException;
	
	void booking(String PdtName) throws SQLException;
}
