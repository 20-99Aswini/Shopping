package com.shopping.aswini;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.InputMismatchException;

public class LoginPage {
	public static String eMailId ="";
	public static String password = " ";
	public static String key = " ";
	public static String emailOut =" ";
	public static String passwordOut = " ";
	protected  static String encodedIp =" ";
	private static final  User user = new User();
	private static final Admin admin = new Admin();
	private static final Insert insert = new Insert();
	 
	public static Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopapp",
				"root", "N@tt@r99");
		return connection;
	}

	public static void signUp(int choice, int typeOfUser) throws SQLException {
		final Connection connection = getConnection();
		final Statement statement = connection.createStatement();
		
		System.out.println("Online Shopping SignUp Page");
		System.out.println("Enter EMailId");
		eMailId = Main.scanner.next();

		while (!(eMailId.length() > 10) && !(eMailId.contains("@gmail.com"))) {
			System.out.println("EMail Id is wrong. Reenter it");
			eMailId = Main.scanner.next();
		}

		ResultSet resultSet = statement.executeQuery("Select * from Admin where EMailId='" + eMailId + "'");
		
		while (resultSet.next()) {
			emailOut = resultSet.getString(2);
		
			if (emailOut.equals(eMailId)) {
				System.out.println("MailId already exists");
				signUp(choice, typeOfUser);
			}
		}

		ResultSet resultSet1 = statement.executeQuery("Select * from User where EMailId='" + eMailId + "'");
		
		while (resultSet1.next()) {
			emailOut = resultSet1.getString(2);
			
			if (emailOut.equals(eMailId)) {
				System.out.println("MailId already exists");
				signUp(choice, typeOfUser);
			}
		}
		System.out.println("Enter Password");
		password = Main.scanner.next();
		String encodedInput = Base64.getEncoder().encodeToString(password.getBytes());
		
		while (!(password.matches(".*[a-z]{1,}.*") && (password.matches(".*[A-Z]{1,}.*")) 
				&& (password.matches(".*[0-9]{1,}.*")) && (password.matches(".*[@#$()!~%^&|*?.,]{1,}.*"))
				&& (password.length() <= 10) && (!password.contains(" ")))) {
			System.out.println("Password is invalid(Sample: Aswini!21  max:10 letters). Reenter it");
			password = Main.scanner.nextLine();
		}

		if ((choice == 1) && (typeOfUser == 1)) {
				System.out.println("Enter Key for admin");
				key =  Main.scanner.next();
			if (key.equals("admin")) {
					
				statement.executeUpdate(
						"Insert into Admin(EMailId,Password) values('" + eMailId + "','" + encodedInput + "')");
				
				System.out.println("Data Entered Successfully");
			}
		} else if ((choice == 2) && (typeOfUser == 1)) {
			
			statement.executeUpdate("Insert into User(EMailId,Password) values('" + eMailId + "','" 
					+ encodedInput + "')");
			
			System.out.println("Data Entered Successfully");
			user.showProducts();
			}
	}

	public static void signIn(int choice, int typeOfUser) throws SQLException {
			final Connection connection= getConnection();
			final Statement statement = connection.createStatement();
			final int preferredChoice;
			
			if ((choice == 1) && (typeOfUser == 2)) {
				System.out.println("Enter EMailId");
				eMailId =  Main.scanner.next();
				
				System.out.println("Enter Password");
				password =  Main.scanner.next();
				encodedIp = Base64.getEncoder().encodeToString(password.getBytes());
				
				try {
					ResultSet resultSet = statement.executeQuery("Select * from admin where EMailId='" + eMailId + "'");
					
					while (resultSet.next()) {
						emailOut = resultSet.getString(2);
						passwordOut = resultSet.getString(3);
						Base64.getDecoder().decode(passwordOut);
					}
					
					if (emailOut.equals(eMailId) && (passwordOut.equals(encodedIp))) {
						System.out.println("You are an admin!! Logged in Successfully");
						System.out.println("Available Products are: " + "\n");
						user.showProducts();
						System.out.println();
						System.out.println("What would u like to do? 1. Insert 2.Search 3.Update 4. Delete");
					    preferredChoice = Main.scanner.nextInt();
					
						if (preferredChoice == 1) {
							insert.insert();
						} else if (preferredChoice == 2) {
							user.search();
						} else if (preferredChoice == 3) {
							admin.update();
						} else if (preferredChoice == 4) {
							admin.delete();
						} else {
							System.out.println("Invalid input ");
							signIn(choice, typeOfUser);
						}
					}
				} catch (InputMismatchException ime) {
					System.out.println("User not found");
				}
		  } else {
			  System.out.println("Enter EMailId");
			  eMailId = Main.scanner.next();
			  
			  System.out.println("Enter Password");
			  password = Main.scanner.next();
			  encodedIp = Base64.getEncoder().encodeToString(password.getBytes());

			  try {
				ResultSet resultSet = statement.executeQuery("Select * from User where EMailId='" + eMailId + "'");
				
			      while (resultSet.next()) {
					  emailOut = resultSet.getString(2);
					  passwordOut = resultSet.getString(3);
					  Base64.getDecoder().decode(passwordOut);
				  }
				
			      if (emailOut.equals(eMailId) && (passwordOut.equals(encodedIp))) {
					  System.out.println("You are an User!! Logged in Successfully");
					  System.out.println("Available Products are: " + "\n");
					  user.showProducts();
					  System.out.println();
					  user.search();
				  } else {
					  System.out.println("Invalid Input");
					  signIn(choice, typeOfUser);
				  }
			  } catch (InputMismatchException ime) {
				System.out.println("Not found");
		 	  }
		  } 
    }
}

