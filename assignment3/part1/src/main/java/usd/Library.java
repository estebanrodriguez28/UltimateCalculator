package usd;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private ArrayList<Book> library;

    public Library() {
        this.library = new ArrayList<>();
    }

    public void addBook(Book book) {
        this.library.add(book);
    }

    public List<Book> find(String[] bookAttributes) {
        ArrayList<Book> findList = new ArrayList<>();

        // Throw error if more than four arguments
        if (bookAttributes.length > 4) {throw new IllegalArgumentException("Invalid number of attributes"); }

        // Iterate over all books and inputs
        for (int i = 0; i < library.size(); i++) {

            Boolean valid = true;

            for (int j = 0; j < bookAttributes.length; j++) {

                // Split input into parts
                String attributeName = (bookAttributes[i].split(':'))[0];
                String attribute = (bookAttributes[i].split(':'))[1];

                // Switch statement over input to determine if book is to be included in results
                switch (attributeName) {
                    case "title":
                        if (!library.get(i).getTitle().equals(attribute)) { valid = false; }
                        break;
                    case "author":
                        if (!library.get(i).getAuthor().equals(attribute)) { valid = false; }
                        break;
                    case "isbn":
                        if (!library.get(i).getIsbn().equals(attribute)) { valid = false; }
                        break;
                    case "shelfLocation":
                        if (!library.get(i).getShelfLocation().equals(attribute)) { valid = false; }
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid attribute");
                }
            }

            // Add to results if book is valid
            if (valid == true) { findList.add(library.get(i)); }

        }

    }
}
