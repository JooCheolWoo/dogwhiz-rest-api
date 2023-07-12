package com.galaxypoby.dogwhiz.common;

import com.galaxypoby.dogwhiz.code.ErrorCode;
import com.galaxypoby.dogwhiz.code.MemberCode;
import com.galaxypoby.dogwhiz.member.entity.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Common {

    public static void checkStatus(Member member) throws CustomException {
        if (!member.getStatus().equals(MemberCode.Status.APPROVED.name())) {
            if(member.getStatus().equals(MemberCode.Status.PENDING.name())) {
                throw new CustomException(ErrorCode.MEMBER_STATUS_PENDING);
            }
            if (member.getStatus().equals(MemberCode.Status.SUSPENDED.name())) {
                throw new CustomException(ErrorCode.MEMBER_STATUS_SUSPENDED);
            }
            if (member.getStatus().equals(MemberCode.Status.DEACTIVATED.name())) {
                throw new CustomException(ErrorCode.MEMBER_STATUS_DEACTIVATED);
            }
        }
    }

    public static void checkAdmin(Member member) throws CustomException {
        if (member.getMemberRoles().stream().anyMatch(role -> role.getRole().startsWith("ADMIN_"))) {
            log.info("관리자 인증 완료");
        } else {
            throw new CustomException(ErrorCode.MEMBER_DENY_ACCESS);
        }
    }
}
