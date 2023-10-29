package usd;

public class Book {

    private String title;
    private String author;
    private String isbn;
    private String shelfLocation;

    public Book(String title, String author, String isbn, String shelfLocation) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.shelfLocation = shelfLocation;
    }

    // Getter methods
    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public String getShelfLocation() {
        return this.shelfLocation;
    }

    // Setter methods
    public void setShelfLocation(String shelfLocation) {
        this.shelfLocation = shelfLocation;
    }

}
