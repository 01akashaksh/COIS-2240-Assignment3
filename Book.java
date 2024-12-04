public class Book {
    private int id;
    private String title;
    private boolean available;

    //Task 3.1
    // calling  isValidId  before  processing  book  details  and  throw  an 
    // exception if the  ID  is invalid while making sure the constructor’s  declaration has  throws Exception  . 
    public Book(int id, String title) throws Exception {
    	if(!isValidId(id))
    	{
    		throw new Exception("Invalid book ID. Pleae try again" + id);
    	}
        this.id = id;
        this.title = title;
        this.available = true;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return available;
    }

    // Method to borrow the book
    public void borrowBook() {
        if (available) {
            available = false;
        }
    }

    // Method to return the book
    public void returnBook() {
        available = true;
    }

    // Method to check if a book id is valid
    public boolean isValidId(int id) {
        return id >= 100 && id <= 999;
    }
}