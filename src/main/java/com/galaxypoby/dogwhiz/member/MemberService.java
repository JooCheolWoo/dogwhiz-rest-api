package com.galaxypoby.dogwhiz.member;

import com.galaxypoby.dogwhiz.code.ErrorCode;
import com.galaxypoby.dogwhiz.code.RoleCode;
import com.galaxypoby.dogwhiz.code.StatusCode;
import com.galaxypoby.dogwhiz.common.CustomException;
import com.galaxypoby.dogwhiz.common.CustomResponse;
import com.galaxypoby.dogwhiz.common.fileManager.FileUpDown;
import com.galaxypoby.dogwhiz.member.dto.RequestMemberDto;
import com.galaxypoby.dogwhiz.member.dto.ResponseMemberDto;
import com.galaxypoby.dogwhiz.member.entity.Member;
import com.galaxypoby.dogwhiz.member.entity.MemberImage;
import com.galaxypoby.dogwhiz.member.entity.Role;
import com.galaxypoby.dogwhiz.member.repository.MemberRepository;
import com.galaxypoby.dogwhiz.member.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final FileUpDown fileUpDown;

    @Transactional
    public CustomResponse addMember(RequestMemberDto.SingUpDto request, MultipartFile file) throws CustomException {

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

        Member member = modelMapper.map(request, Member.class);
        member.setEncodedPwd(passwordEncoder.encode(member.getPassword()));

        Role role = roleRepository.findByRoleCodeAndStatusCode(RoleCode.USER_NORMAL, StatusCode.PENDING);

        member.updateRole(role);

        if (file != null) {
            Map<String ,String> path = fileUpDown.fileUpload("profile", file);
            MemberImage memberImage = new MemberImage(member, file, path);
            member.updateMemberImage(memberImage);
        }

        memberRepository.save(member);

        ResponseMemberDto.MemberDto response = modelMapper.map(member, ResponseMemberDto.MemberDto.class);

        return new CustomResponse(ErrorCode.OK, response);
    }

    public CustomResponse canUseEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isEmpty()) {
            return new CustomResponse(ErrorCode.OK);
        } else {
            return new CustomResponse(ErrorCode.MEMBER_EMAIL_DUPLICATION);
        }
    }

    public CustomResponse canUseNickname(String nickname) {
        Optional<Member> member = memberRepository.findByNickname(nickname);
        if (member.isEmpty()) {
            return new CustomResponse(ErrorCode.OK);
        } else {
            return new CustomResponse(ErrorCode.MEMBER_NICKNAME_DUPLICATION);
        }
    }

    public CustomResponse findMemberList() {
        List<Member> members = memberRepository.findAll();

        List<ResponseMemberDto.MemberDto> response = members.stream()
                .map(member -> modelMapper.map(member, ResponseMemberDto.MemberDto.class))
                .collect(Collectors.toList());

        return new CustomResponse(ErrorCode.OK, response);
    }

    public CustomResponse findMember(Long memberId) throws CustomException {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_EXIST));

        ResponseMemberDto.MemberDetailDto response = modelMapper.map(member, ResponseMemberDto.MemberDetailDto.class);

        return new CustomResponse(ErrorCode.OK, response);
    }

    @Transactional
    public CustomResponse modifyMember(Long memberId, RequestMemberDto.EditDto request) throws CustomException {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_EXIST));

        return new CustomResponse(ErrorCode.OK);
    }

    @Transactional
    public CustomResponse removeMember(Long memberId) throws CustomException {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_EXIST));

        member.leave();

        return new CustomResponse(ErrorCode.OK);
    }
}
