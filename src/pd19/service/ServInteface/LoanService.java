package pd19.service.ServInteface;

import pd19.dto.CreateLoanRequest;
import pd19.dto.LoanDto;
import pd19.dto.ReturnBookRequest;

import java.util.List;

public interface LoanService {
    void borrow(CreateLoanRequest createLoanRequest);

    void returnBook(ReturnBookRequest returnBookRequest);

    List<LoanDto> findOverdue();
}
