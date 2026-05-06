package pd19.mapping;

import pd19.dto.LoanDto;
import pd19.entity.Loan;

public class LoanMapping {
    public static LoanDto loanDtoMapping(Loan loan) {
        return new LoanDto(BookMapping.bookDtoMapping(loan.getBook()),
                MemberMapping.memberDtoMapping(loan.getMember()),
                loan.getBorrowedAt(), loan.getDueDate(), loan.getReturnedAt());
    }
}
