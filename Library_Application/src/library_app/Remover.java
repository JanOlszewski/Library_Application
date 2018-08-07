package library_app;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Remover 
{
	public static void remove(Connection con, String choice) throws SQLException
	{
		Scanner scan = new Scanner(System.in);
		Statement stt = con.createStatement();
		ResultSet res = null;
		
		stt.execute("USE library");
		
		choice.toUpperCase();
		if(choice.equals("BORROWING"))
		{
			Finder.find_borrowing(con);
			System.out.print("\nChoose the borrowing ID number: ");
			int id = scan.nextInt();
			
			res = stt.executeQuery("SELECT * FROM borrowings WHERE borrowing_id = " + id);
			res.next();
			stt.execute("UPDATE books SET quantity = quantity + 1 WHERE idb = " + res.getInt("idb"));
			stt.execute("DELETE FROM borrowings WHERE borrowings.borrowing_id = " + id);
		}
		else if(choice.equals("CUSTOMER"))
		{
			Finder.find_customer(con);
			System.out.print("Choose the customer ID number: ");
			int id = scan.nextInt();
			stt.execute("DELETE FROM customers WHERE customers.idc = " + id);
		}
		else if(choice.equals("BOOK"))
		{
			Finder.find_book(con);
			System.out.print("Choose the book ID number: ");
			int id = scan.nextInt();
			stt.execute("DELETE FROM books WHERE books.idb = " + id);
		}
		else 
		{ 
			System.out.println("Function declaration error!"); 
			stt.close();
			res.close();
			System.exit(0); 
		}
		
		System.out.println("Removal comleted successfully");
		
		stt.close();
		res.close();
	}
}
