
package com.kiet.library.service;

import com.kiet.library.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final List<Book> books = new ArrayList<>();

    public BookService() {
        books.add(new Book(201, "Effective Java", "Joshua Bloch", "Programming", 2018, true));
        books.add(new Book(202, "Head First Java", "Kathy Sierra", "Programming", 2005, true));
        books.add(new Book(203, "Sapiens", "Yuval Noah Harari", "History", 2011, false));
        books.add(new Book(204, "Wings of Fire", "A.P.J. Abdul Kalam", "Biography", 1999, true));
        books.add(new Book(205, "Introduction to Algorithms", "Thomas H. Cormen", "Computer Science", 2009, true));
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public Book getBookById(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }

    public Book addBook(Book book) {
        if (getBookById(book.getBookId()) != null) {
            return null;
        }
        books.add(book);
        return book;
    }

    public Book updateBook(int bookId, Book updatedBook) {
        Book existing = getBookById(bookId);
        if (existing == null) {
            return null;
        }
        existing.setTitle(updatedBook.getTitle());
        existing.setAuthor(updatedBook.getAuthor());
        existing.setCategory(updatedBook.getCategory());
        existing.setPublicationYear(updatedBook.getPublicationYear());
        existing.setAvailable(updatedBook.isAvailable());
        return existing;
    }

    public boolean deleteBook(int bookId) {
        Book existing = getBookById(bookId);
        if (existing == null) {
            return false;
        }
        books.remove(existing);
        return true;
    }

    public List<Book> searchByCategory(String category) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getCategory().equalsIgnoreCase(category)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> findPublishedFromYear(int fromYear) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getPublicationYear() >= fromYear) {
                result.add(book);
            }
        }
        return result;
    }
}