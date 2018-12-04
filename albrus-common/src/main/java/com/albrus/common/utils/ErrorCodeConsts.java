package com.albrus.common.utils;

public enum ErrorCodeConsts {
    BUSINESS_SUCCESS(0, "操作成功"),
    BUSINESS_ERROR(1, "操作失败");

    private int code;
    private String label;

    public int getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    ErrorCodeConsts(int code, String label) {
        this.code = code;
        this.label = label;
    }
}
