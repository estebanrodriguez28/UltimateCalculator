package usd;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
// assignment_3_dillon_timmer
class BookTest {

    private Book book;
    private String title = "Book Title";
    private String author = "Book Author";
    private String isbn = "0-061-96436-0";
    private String shelfLocation = "ABC123";

    @BeforeEach
    public void setUp() {
        book = new Book(title, author, isbn, shelfLocation);
    }

    @Test
    @DisplayName("Check if book.getTitle() getter method works correctly")
    public void getTitle() {
        assertEquals(book.getTitle(), title);
    }

    @Test
    @DisplayName("Check if book.getAuthor() getter method works correctly")
    public void getAuthor() {
        assertEquals(book.getAuthor(), author);
    }

    @Test
    @DisplayName("Check if book.getIsbn() getter method works correctly")
    public void getIsbn() {
        assertEquals(book.getIsbn(), isbn);
    }

    @Test
    @DisplayName("Check if book.getShelfLocation() getter method works correctly")
    public void getShelfLocation() {
        assertEquals(book.getShelfLocation(), shelfLocation);
    }

    @Test
    @DisplayName("Check if book.setShelfLocation() setter method works correctly")
    public void setShelfLocation() {
        String newSetLocation = "DEF456";
        book.setShelfLocation(newSetLocation);
        assertEquals(book.getShelfLocation(), newSetLocation);
    }

}