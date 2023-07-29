import java.util.ArrayList;

public class Library {
    private ArrayList<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public boolean removeBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                books.remove(book);
                return true;
            }
        }
        return false;
    }

    public Book searchBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    public void displayBooks() {
        for (Book book : books) {
            System.out.println("Title: " + book.getTitle() + ", Author: " + book.getAuthor());
        }
    }
}
