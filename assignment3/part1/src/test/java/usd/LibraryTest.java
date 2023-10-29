package usd;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LibraryTest {

    private Library library;

    @BeforeEach
    public void setUp() {
        library = new Library();
    }

    @Test
    @DisplayName("Check if addBook() correctly adds book to the library")
    public void addBook() {

        // add new book to library
        String expectedTitle = "Test Title";
        Book newBook = new Book(expectedTitle, "Test Author", "1-1-1-1", "A3");
        library.addBook(newBook);

        // build library list and compare to added book's title
        List<Book> libraryList = library.find(new String[0]);
        assertEquals(libraryList.get(0).getTitle, expectedTitle);
    }

    @Test
    @DisplayName("Check if find() method returns list for called attribute")
    public void find() {

        // Add 4 books to the library
        library.addBook(new Book("Book 1", "Author 1", "1-1-1-1", "A1"));
        library.addBook(new Book("Book 2", "Author 2", "2-2-2-2", "B2"));
        library.addBook(new Book("Book 3", "Author 3", "3-3-3-3", "C3"));
        library.addBook(new Book("Book 4", "Author 4", "4-4-4-4", "D4"));

        // Check for each category and see if returned list has 1 item
        assertEquals(library.find(new String[]{"title:Book 1"}).size(), 1 );
        assertEquals(library.find(new String[]{"author:Author 2"}).size(), 1 );
        assertEquals(library.find(new String[]{"isbn:3-3-3-3"}).size(), 1 );
        assertEquals(library.find(new String[]{"shelfLocation:D4"}).size(), 1 );

    }

    @Test
    @DisplayName("Check if an invalid attribute throws IllegalArgumentException")
    void invalidFind(){
        assertThrows(IllegalArgumentException.class, () -> library.find(new String[]{"titles:Book 1"}));
    }

}