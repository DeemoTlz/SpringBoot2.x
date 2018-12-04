package com.albrus.common.model;

import com.albrus.common.utils.ErrorCodeConsts;

public class ErrorRtn extends Rtn {
    public ErrorRtn() {
        this(ErrorCodeConsts.BUSINESS_ERROR.getCode(), ErrorCodeConsts.BUSINESS_ERROR.getLabel(), null, null);
    }

    public ErrorRtn(int code) {
        this(code, null, null, null);
    }

    public ErrorRtn(String msg) {
        this(ErrorCodeConsts.BUSINESS_ERROR.getCode(), msg, null, null);
    }

    public ErrorRtn(int code, String msg) {
        this(code, msg, null, null);
    }

    public <T> ErrorRtn(String msg, T data) {
        this(ErrorCodeConsts.BUSINESS_ERROR.getCode(), msg, data, null);
    }

    public <T> ErrorRtn(String msg, T data, String detail) {
        this(ErrorCodeConsts.BUSINESS_ERROR.getCode(), msg, data, detail);
    }

    private <T> ErrorRtn(int code, String msg, T data, String detail) {
        this.setCode(code);
        this.setMsg(msg);
        this.setData(data);
        this.setDetail(detail);
    }
}
