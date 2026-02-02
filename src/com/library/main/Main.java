package com.library.main;

import com.library.model.Book;
import com.library.service.LibraryService;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        LibraryService service = new LibraryService();
        Scanner sc = new Scanner(System.in);

        // LOGIN
        System.out.println("===== LOGIN =====");
        System.out.print("Username: ");
        String user = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();

        if (!service.login(user, pass)) {
            System.out.println("❌ Invalid login. Exiting...");
            return;
        }

        System.out.println("✅ Login successful!");

        // MENU
        while (true) {
            System.out.println("\n===== LIBRARY MENU =====");
            System.out.println("1. Add Book");
            System.out.println("2. Search Book");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Author: ");
                    String author = sc.nextLine();
                    service.addBook(new Book(id, title, author, false));
                    break;

                case 2:
                    sc.nextLine();
                    System.out.print("Enter keyword: ");
                    service.searchBook(sc.nextLine());
                    break;

                case 3:
                    System.out.print("Enter ID to issue: ");
                    service.issueBook(sc.nextInt());
                    break;

                case 4:
                    System.out.print("Enter ID to return: ");
                    service.returnBook(sc.nextInt());
                    break;

                case 5:
                    System.out.println("👋 Exiting system...");
                    System.exit(0);
            }
        }
    }
}
