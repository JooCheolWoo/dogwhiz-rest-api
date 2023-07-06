package com.galaxypoby.dogwhiz.login;

import com.galaxypoby.dogwhiz.code.ErrorCode;
import com.galaxypoby.dogwhiz.common.CustomException;
import com.galaxypoby.dogwhiz.common.CustomResponse;
import com.galaxypoby.dogwhiz.login.dto.RequestLoginDto;
import com.galaxypoby.dogwhiz.login.dto.ResponseLoginDto;
import com.galaxypoby.dogwhiz.member.entity.Member;
import com.galaxypoby.dogwhiz.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    
    public CustomResponse loginMember(RequestLoginDto request) throws CustomException {
        Member member = memberRepository.findByEmail(request.getEmail());

        if (member == null) {
            throw new CustomException(ErrorCode.MEMBER_NOT_EXIST);
        }

        if (passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new CustomException(ErrorCode.MEMBER_PASSWORD_NOT_MATCH);
        }

        member.login();

        memberRepository.save(member);

        ResponseLoginDto response = modelMapper.map(member, ResponseLoginDto.class);

        return new CustomResponse(ErrorCode.OK, response);
    }
}
