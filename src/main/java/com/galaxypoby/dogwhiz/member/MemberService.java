package com.galaxypoby.dogwhiz.member;

import com.galaxypoby.dogwhiz.code.ErrorCode;
import com.galaxypoby.dogwhiz.common.CustomException;
import com.galaxypoby.dogwhiz.common.CustomResponse;
import com.galaxypoby.dogwhiz.member.dto.RequestMemberDto;
import com.galaxypoby.dogwhiz.member.dto.ResponseMemberDto;
import com.galaxypoby.dogwhiz.member.entity.Member;
import com.galaxypoby.dogwhiz.member.entity.MemberMapper;
import com.galaxypoby.dogwhiz.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public CustomResponse addMember(RequestMemberDto.MemberDto request) throws CustomException {

        // 이메일 중복검사 (이미 존재하면 false)
        if (!(canUseEmail(request.getEmail()).getStatus() == 0)) {
            throw new CustomException(ErrorCode.MEMBER_EMAIL_DUPLICATION);
        }

        // 닉네임 중복검사 (이미 존재하면 false)
        if (!(canUseNickname(request.getNickname()).getStatus() == 0)) {
            throw new CustomException(ErrorCode.MEMBER_NICKNAME_DUPLICATION);
        }

        // 비밀번호와 비밀번호 확인 일치검사
        if (!request.getPassword().equals(request.getRePassword())) {
            throw new CustomException(ErrorCode.MEMBER_PASSWORD_NOT_MATCH);
        }

        Member member = MemberMapper.INSTANCE.requestMemberDtotoMember(request);
        member.encodePwd(passwordEncoder.encode(member.getPassword()));

        memberRepository.save(member);

        ResponseMemberDto.MemberDto response = MemberMapper.INSTANCE.toResponseMemberDto(member);

        return new CustomResponse(ErrorCode.OK, response);
    }

    public CustomResponse canUseEmail(String email) {
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            return new CustomResponse(ErrorCode.OK);
        } else {
            return new CustomResponse(ErrorCode.MEMBER_EMAIL_DUPLICATION);
        }
    }

    public CustomResponse canUseNickname(String nickname) {
        Member member = memberRepository.findByNickname(nickname);
        if (member == null) {
            return new CustomResponse(ErrorCode.OK);
        } else {
            return new CustomResponse(ErrorCode.MEMBER_NICKNAME_DUPLICATION);
        }
    }

    public CustomResponse findMemberList() {
        List<Member> members = memberRepository.findAll();
        List<ResponseMemberDto.MemberDto> response = MemberMapper.INSTANCE.toResponseMemberDtoList(members);

        return new CustomResponse(ErrorCode.OK, response);
    }

    public CustomResponse findMember(Long memberId) throws CustomException {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_EXIST));

        ResponseMemberDto.MemberDto response = MemberMapper.INSTANCE.toResponseMemberDto(member);

        return new CustomResponse(ErrorCode.OK, response);
    }

    @Transactional
    public CustomResponse modifyMember(Long memberId, RequestMemberDto.EditMemberDto request) throws CustomException {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_EXIST));

        if (request.getPassword() != null &&
                !passwordEncoder.matches(member.getPassword(), request.getPassword())) {
            if (!request.getPassword().equals(request.getRePassword())) {
                throw new CustomException(ErrorCode.MEMBER_PASSWORD_NOT_MATCH);
            }
            member.updatePwd(passwordEncoder.encode(request.getPassword()));
        }

        if (request.getNickname() != null &&
                !member.getNickname().equals(request.getNickname())) {
            if (!(canUseNickname(request.getNickname()).getStatus() == 0)) {
                throw new CustomException(ErrorCode.MEMBER_NICKNAME_DUPLICATION);
            }
            member.updateNickname(request.getNickname());
        }

        ResponseMemberDto.MemberDto response = MemberMapper.INSTANCE.toResponseMemberDto(member);

        return new CustomResponse(ErrorCode.OK, response);
    }

    @Transactional
    public CustomResponse removeMember(Long memberId) throws CustomException {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_EXIST));

        member.delete();

        return new CustomResponse(ErrorCode.OK);
    }
}
