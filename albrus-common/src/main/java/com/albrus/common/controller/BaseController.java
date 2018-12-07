package com.albrus.common.controller;

import com.albrus.common.model.ErrorRtn;
import com.albrus.common.model.Rtn;
import com.albrus.common.model.SuccessRtn;

public abstract class BaseController {

    public Rtn success() {
        return new SuccessRtn();
    }

    public Rtn success(String msg) {
        return new SuccessRtn(msg);
    }

    public <T> Rtn success(String msg, T data) {
        return new SuccessRtn(msg, data);
    }

    public <T> Rtn success(String msg, T data, String detail) {
        return new SuccessRtn(msg, data, detail);
    }

    public Rtn fail() {
        return new ErrorRtn();
    }

    public Rtn fail(int code) {
        return new ErrorRtn(code);
    }

    public Rtn fail(String msg) {
        return new ErrorRtn(msg);
    }

    public Rtn fail(int code, String msg) {
        return new ErrorRtn(code, msg);
    }

    public <T> Rtn fail(String msg, T data) {
        return new ErrorRtn(msg, data);
    }

    public <T> Rtn fail(String msg, T data, String detail) {
        return new ErrorRtn(msg, data, detail);
    }
}
