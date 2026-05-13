package pd19.service.ServInteface;

import pd19.dto.BookDto;

import java.util.List;

public interface BookService {
    void addBook(BookDto bookDto);

    BookDto findByLsbn(String lsbn);

    List<BookDto> findAvailable();

    List<BookDto> search(String query);
}
