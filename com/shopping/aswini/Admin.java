package com.shopping.aswini;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class Admin extends ConnectionManagement implements AdminProduct {
	protected  char choice = ' ';
	protected  String productName=" ";
	protected  String price =" ";
	protected  String quantity =" ";
	protected  String brandName = " " ;
	
	@Override
	public void update() throws SQLException {
		final Connection connection = getConnection();
		final Statement statement =connection.createStatement();
		
		do {
			System.out.println("Enter Product to be updated");
			productName = Main.scanner.next();
			System.out.println("Enter Price and  Quantity to be updated ");
			price = Main.scanner.next();
			quantity = Main.scanner.next();
			
			statement.executeUpdate("Update pdts set Price='" + price + "',"
							+ "Quantity='" + quantity + "'where PdtName='" +productName + "'");
			
			System.out.println("Data has been updated");
			System.out.println("Do you need to update elements again" + " " + "Press y/n");
			choice = Main.scanner.next().charAt(0);
		} while (choice == 'y' || choice == 'Y');
	}

	@Override
	public void delete() throws SQLException {
		final Connection connection = getConnection();
		final Statement statement = connection.createStatement();
	
		do {
			 productName = Main.scanner.next();
			 brandName = Main.scanner.next();
			
			 statement.executeUpdate("Delete from pdts where PdtName='" + productName + "'and BrandName='" 
					 + brandName + "'");
			
			System.out.println("Data has been Deleted");
			System.out.println("Do you need to delete elements again" + " " + "Press y/n");
			choice = Main.scanner.next().charAt(0);
		} while (choice == 'y' || choice == 'Y');
	}
}



