package com.vodka.common.base.result;

import lombok.Getter;

/**
 * @author Breeze
 * @date 2023/4/26 21:39
 * @description
 */
@Getter
public enum Codes {

    SUCCESS(200, "success"),
    FAIL(500, "fail"),
    PARAM_ERROR(501, "param error"),
    FLOW_ERROR(429, "flow error"),
    DEGRADE_ERROR(430, "degrade error");

    private Integer code;
    private String msg;

    Codes(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
