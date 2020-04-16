package com.book.es.web;

import java.text.MessageFormat;

public interface BaseController {

    default  WebResponse ok() {
        return new WebResponse().setMsg("success");
    }

    default WebResponse ok(Object data) {
        return new WebResponse().setData(data);
    }

    default WebResponse defaultErr() {
        return new WebResponse().setCode(-1);
    }

    default WebResponse defaultErr(String msg) {
        return defaultErr().setMsg(msg);
    }

    default WebResponse defaultErrArgs(String msg, Object... args) {
        return defaultErr().setMsg(MessageFormat.format(msg, args));
    }

    default WebResponse err(int code) {
        return new WebResponse().setCode(code);
    }

    default WebResponse err(int code, String msg) {
        return err(code).setMsg(msg);
    }
}
