package pd19.service.ServClass;

import lombok.AllArgsConstructor;
import pd19.dto.BookDto;
import pd19.entity.Book;
import pd19.exception.BookNotFoundException;
import pd19.mapper.BookMapper;
import pd19.repository.BookRepository;
import pd19.service.ServInteface.BookService;

import java.util.List;

@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    @Override
    public void addBook(BookDto book) {
        bookRepository.save(BookMapper.mapToBook(book, bookRepository.getNextId()));
    }

    @Override
    public BookDto findByLsbn(String lsbn) {
        Book book = bookRepository.getBookByIsbn(lsbn).orElseThrow(BookNotFoundException::new);
        return BookMapper.mapToBookDto(book);
    }

    @Override
    public List<BookDto> findAvailable() {
        return bookRepository.findAvailable().stream()
                .map(BookMapper::mapToBookDto)
                .toList();
    }

    @Override
    public List<BookDto> search(String query) {
        return bookRepository.search(query).stream()
                .map(BookMapper::mapToBookDto)
                .toList();
    }
}
