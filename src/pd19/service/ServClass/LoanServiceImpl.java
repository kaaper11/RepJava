package pd19.service.ServClass;

import lombok.AllArgsConstructor;
import pd19.dto.CreateLoanRequest;
import pd19.dto.LoanDto;
import pd19.dto.ReturnBookRequest;
import pd19.entity.Book;
import pd19.entity.Loan;
import pd19.entity.Member;
import pd19.exception.BookNotFoundException;
import pd19.exception.LoanLimitExceededException;
import pd19.exception.LoanNotFoundException;
import pd19.exception.MemberNotFoundException;
import pd19.mapper.LoanMapper;
import pd19.repository.BookRepository;
import pd19.repository.LoanRepository;
import pd19.repository.MemberRepository;
import pd19.service.ServInteface.LoanService;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
public class LoanServiceImpl implements LoanService {
    LoanRepository loanRepo;
    BookRepository bookRepo;
    MemberRepository memberRepo;

    @Override
    public void borrow(CreateLoanRequest createLoanRequest) {
        Member member = memberRepo.findById(createLoanRequest.memberId()).orElseThrow(MemberNotFoundException::new);
        Book book = bookRepo.getBookById(createLoanRequest.bookId()).orElseThrow(BookNotFoundException::new);
        book.borrow();
        if (member.canBorrow()) {
            Loan loan = new Loan(loanRepo.getNextId(), book, member, LocalDate.now(), LocalDate.now().plusDays(7));
            loanRepo.save(loan);
            member.borrow(loan);
        } else {
            throw new LoanLimitExceededException();
        }
    }

    @Override
    public void returnBook(ReturnBookRequest returnBookRequest) {
        Loan loan = loanRepo.findById(returnBookRequest.loanId()).orElseThrow(LoanNotFoundException::new);
        loan.returnBook();
        loan.getMember().returnBook(loan);
    }

    @Override
    public List<LoanDto> findOverdue() {
        return loanRepo.getOverdue().stream()
                .map(LoanMapper::mapToLoanDto)
                .toList();
    }

    @Override
    public List<LoanDto> getActiveLoans(long id) {
        return loanRepo.getActiveLoans(id).stream()
                .map(LoanMapper::mapToLoanDto)
                .toList();
    }
}
