package com.galaxypoby.dogwhiz.common;

import com.galaxypoby.dogwhiz.code.ErrorCode;
import com.galaxypoby.dogwhiz.code.MemberCode;
import com.galaxypoby.dogwhiz.member.entity.Member;

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
}
