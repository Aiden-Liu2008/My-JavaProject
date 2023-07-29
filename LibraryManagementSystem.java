import java.util.Scanner;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Search Book");
            System.out.println("4. Display All Books");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter the title of the book: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter the author of the book: ");
                    String author = scanner.nextLine();
                    Book book = new Book(title, author);
                    library.addBook(book);
                    System.out.println("Book added successfully.");
                    break;
                case 2:
                    System.out.print("Enter the title of the book to remove: ");
                    String titleToRemove = scanner.nextLine();
                    if (library.removeBook(titleToRemove)) {
                        System.out.println("Book removed successfully.");
                    } else {
                        System.out.println("Book not found in the library.");
                    }
                    break;
                case 3:
                    System.out.print("Enter the title of the book to search: ");
                    String titleToSearch = scanner.nextLine();
                    Book foundBook = library.searchBook(titleToSearch);
                    if (foundBook != null) {
                        System.out.println("Book found in the library:");
                        System.out.println("Title: " + foundBook.getTitle());
                        System.out.println("Author: " + foundBook.getAuthor());
                    } else {
                        System.out.println("Book not found in the library.");
                    }
                    break;
                case 4:
                    System.out.println("All Books in the library:");
                    library.displayBooks();
                    break;
                case 5:
                    System.out.println("Exiting Library Management System.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
