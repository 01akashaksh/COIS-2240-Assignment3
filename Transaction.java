import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Transaction {

	// Singleton instance-Task 2.1
	private static Transaction instance;
	
	//Private constructor-Taks2.1
	private Transaction() {}
	
	//Method to retrieve the single instance of Transaction-Task2.1
	public static Transaction getTransaction()
	{
		if(instance == null)
		{
			instance = new Transaction();
		}
		return instance;
	}
    // Perform the borrowing of a book
	//removed static to make it instance method
    public boolean borrowBook(Book book, Member member) {
        if (book.isAvailable()) {
            book.borrowBook();
            member.borrowBook(book); 
            String transactionDetails = getCurrentDateTime() + " - Borrowing: " + member.getName() + " borrowed " + book.getTitle();
            System.out.println(transactionDetails);
            // Task 2.2
            //Save to file
            saveTransaction(transactionDetails);
            return true;
        } else {
            System.out.println("The book is not available.");
            return false;
        }
    }

    // Perform the returning of a book
  //removed static to make it instance method
    public boolean returnBook(Book book, Member member) {
        if (member.getBorrowedBooks().contains(book)) {
            member.returnBook(book);
            book.returnBook();
            String transactionDetails = getCurrentDateTime() + " - Returning: " + member.getName() + " returned " + book.getTitle();
            System.out.println(transactionDetails);
         // Task 2.2
            //Save to file
            saveTransaction(transactionDetails);
            return true; //Task3.2 Return Success
        } else {
            System.out.println("This book was not borrowed by the member.");
            return false; //Task3.2 Return failure
        }
    }

    // Get the current date and time in a readable format
  //removed static to make it instance method
    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
    
    // Task 2.2
    // Save transaction details in a file  when  the  program  exits,  from  borrowing  and  returning  transactions
    private void saveTransaction(String transactionDetails)
    {
    	try
    	{
    		BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.txt", true)); // Using John Video
            writer.write(transactionDetails);
            writer.write("\n");
            writer.close();
    	}
    	catch(IOException e)
    	{
    		System.err.println("Error saving transaction:(" + e.getMessage());
    	}
    }

    // Task 2.3
    //Method to display transaction history
    public void displayTransactionHistory() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("transactions.txt"));
            String line;
            System.out.println("\n--- Transaction History ---");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading transaction history: " + e.getMessage());
        }
    }
}
