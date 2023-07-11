package com.galaxypoby.dogwhiz.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class MemberCode {

    @AllArgsConstructor
    public enum Role {

        ADMIN_MASTER,
        ADMIN_MANAGER,
        ADMIN_NORMAL,

        USER_SPECIAL,
        USER_CERTIFIED,
        USER_NORMAL,

        SELLER_CORPORATION,
        SELLER_PERSONAL,
        SELLER_NORMAL;

    }

    @AllArgsConstructor
    public enum Status {
        APPROVED,
        PENDING,
        SUSPENDED,
        DEACTIVATED;
    }
}
