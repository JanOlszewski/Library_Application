package library_app;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Remover 
{
	public static void remove_borrowing(Connection con) throws SQLException
	{
		Scanner scann = new Scanner(System.in);
		Statement stt = con.createStatement();
		ResultSet res = null;
		
		stt.execute("USE library");
		
		Finder.find_borrowing(con);
		System.out.print("\nChoose the borrowing ID number: ");
		int ID = scann.nextInt();
		
		res = stt.executeQuery("SELECT * FROM borrowings WHERE borrowing_id = " + ID);
		res.next();
		stt.execute("UPDATE books SET quantity = quantity + 1 WHERE idb = " + res.getInt("idb"));
		stt.execute("DELETE FROM borrowings WHERE borrowings.borrowing_id = " + ID);
		
		System.out.println("Removal completed successfully");
		
		stt.close();
		res.close();
	}
	
	public static void remove_customer(Connection con) throws SQLException
	{
		Scanner scan = new Scanner(System.in);
		Statement stt = con.createStatement();
		
		stt.execute("USE library");
		
		Finder.find_customer(con);
		System.out.print("Choose the customer ID number: ");
		int ID = scan.nextInt();
		
		stt.execute("DELETE FROM customers WHERE customers.idc = " + ID);
		
		System.out.println("Removal completed successfully");
		
		stt.close();
	}
	
	public static void remove_book(Connection con) throws SQLException
	{
		Scanner scan = new Scanner(System.in);
		Statement stt = con.createStatement();
		
		stt.execute("USE library");
		
		Finder.find_book(con);
		System.out.print("Choose the book ID number: ");
		int ID = scan.nextInt();
		
		stt.execute("DELETE FROM books WHERE books.idb = " + ID);
		
		System.out.println("Removal comleted successfully");
		
		stt.close();
	}
}
