package com.galaxypoby.dogwhiz.member;

import com.galaxypoby.dogwhiz.common.CustomException;
import com.galaxypoby.dogwhiz.common.CustomResponse;
import com.galaxypoby.dogwhiz.member.dto.RequestMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping
    public CustomResponse memberAdd(@RequestBody RequestMemberDto.MemberDto request) throws CustomException {
        return memberService.addMember(request);
    }

    @GetMapping
    public CustomResponse memberList() {
        return memberService.findMemberList();
    }

    @GetMapping("/{memberId}")
    public CustomResponse memberDetail(@PathVariable(name = "memberId") Long memberId) throws CustomException {
        return memberService.findMember(memberId);
    }

//    @PatchMapping("/{memberId}")
//    public CustomResponse memberModify(@PathVariable(name = "memberId") Long memberId,
//                                       @RequestBody RequestMemberDto.EditMemberDto request) throws CustomException {
//        return memberService.modifyMember(memberId, request);
//    }
//
//    @DeleteMapping("/{memberId}")
//    public CustomResponse memberRemove(@PathVariable(name = "memberId") Long memberId) throws CustomException {
//        return memberService.removeMember(memberId);
//    }
}
