import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LibraryManagementTest {
    private Book validBook1; //Task3.1
    private Book validBook2; //Task3.1
    private Member member;   //Task 3.2
    private Transaction transaction; //Task 3.2
    
    
    //Task3.1
    @Before
    public void setUp() throws Exception {
        // Setting up valid Book objects
        validBook1 = new Book(100, "Valid Book 1");
        validBook2 = new Book(999, "Valid Book 2");
        member = new Member(1,"Test member."); //Task 3.2
        transaction = new Transaction();  //Task 3.2
    }

    //Task 3.1
    // Created  a  method  testBookId  in  which  I  will   instantiate  multiple  Book  objects 
    //using  the  following  data  
   // ○  Validate boundary cases: 100 (valid), 999 (valid), and 1000 (invalid). 
    //○  Test two invalid numbers: one less than 100 and another greater than 999. 
    //○  Ensure that the correct exception message is thrown for invalid IDs. 
    @Test
    public void testBookId() {
        try {
            // Testing valid IDs starting below
            assertNotNull(validBook1);
            assertEquals(100, validBook1.getId());
            assertEquals("Valid Book 1", validBook1.getTitle());

            assertNotNull(validBook2);
            assertEquals(999, validBook2.getId());
            assertEquals("Valid Book 2", validBook2.getTitle());
            
            
            // Valid IDs testing ends here

            // Testing invalid IDs
            Book invalidBook1 = null;
            Book invalidBook2 = null;
            Book invalidBook3 = null;

            try {
                invalidBook1 = new Book(1000, "Invalid Book 1");
                fail("Exception expected for ID 1000");
            } catch (Exception e) {
                assertEquals("Invalid book ID. Pleae try again1000", e.getMessage());
            }

            try {
                invalidBook2 = new Book(99, "Invalid Book 2");
                fail("Exception expected for ID 99");
            } catch (Exception e) {
                assertEquals("Invalid book ID. Pleae try again99", e.getMessage());
            }

            try {
                invalidBook3 = new Book(1001, "Invalid Book 3");
                fail("Exception expected for ID 1001");
            } catch (Exception e) {
                assertEquals("Invalid book ID. Pleae try again1001", e.getMessage());
            }

        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
    
    //Task 3.2
    
    @Test
    public void testBorrowReturn() {
        // Ensure the book is initially available
        assertTrue(validBook1.isAvailable());

        // Borrow the book
        assertTrue(transaction.borrowBook(validBook1, member));
        assertFalse(validBook1.isAvailable()); // Ensure the book is unavailable

        // Attempt to borrow the same book again
        assertFalse(transaction.borrowBook(validBook1, member));

        // Return the book
        assertTrue(transaction.returnBook(validBook1, member));
        assertTrue(validBook1.isAvailable()); // Ensure the book is available again

        // Attempt to return the book again
        assertFalse(transaction.returnBook(validBook1, member));
    }
}