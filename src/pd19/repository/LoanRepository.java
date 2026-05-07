package pd19.repository;

import pd19.entity.Loan;

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

    public List<Loan> getActiveLoans(long memberId) {
        return loans.stream()
                .filter(loan -> loan.getMember().getId() == memberId && loan.isActive())
                .toList();
    }

    public long getNextId() {
        return idCounter++;
    }
}
