package com.galaxypoby.dogwhiz.member;

import com.galaxypoby.dogwhiz.common.CustomException;
import com.galaxypoby.dogwhiz.common.CustomResponse;
import com.galaxypoby.dogwhiz.member.dto.RequestMemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping
    public CustomResponse memberAdd(@RequestPart(name = "request") RequestMemberDto.SingUpDto request,
                                    @RequestPart(name = "file", required = false) MultipartFile file) throws CustomException {
        return memberService.addMember(request, file);
    }

    @GetMapping
    public CustomResponse memberList() {
        return memberService.findMemberList();
    }

    @GetMapping("/{memberId}")
    public CustomResponse memberDetail(@PathVariable(name = "memberId") Long memberId) throws CustomException {
        return memberService.findMember(memberId);
    }

    @PatchMapping("/{memberId}")
    public CustomResponse memberModify(@PathVariable(name = "memberId") Long memberId,
                                       @RequestBody RequestMemberDto.EditDto request) throws CustomException {
        return memberService.modifyMember(memberId, request);
    }

    @DeleteMapping("/{memberId}")
    public CustomResponse memberRemove(@PathVariable(name = "memberId") Long memberId) throws CustomException {
        return memberService.removeMember(memberId);
    }

    @GetMapping("/valid/email")
    public CustomResponse emailValid(@RequestParam("email") String email) {
        return memberService.emailDuplicationCheck(email);
    }

    @GetMapping("/valid/nickname")
    public CustomResponse nicknameValid(@RequestParam("nickname") String nickname) {
        return memberService.nicknameDuplicationCheck(nickname);
    }

    @GetMapping("/verification")
    public CustomResponse emailVerify(@RequestParam("email") String email,
                                      @RequestParam("emailKey") String emailKey) throws CustomException {
        return memberService.verifyEmail(email, emailKey);
    }
}
