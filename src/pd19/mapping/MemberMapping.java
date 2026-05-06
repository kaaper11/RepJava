package pd19.mapping;

import pd19.dto.BookDto;
import pd19.dto.MemberDto;
import pd19.entity.Book;
import pd19.entity.Loan;
import pd19.entity.Member;

import java.util.ArrayList;

public class MemberMapping {

    public static MemberDto memberDtoMapping(Member member) {
        return new MemberDto(member.getName(), member.getEmail());
    }

    public static Member memberMapping(MemberDto memberDto, long id) {
        return new Member(id, memberDto.name(), memberDto.email());
    }
}
