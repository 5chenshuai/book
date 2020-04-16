package com.book.es.enums;

public enum  ErrorCode {
    OPT_UPLOAD_FILE(-1,"文件不存在"),

    UPLOAD_FAIL(-2,"上传失败");

    private final int code;

    private final String name;

    ErrorCode(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
