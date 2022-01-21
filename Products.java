import java.sql.*;
import java.util.*;

public class Products {
	public Connection getConnection() throws SQLException {

		Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopapp", "root", "N@tt@r99");
		return c;

	}

	public void show() throws SQLException {
		Connection c = getConnection();
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery("Select * from pdts");
		ResultSetMetaData rsmd1 = rs.getMetaData();
		int n = rsmd1.getColumnCount();
		while (rs.next()) {
			for (int i = 1; i <= n; i++)
				System.out.print(rs.getString(i) + "\t");
			System.out.println();
		}
	}

	public void create() throws SQLException {
		Scanner sc = new Scanner(System.in);
		Connection c = getConnection();
		Statement st = c.createStatement();
		char ch = ' ';
		do {
			System.out.println("PdtName,BrandName,Price,Person,Colour1,Colour2,Size,Quantity,Review");
			String PdtName = sc.next();
			String BrandName = sc.next();
			String Price = sc.next();
			String Person = sc.next();
			String Colour1 = sc.next();
			String Colour2 = sc.next();
			String Size = sc.next();
			String Quantity = sc.next();
			String Review = sc.next();

			PreparedStatement p = c.prepareStatement("Insert into pdts values(?,?,?,?,?,?,?,?,?)");
			p.setString(1, PdtName);
			p.setString(2, BrandName);
			p.setString(3, Price);
			p.setString(4, Person);
			p.setString(5, Colour1);
			p.setString(6, Colour2);
			p.setString(7, Size);
			p.setString(8, Quantity);
			p.setString(9, Review);
			p.executeUpdate();

			System.out.println("Data has been Inserted successfully");
			System.out.println("Do you need to insert elements again" + " " + "Press y/n");
			Scanner sc2 = new Scanner(System.in);
			ch = sc2.next().charAt(0);

		} while (ch == 'y' || ch == 'Y');
	}

	public void search() throws SQLException {
		Scanner sc = new Scanner(System.in);
		Scanner sc1 = new Scanner(System.in);
		Products p=new Products();
		String out = " ";
		String PdtName=" ";
		int choice = 0;
		Connection c = getConnection();
		Statement st = c.createStatement();
		char ch = ' ';
		do {
		System.out.println("By what criteria u want to search 1. PdtName  2. For whom");
		choice = sc1.nextInt();
		if (choice == 1) {
			System.out.println("What is the pdt u want to search?");
			 PdtName = sc.next();
			ResultSet rs = st.executeQuery("Select * from pdts where PdtName='" + PdtName + "'");
			ResultSetMetaData rsmd = rs.getMetaData();
			int n = rsmd.getColumnCount();
			while (rs.next()) {
				out = rs.getString(1);

				if (out.equals(PdtName)) {

					for (int i = 1; i <= n; i++)
						System.out.print(rs.getString(i) + "\t");
					System.out.println();
				}
			}
			if (out.equals(PdtName))
				System.out.println("Search Found");
			else
			
				System.out.println("No result found");
		
		} else if (choice == 2) {
			System.out.println("Product for Men or Women or both");
			String Person = sc.next();
			System.out.println("What is the pdt u want to search?");
			 PdtName = sc.next();
			ResultSet rs = st.executeQuery("Select * from pdts where Person='" + Person + "'and PdtName='"+PdtName+"'");
			ResultSetMetaData rsmd = rs.getMetaData();
			int n = rsmd.getColumnCount();
			while (rs.next()) {
				out = rs.getString(4);

				if (out.equals(Person)) {

					for (int i = 1; i <= n; i++)
						System.out.print(rs.getString(i) + "\t");
					System.out.println();
				}
			}
			if (out.equals(Person))
				System.out.println("Search Found");
			else 
				System.out.println("No result found");
			
		}System.out.println("Would you like to do  Booking ");
		Scanner sc2 = new Scanner(System.in);
		char ch5 = sc2.next().charAt(0);

		if (ch5 == 'y' || ch5== 'Y') {

			p.booking(PdtName);
		}

		ch = sc2.next().charAt(0);

	} while (ch == 'y' || ch == 'Y');
		
	}
	public void update() throws SQLException
	{	Scanner sc = new Scanner(System.in);
		Connection c = getConnection();
		Statement st = c.createStatement();
		char ch=' ';
		do
		{
		System.out.println("Enter Product to be updated");
		String PdtName=sc.next();
		System.out.println("Enter Price and  Quantity to be updated ");
		String Price=sc.next();
		String Quantity=sc.next();
		st.executeUpdate("Update pdts set Price='"+Price+"',Quantity='"+Quantity+"'where PdtName='"+PdtName+"'");
		System.out.println("Data has been updated");
		System.out.println("Do you need to update elements again" + " " + "Press y/n");
		Scanner sc2 = new Scanner(System.in);
		ch = sc2.next().charAt(0);

	} while (ch == 'y' || ch == 'Y');
	}
	public void delete() throws SQLException
	{
		Scanner sc = new Scanner(System.in);
		Connection c = getConnection();
		Statement st = c.createStatement();
		char ch=' ';
		do
		{
		System.out.println("Enter the product and BrandName to be deleted");
		String PdtName=sc.next();
		String BrandName=sc.next();
		st.executeUpdate("Delete from pdts where PdtName='"+PdtName+"'and BrandName='"+BrandName+"'");
		System.out.println("Data has been Deleted");
		System.out.println("Do you need to delete elements again" + " " + "Press y/n");
		Scanner sc2 = new Scanner(System.in);
		ch = sc2.next().charAt(0);

	} while (ch == 'y' || ch == 'Y');
		
	}
	public void booking(String PdtName) throws SQLException {
		Connection c = getConnection();
		Statement st = c.createStatement();
		Products p=new Products();
		int qty = 0;
		Scanner sc1=new Scanner(System.in);
		int no = 0, Total = 0;
		int Amt = 0;
		System.out.println("No.of pieces you need");
		no = sc1.nextInt();
		ResultSet rs = st.executeQuery("Select * from pdts where PdtName='" + PdtName+"'");

		while (rs.next()) {
			qty = rs.getInt(8);
			Amt = rs.getInt(3);
			Total = Amt * no;

		}
		if (no < qty) {

			qty = qty - no;

			st.executeUpdate("Update pdts set Quantity='" + qty + "' where PdtName='" + PdtName + "'");
		
			System.out.println("you have booked " + no +" " + PdtName + " Total amount:" + Total);
			System.out.println("Do you like to place any order then press y/n");
			Scanner sc2 = new Scanner(System.in);
			char ch5 = sc2.next().charAt(0);

			if (ch5 == 'y' || ch5== 'Y') {

				p.search();
			}

		} else
			System.out.println("You can book only " + qty + "\t" + PdtName);

	}

	

}
