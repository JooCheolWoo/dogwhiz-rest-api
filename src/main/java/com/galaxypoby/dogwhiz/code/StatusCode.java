package com.galaxypoby.dogwhiz.code;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
public enum StatusCode {

    APPROVED,
    PENDING,
    SUSPENDED,
    DEACTIVATED;
}
