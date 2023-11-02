package usd;

public class LibrarySearch {
    public static void main(String[] args) {
        // Here we create a Book array for use in the remainder of this PSVM method. This does not need to be altered.
        Book[] books = {
                new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", "1234567890", "A1"),
                new Book("The Hobbit", "J.R.R. Tolkien", "0987654321", "B2"),
                new Book("Pride and Prejudice", "Jane Austen", "1122334455", "C3"),
                new Book("To Kill a Mockingbird", "Harper Lee", "2233445566", "D4"),
                new Book("1984", "George Orwell", "3344556677", "E5"),
                new Book("Brave New World", "Aldous Huxley", "4455667788", "F1"),
                new Book("The Great Gatsby", "F. Scott Fitzgerald", "5566778899", "G2"),
                new Book("Moby Dick", "Herman Melville", "6677889900", "H3"),
                new Book("Harry Potter and the Chamber of Secrets", "J.K. Rowling", "7788990011", "A1"),
                new Book("The Lord of the Rings: The Fellowship of the Ring", "J.R.R. Tolkien", "8899001122", "B5"),
        };
        Library library = new Library();

        // Add all books in list to library
        for (int i = 0; i < books.length; i++) {
            library.addBook(books[i]);
        }

        //  find matching books, and print matching books



    }
}