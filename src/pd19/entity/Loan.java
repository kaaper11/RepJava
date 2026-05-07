package pd19.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Loan {
    private long id;
    private Book book;
    private Member member;
    private LocalDate borrowedAt;
    private LocalDate dueDate;
    private LocalDate returnedAt;

    public Loan(long id, Book book, Member member, LocalDate borrowedAt, LocalDate dueDate) {
        this.id = id;
        this.book = book;
        this.member = member;
        this.borrowedAt = borrowedAt;
        this.dueDate = dueDate;
        this.returnedAt = null;
    }

    public boolean isOverdue() {
        return this.isActive() && dueDate.isBefore(LocalDate.now());
    }

    public void returnBook() {
        returnedAt = LocalDate.now();
        book.returnBook();
    }

    public boolean isActive(){
        return returnedAt == null;
    }
}
