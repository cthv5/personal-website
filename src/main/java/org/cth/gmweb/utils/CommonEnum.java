package org.cth.gmweb.utils;

/**
 * 返回code枚举
 * @author cth
 * @date 2019/06/17
 */
public enum CommonEnum {
    SUCCESS(0),
    FAILURE(-1),
    PAGENOTFOUND(404),
    UNAUTHORIZED(403),
    SERVERERROR(500);

    private int code;

    CommonEnum(int code) {
        this.code = code;
    }

    public int getValue() {
        return this.code;
    }
}