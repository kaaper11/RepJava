package pd19.dto;

import java.time.LocalDate;

public record LoanDto(BookDto bookDto, MemberDto memberDto, LocalDate borrowedAt, LocalDate dueDate,
                      LocalDate returnedAt) {
}
