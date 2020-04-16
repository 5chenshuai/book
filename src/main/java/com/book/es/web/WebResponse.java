/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.book.es.web;

/**
 * @author bettercp3
 * @version $Id: WebResponse, v0.1 2018-01-11 20:54 bettercp3 Exp $
 */
public class WebResponse {

    private String msg;
    private int code = 0;

    private Object data;

    public String getMsg() {
        return msg;
    }

    public WebResponse setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public int getCode() {
        return code;
    }

    public WebResponse setCode(int code) {
        this.code = code;
        return this;
    }

    public Object getData() {
        return data;
    }

    public WebResponse setData(Object data) {
        this.data = data;
        return this;
    }
}