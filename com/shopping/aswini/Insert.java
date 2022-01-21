package com.shopping.aswini;

import java.sql.SQLException;

public class Insert extends ConnectionManagement {
	private final InsertDAO id = new InsertDAO();
	
	public void insert() throws SQLException {
		char choice = ' ';
		
		do {
			System.out.println("Enter all credentials:");
			System.out.println("PdtName,BrandName,Price,Person,Colour1,Colour2,Size,Quantity,Review");
			
			String productName = Main.scanner.next();
			String brandName = Main.scanner.next();
			String price = Main.scanner.next();
			String person = Main.scanner.next();
			String colour1 = Main.scanner.next();
			String colour2 = Main.scanner.next();
			String size = Main.scanner.next();
			String quantity = Main.scanner.next();
			String review = Main.scanner.next();
			
			PojoInsert d = new PojoInsert(productName, brandName, price, person, colour1, colour2, size, 
					quantity, review);
			System.out.println(d);
			id.insert(d);
			System.out.println("Do you need to insert elements again");
			choice = Main.scanner.next().charAt(0);

		} while (choice == 'y' || choice == 'Y');
	}
}
