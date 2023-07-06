package com.galaxypoby.dogwhiz.code;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum RoleCode {
    /**
     * 관리자
     */
    ADMIN_MASTER,
    ADMIN_MANAGER,
    ADMIN_NORMAL,

    /**
     * 판매자
     */
    SELLER_CORPORATE,
    SELLER_INDIVIDUAL,

    /**
     * 회원
     */
    USER_SPECIAL,
    USER_CERTIFIED,
    USER_NORMAL;
}
