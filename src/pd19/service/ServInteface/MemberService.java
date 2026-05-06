package pd19.service.ServInteface;

import pd19.dto.LoanDto;
import pd19.dto.MemberDto;

import java.util.List;

public interface MemberService {
    void register(MemberDto memberDto);

    MemberDto findById(long id);

    List<LoanDto> getActiveLoans(long id);
}
