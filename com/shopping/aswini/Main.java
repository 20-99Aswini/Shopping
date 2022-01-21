package com.shopping.aswini;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	protected static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {
		System.out.println("1.Admin or 2. User");
		int choice = scanner.nextInt();
		System.out.println("1. New User or 2. Existing User");
		int typeOfUser = scanner.nextInt();

		if (((choice == 1) || (choice == 2)) && (typeOfUser == 1)) {
			LoginPage.signUp(choice, typeOfUser);
		} else if (((choice == 1) || (choice == 2)) && (typeOfUser == 2)) {
			LoginPage.signIn(choice, typeOfUser);
		}
	}
}
