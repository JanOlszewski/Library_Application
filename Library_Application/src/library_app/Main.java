package library_app;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main 
{
	public static void main(String[] args) throws SQLException, IOException, 
	InstantiationException, IllegalAccessException, ClassNotFoundException 
	{
		Scanner scan = new Scanner(System.in);
		Connection con = Connector.getConnection();
		
		int CHOICE;
		
		for(;;)
		{
			System.out.println("\n|  |-------------------|");
			System.out.println("| 1| Add    Book       |");
			System.out.println("| 2| Add    Client     |");
			System.out.println("| 3| Add    Borrowing  |");
			System.out.println("| 4| Delete Book       |");
			System.out.println("| 5| Delete Client     |");
			System.out.println("| 6| Delete Borrowing  |");
			System.out.println("| 7| Find   Book       |");
			System.out.println("| 8| Find   Client     |");
			System.out.println("| 9| Find   Borrowing  |");
			System.out.println("|10| Create SQL Request|");
			System.out.println("|  |-------------------|");
			System.out.println("| 0| CLOSE THE PROGRAM |");
			System.out.println("|  |-------------------|");
			
			System.out.print("|  |Dial the number: ");
			CHOICE = scan.nextInt();
			
			if(CHOICE == 0) { break; }
			else if(CHOICE == 1) { Creator.createBook(con); }
			else if(CHOICE == 2) { Creator.createClient(con); }
			else if(CHOICE == 3) { Creator.createBorrowing(con); }
			else if(CHOICE == 4) { Remover.remove_book(con); }
			else if(CHOICE == 5) { Remover.remove_customer(con); }
			else if(CHOICE == 6) { Remover.remove_borrowing(con); }
			else if(CHOICE == 7) { Finder.find_book(con); }
			else if(CHOICE == 8) { Finder.find_customer(con); }
			else if(CHOICE == 9) { Finder.find_borrowing(con); }
			else if(CHOICE == 10) { Finder.find_your_own(con); }
			else { System.out.println("There is no option on the menu!\nTry again.\n"); }
		}
		
		con.close();
	}
}




