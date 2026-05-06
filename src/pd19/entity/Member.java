package pd19.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class Member {
    private long id;
    private String name;
    private String email;
    private List<Loan> loans;

    public Member(long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.loans = new ArrayList<Loan>();
    }

    public boolean canBorrow() {
        return loans.size() < 3 ? true : false;
    }

    public void borrow(Loan loan) {
        loans.add(loan);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(email, member.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }
}
