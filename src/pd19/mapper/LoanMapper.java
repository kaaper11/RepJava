package pd19.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pd19.dto.LoanDto;
import pd19.entity.Loan;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class LoanMapper {

    public static LoanDto mapToLoanDto(Loan loan) {
        return new LoanDto(BookMapper.mapToBookDto(loan.getBook()),
                MemberMapper.mapToMemberDto(loan.getMember()),
                loan.getBorrowedAt(), loan.getDueDate(), loan.getReturnedAt());
    }
}
