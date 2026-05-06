package pd19.service.ServClass;

import pd19.dto.BookDto;
import pd19.entity.Book;
import pd19.exception.BookNotFoundException;
import pd19.mapping.BookMapping;
import pd19.repository.BookRepository;
import pd19.service.ServInteface.BookService;

import java.util.List;

public class BookServiceImp implements BookService {
    private BookRepository bookRepository;

    public BookServiceImp(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void addBook(BookDto book) {
        bookRepository.save(BookMapping.bookMapping(book, bookRepository.getId()));
    }

    @Override
    public BookDto findByLsbn(String lsbn) {
        Book book = bookRepository.getBookByIsbn(lsbn).orElseThrow(BookNotFoundException::new);
        return BookMapping.bookDtoMapping(book);
    }

    @Override
    public List<BookDto> findAvailable() {
        return bookRepository.findAvailable().stream()
                .map(BookMapping::bookDtoMapping)
                .toList();
    }

    @Override
    public List<BookDto> search(String query) {
        return bookRepository.search(query).stream()
                .map(BookMapping::bookDtoMapping)
                .toList();
    }
}
