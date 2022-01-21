package com.shopping.aswini;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class User extends  ConnectionManagement implements UserProduct {
	protected String out=" ";
	protected String productName= " ";
	protected final User user = new User();
	
	@Override
	public void showProducts() throws SQLException {
		final Connection connection = getConnection();
		final Statement statement = connection.createStatement();
		final ResultSet resultSet = statement.executeQuery("Select * from pdts");
		final ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
		int n = resultSetMetaData.getColumnCount();
		
		while (resultSet.next()) {
			for (int i = 1; i <= n; i++) {
				System.out.print(resultSet.getString(i) + "\t");
			}
				System.out.println();
		}
	}

	@Override
	public void search() throws SQLException {
		int choice = 0;
		char bookingChoice = ' ';
	    char searchingChoice=' ';
		final Connection connection = getConnection();
		final Statement statement = connection.createStatement();
		
		System.out.println("By what criteria u want to search 1. PdtName  2. For whom");
		choice = Main.scanner.nextInt();
		
			if (choice == 1) {
				System.out.println("What is the pdt u want to search?");
				productName = Main.scanner.next();
			    final ResultSet resultSet = statement.executeQuery("Select * from pdts where PdtName Like '"
			    		+productName+"%'");
				final ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
				int n = resultSetMetaData.getColumnCount();
				
				while (resultSet.next()) {
					out = resultSet.getString(1);

					if (out.contains(productName)) {

						for (int i = 1; i <= n; i++) {
							System.out.print(resultSet.getString(i) + "\t");
						}
							System.out.println();
					}
				}
				
				if (out.contains(productName)) {
				    System.out.println("Search Found");
				    System.out.println("Would u like to place order? Press y/n");
				    bookingChoice = Main.scanner.next().charAt(0);
				    
				    if (bookingChoice == 'y' || bookingChoice == 'Y') {
					    user.booking(productName);
				    }
				} else {
					System.out.println("No result found");
				}
				System.out.println("would u like to search again? press y/n");
				searchingChoice=Main.scanner.next().charAt(0);
				
				if(searchingChoice=='y'||searchingChoice=='Y') {
					user.search();
				}
			} else if (choice == 2) {
				System.out.println("Product for Men or Women or both");
				String Person = Main.scanner.next();
				System.out.println("What is the pdt u want to search?");
				productName = Main.scanner.next();
				final ResultSet resultSet = statement.executeQuery("Select * from pdts where Person='" 
						+ Person + "'and PdtName='" + productName + "'");
				final ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
				int n = resultSetMetaData.getColumnCount();
				
				while (resultSet.next()) {
					out = resultSet.getString(4);

					if (out.equals(Person)) {

						for (int i = 1; i <= n; i++)
							System.out.print(resultSet.getString(i) + "\t");
						System.out.println();
					}
				}
				
				if (out.equals(Person)) {
					System.out.println("Search Found");
					System.out.println("Would you like to do  Booking ");
					bookingChoice = Main.scanner.next().charAt(0);

					if (bookingChoice == 'y' || bookingChoice == 'Y') {
						user.booking(productName);
					}
				} else {
					System.out.println("No result found");
				}
				System.out.println("would u like to search again? press y/n");
				searchingChoice=Main.scanner.next().charAt(0);
				
				if(searchingChoice=='y'||searchingChoice=='Y') {
					user.search();
				}
			}
		} 

	@Override
	public void booking(String productName) throws SQLException {
		final Connection connection = getConnection();
		final Statement statement = connection.createStatement();
		
		int quantity =0;
		int number = 0;
		int total = 0;
		int amount = 0;
		
		System.out.println("No.of pieces you need");
		number = Main.scanner.nextInt();
		
		ResultSet rs = statement.executeQuery("Select * from pdts where PdtName='" + productName + "'");
		
		while (rs.next()) {
			quantity = rs.getInt(8);
			amount = rs.getInt(3);
			total = amount * number;
		}
		
		if (number < quantity) {
			quantity = quantity - number;
			
			statement.executeUpdate("Update pdts set Quantity='" + quantity + "' where PdtName='" + productName + "'");
			
			System.out.println("you have booked " + number+ " " +productName + " Total amount:" + total);
			System.out.println("Do you like to place any order then press y/n");
			char ch5 = Main.scanner.next().charAt(0);

			if (ch5 == 'y' || ch5 == 'Y') {
				user.search();
			}
		} else {
			System.out.println("You can book only " + quantity + "\t" + productName);
		}
    }
}
