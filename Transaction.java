import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    public void returnBook(Book book, Member member) {
        if (member.getBorrowedBooks().contains(book)) {
            member.returnBook(book);
            book.returnBook();
            String transactionDetails = getCurrentDateTime() + " - Returning: " + member.getName() + " returned " + book.getTitle();
            System.out.println(transactionDetails);
         // Task 2.2
            //Save to file
            saveTransaction(transactionDetails);
        } else {
            System.out.println("This book was not borrowed by the member.");
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
    	try(FileWriter writer = new FileWriter("transactions.txt",true))
    	{
    		writer.write(transactionDetails + "\n");
    	}
    	catch(IOException e)
    	{
    		System.err.println("Error saving transaction:(" + e.getMessage());
    	}
    }

}