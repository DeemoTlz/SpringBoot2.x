package com.albrus.common.model;

import com.albrus.common.utils.ErrorCodeConsts;

public class SuccessRtn extends Rtn {
    public SuccessRtn() {
        this(ErrorCodeConsts.BUSINESS_SUCCESS.getCode(), ErrorCodeConsts.BUSINESS_SUCCESS.getLabel(), null, null);
    }

    public SuccessRtn(String msg) {
        this(ErrorCodeConsts.BUSINESS_SUCCESS.getCode(), msg, null, null);
    }

    public <T> SuccessRtn(String msg, T data) {
        this(ErrorCodeConsts.BUSINESS_SUCCESS.getCode(), msg, data, null);
    }

    public <T> SuccessRtn(String msg, T data, String detail) {
        this(ErrorCodeConsts.BUSINESS_SUCCESS.getCode(), msg, data, detail);
    }

    private <T> SuccessRtn(int code, String msg, T data, String detail) {
        this.setCode(code);
        this.setMsg(msg);
        this.setData(data);
        this.setDetail(detail);
    }
}
