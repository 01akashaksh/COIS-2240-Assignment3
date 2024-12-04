import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Member> members = new ArrayList<>();
    private List<Book> books = new ArrayList<>();

    // Add a new member to the library
    // Task 2.4
    public boolean addMember(Member member) {
    	if(findMemberById(member.getId())!= null) 
    	{
    		System.out.println(""+ member.getId() + "already exits.");
    		return false; // Same ID exists
    	}
    	
        members.add(member);
        return true; // added successfully
    }
    
    // Add a new book to the library
    // Task 2.4
    public boolean addBook(Book book) {
    	if(findBookById(book.getId()) != null)
    	{
    		System.out.println("Error: Book with ID" + book.getId() + "already exists.");
    		return false;//Same ID exists
    	}
        books.add(book);
        return true;// added successfully
    }

    // Find a member by ID
    public Member findMemberById(int id) {
        for (Member member : members) {
            if (member.getId() == id) {
                return member;
            }
        }
        return null;
    }

    // Find a book by ID
    public Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    // Get the list of members
    public List<Member> getMembers() {
        return members;
    }
    
    // Get the list of books
    public List<Book> getBooks() {
        return books;
    }
}
