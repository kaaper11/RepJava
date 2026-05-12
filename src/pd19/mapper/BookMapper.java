package pd19.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pd19.dto.BookDto;
import pd19.entity.Book;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BookMapper {

    public static BookDto mapToBookDto(Book book) {
        return new BookDto(book.getIsbn(), book.getTitle(), book.getAuthor(), book.getYear(), book.getAvailableCopies());
    }

    public static Book mapToBook(BookDto bookDto, long id) {
        return new Book(id, bookDto.isbn(), bookDto.title(), bookDto.author(), bookDto.year(), bookDto.availableCopies());
    }
}
