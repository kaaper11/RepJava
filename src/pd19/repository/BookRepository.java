package pd19.repository;

import pd19.entity.Book;
import pd19.exception.BookNotFoundException;

import java.util.*;

public class BookRepository {
    private Set<Book> books = new HashSet<>();
    private long idCounter = 0;

    public void save(Book book) {
        if (!books.contains(book)) {
            books.add(book);
        } else {
            book.addBookCopy();
        }
    }

    public Optional<Book> getBookByIsbn(String isbn) {
        return books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst();
    }

    public Optional<Book> getBookById(long id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst();
    }

    public List<Book> findAvailable() {
        return books.stream()
                .filter(book -> book.getAvailableCopies() > 0)
                .toList();
    }

    public List<Book> search(String query) {
        return books.stream()
                .filter(book -> book.getAuthor().equals(query) || book.getTitle().equals(query))
                .toList();
    }

    public long getId() {
        return idCounter++;
    }
}
