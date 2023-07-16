package com.galaxypoby.dogwhiz.common;

import com.galaxypoby.dogwhiz.code.ErrorCode;
import com.galaxypoby.dogwhiz.code.MemberCode;
import com.galaxypoby.dogwhiz.member.entity.Member;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public static void checkAuth(Member member, String auth) throws CustomException {
        Set<String> authList = new HashSet<>(Arrays.asList(auth.split("/")));

        if (member.getMemberRoles().stream().anyMatch(role -> authList.contains(role.getRole()))) {
            log.info("인증 완료");
        } else {
            throw new CustomException(ErrorCode.MEMBER_DENY_ACCESS);
        }
    }
}
