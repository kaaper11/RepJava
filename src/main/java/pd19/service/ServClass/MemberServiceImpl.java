package pd19.service.ServClass;

import lombok.AllArgsConstructor;
import pd19.dto.MemberDto;
import pd19.entity.Member;
import pd19.exception.MemberNotFoundException;
import pd19.mapper.MemberMapper;
import pd19.repository.MemberRepository;
import pd19.service.ServInteface.MemberService;

@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
    MemberRepository memberRepository;

    @Override
    public void register(MemberDto memberDto) {
        memberRepository.save(MemberMapper.mapToMember(memberDto, memberRepository.getNextId()));
    }

    @Override
    public MemberDto findById(long id) {
        Member member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
        return MemberMapper.mapToMemberDto(member);
    }
}
