package pd19.mapping;

import pd19.dto.BookDto;
import pd19.entity.Book;

public class BookMapping {

    public static BookDto bookDtoMapping(Book book) {
        return new BookDto(book.getIsbn(), book.getTitle(), book.getAuthor(), book.getYear(), book.getAvailableCopies());
    }

    public static Book bookMapping(BookDto bookDto, long id) {
        return new Book(id, bookDto.isbn(), bookDto.title(), bookDto.author(), bookDto.year(), bookDto.availableCopies());
    }
}
