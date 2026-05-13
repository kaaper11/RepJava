package pd19.repository;

import pd19.entity.Member;
import pd19.exception.MemberHaveAlreadyAccountException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class MemberRepository {
    private Set<Member> members = new HashSet<>();
    private long idCounter = 0;

    public void save(Member member) {
        if (!members.contains(member)) {
            members.add(member);
        } else {
            throw new MemberHaveAlreadyAccountException();
        }
    }

    public Optional<Member> findById(long id) {
        return members.stream()
                .filter(member -> member.getId() == id)
                .findFirst();
    }

    public long getNextId() {
        return idCounter++;
    }
}
