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
		
		int choice;
		boolean b = true;
		
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
			choice = scan.nextInt();
			
			switch(choice)
			{
			case 0: b = false;
				break;
			case 1: Creator.create_book(con);
				break;
			case 2: Creator.create_client(con);
				break;
			case 3: Creator.create_borrowing(con);
				break;
			case 4: Remover.remove(con, "BOOK");
				break;
			case 5: Remover.remove(con, "CUSTOMER");
				break;
			case 6: Remover.remove(con, "BORROWING");
				break;
			case 7: Finder.find_book(con);
				break;
			case 8: Finder.find_customer(con);
				break;
			case 9: Finder.find_borrowing(con);
				break;
			case 10: Finder.find_your_own(con);
				break;
			default: System.out.println("There is no such option on the menu!");
				break;
			}
			
			if(b == false) { break; }
		}
		
		con.close();
	}
}




