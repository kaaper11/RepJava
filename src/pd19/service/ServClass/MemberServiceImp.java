package pd19.service.ServClass;

import pd19.dto.LoanDto;
import pd19.dto.MemberDto;
import pd19.entity.Member;
import pd19.exception.MemberNotFoundException;
import pd19.mapping.LoanMapping;
import pd19.mapping.MemberMapping;
import pd19.repository.MemberRepository;
import pd19.service.ServInteface.MemberService;

import java.util.List;

public class MemberServiceImp implements MemberService {
    MemberRepository memberRepository;

    public MemberServiceImp(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void register(MemberDto memberDto) {
        memberRepository.save(MemberMapping.memberMapping(memberDto, memberRepository.getId()));
    }

    @Override
    public MemberDto findById(long id) {
        Member member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
        return MemberMapping.memberDtoMapping(member);
    }

    @Override
    public List<LoanDto> getActiveLoans(long id) {
        return memberRepository.getActiveLoans(id).stream()
                .map(LoanMapping::loanDtoMapping)
                .toList();
    }
}
