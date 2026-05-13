package pd19.service.ServInteface;

import pd19.dto.MemberDto;

public interface MemberService {
    void register(MemberDto memberDto);

    MemberDto findById(long id);
}
