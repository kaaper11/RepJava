package pd19.service.ServClass;

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
import pd19.mapping.LoanMapping;
import pd19.repository.BookRepository;
import pd19.repository.LoanRepository;
import pd19.repository.MemberRepository;
import pd19.service.ServInteface.LoanService;

import java.time.LocalDate;
import java.util.List;

public class LoanServiceImp implements LoanService {
    LoanRepository loanRepo;
    BookRepository bookRepo;
    MemberRepository memberRepo;

    public LoanServiceImp(LoanRepository loanRepo, BookRepository bookRepo, MemberRepository memberRepo) {
        this.loanRepo = loanRepo;
        this.bookRepo = bookRepo;
        this.memberRepo = memberRepo;
    }

    @Override
    public void borrow(CreateLoanRequest createLoanRequest) {
        Member member = memberRepo.findById(createLoanRequest.memberId()).orElseThrow(MemberNotFoundException::new);
        Book book = bookRepo.getBookById(createLoanRequest.bookId()).orElseThrow(BookNotFoundException::new);
        book.borrow();
        if (member.canBorrow()) {
            loanRepo.save(new Loan(loanRepo.getId(), book, member, LocalDate.now(), LocalDate.now().plusDays(7), null));
        } else {
            throw new LoanLimitExceededException();
        }
    }

    @Override
    public void returnBook(ReturnBookRequest returnBookRequest) {
        loanRepo.findById(returnBookRequest.loanId()).orElseThrow(LoanNotFoundException::new).returnBook();
    }

    @Override
    public List<LoanDto> findOverdue() {
        return loanRepo.getOverdue().stream()
                .map(LoanMapping::loanDtoMapping)
                .toList();
    }
}
