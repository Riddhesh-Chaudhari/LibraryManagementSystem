package com.library.service;

import com.library.model.Book;
import com.library.util.FileUtil;
import java.util.*;
import java.io.*;

public class LibraryService {

    private final String BOOK_FILE = "books.txt";
    private final String USER_FILE = "users.txt";

    // LOGIN
    public boolean login(String username, String password) throws IOException {
        List<String> users = FileUtil.readAll(USER_FILE);
        for (String line : users) {
            String[] parts = line.split(",");
            if (parts[0].equals(username) && parts[1].equals(password)) {
                return true;
            }
        }
        return false;
    }

    // ADD BOOK (with duplicate check)
    public void addBook(Book book) throws IOException {
        List<String> lines = FileUtil.readAll(BOOK_FILE);

        for (String line : lines) {
            String[] parts = line.split(",");
            if (Integer.parseInt(parts[0]) == book.getId()) {
                System.out.println("❌ Book ID already exists!");
                return;
            }
        }

        lines.add(book.toString());
        FileUtil.writeAll(BOOK_FILE, lines);
        System.out.println("✅ Book added successfully!");
    }

    // SEARCH BOOK
    public void searchBook(String keyword) throws IOException {
        List<String> lines = FileUtil.readAll(BOOK_FILE);
        boolean found = false;

        for (String line : lines) {
            if (line.toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(line);
                found = true;
            }
        }

        if (!found) {
            System.out.println("❌ No book found.");
        }
    }

    // ISSUE BOOK
    public void issueBook(int id) throws IOException {
        updateStatus(id, true);
    }

    // RETURN BOOK
    public void returnBook(int id) throws IOException {
        updateStatus(id, false);
    }

    // UPDATE STATUS
    private void updateStatus(int id, boolean status) throws IOException {
        List<String> lines = FileUtil.readAll(BOOK_FILE);
        List<String> updated = new ArrayList<>();
        boolean found = false;

        for (String line : lines) {
            String[] parts = line.split(",");
            if (Integer.parseInt(parts[0]) == id) {
                parts[3] = String.valueOf(status);
                found = true;
            }
            updated.add(String.join(",", parts));
        }

        if (found) {
            FileUtil.writeAll(BOOK_FILE, updated);
            System.out.println("✅ Status updated!");
        } else {
            System.out.println("❌ Book not found.");
        }
    }
}
