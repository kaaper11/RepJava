package pd19.repository;

import pd19.entity.Loan;
import pd19.exception.LoanNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoanRepository {
    private List<Loan> loans = new ArrayList<>();
    private long idCounter = 0;

    public void save(Loan loan) {
        loans.add(loan);
    }

    public Optional<Loan> findById(long id) {
        return loans.stream()
                .filter(loan -> loan.getId() == id)
                .findFirst();
    }

    public List<Loan> getOverdue() {
        return loans.stream()
                .filter(Loan::isOverdue)
                .toList();
    }

    public long getId() {
        return idCounter++;
    }
}
