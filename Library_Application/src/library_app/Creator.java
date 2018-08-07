package library_app;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Creator 
{
	public static void create_book(Connection con) throws SQLException
	{
		Scanner scan = new Scanner(System.in);
		Statement stt = con.createStatement();
		
		stt.execute("USE library");
		stt.execute("CREATE TABLE IF NOT EXISTS books (" +
					"idb BIGINT NOT NULL AUTO_INCREMENT," +
					"title VARCHAR(30), " +
					"author VARCHAR(30), " +
					"publishing_house VARCHAR(30), " +
					"quantity int, " +
					"PRIMARY KEY(idb))");
		
		System.out.print("Enter data\nTitle: ");
		String title = scan.nextLine();
		System.out.print("Author: ");
		String author = scan.nextLine();
		System.out.print("Publishing house: ");
		String publishing_house = scan.nextLine();
		System.out.print("Quantity: ");
		int quantity = scan.nextInt();
		
		stt.execute("INSERT INTO books (title, author, publishing_house, quantity) VALUES" +
					"('" + title + "', '" + author + "', '" + publishing_house + "', '" + quantity + "')");
		
		System.out.println("successfully added");
		
		stt.close();
	}
	
	public static void create_client(Connection con) throws SQLException
	{
		Scanner scan = new Scanner(System.in);
		Statement stt = con.createStatement();
		
		stt.execute("USE library");
		stt.execute("CREATE TABLE IF NOT EXISTS customers (" +
				"idc BIGINT NOT NULL AUTO_INCREMENT, " +
				"fname VARCHAR(30), " +
				"lname VARCHAR(30), " +
				"adress VARCHAR(30), " +
				"PRIMARY KEY(idc))");
		
		System.out.print("Enter the first name: ");
		String fname = scan.nextLine();
		System.out.print("Enter the last name: ");
		String lname = scan.nextLine();
		System.out.print("Enter the adress: ");
		String adress = scan.nextLine();
		
		stt.execute("INSERT INTO customers (fname, lname, adress) VALUES" +
					"('" + fname + "', '" + lname + "', '" + adress + "')");
		
		System.out.println("successfully added");
		
		stt.close();
	}
	
	public static void create_borrowing(Connection con) throws SQLException
	{
		Scanner scan = new Scanner(System.in);
		Statement stt = con.createStatement();
		ResultSet res = null;
		
		stt.execute("USE library");
		stt.execute("CREATE TABLE IF NOT EXISTS borrowings (" +
					"borrowing_id BIGINT NOT NULL AUTO_INCREMENT, " +
					"idc int, " +
					"idb int, " +
					"PRIMARY KEY(borrowing_id))");
		
		Finder.find_customer(con);
		System.out.print("\nEnter the customer identification number: ");
		int idc = scan.nextInt(); 
		Finder.find_book(con);
		System.out.print("\nEnter the book identification number: ");
		int idb = scan.nextInt();     
		
		res = stt.executeQuery("SELECT * FROM books WHERE idb = " + idb);
		res.next();
		if(res.getInt("idb") > 0)
		{
			stt.execute("INSERT INTO borrowings (idc, idb) VALUES ('" + idc + "', '" + idb + "')");
			stt.execute("UPDATE books SET quantity = quantity - 1 WHERE idb = " + idb);
			
			System.out.println("successfully added");
		}
		else
		{
			System.out.println("The selected book is not available");
		}
		
		stt.close();
		res.close();
	}
}
