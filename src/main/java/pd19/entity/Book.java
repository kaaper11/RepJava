package pd19.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pd19.exception.BookNotAvailableException;

import java.util.Objects;

@Getter
@AllArgsConstructor
public class Book {
    private long id;
    private String isbn;
    private String title;
    private String author;
    private int year;
    private int availableCopies;

    public void borrow() {
        if (availableCopies == 0) {
            throw new BookNotAvailableException();
        } else {
            availableCopies--;
        }
    }

    public void returnBook() {
        availableCopies++;
    }

    public void addBookCopy() {
        availableCopies++;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(isbn);
    }
}
