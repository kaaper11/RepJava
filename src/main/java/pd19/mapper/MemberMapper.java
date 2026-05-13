package pd19.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pd19.dto.MemberDto;
import pd19.entity.Member;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MemberMapper {

    public static MemberDto mapToMemberDto(Member member) {
        return new MemberDto(member.getName(), member.getEmail());
    }

    public static Member mapToMember(MemberDto memberDto, long id) {
        return new Member(id, memberDto.name(), memberDto.email());
    }
}
