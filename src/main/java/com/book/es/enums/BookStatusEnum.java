package com.book.es.enums;

public enum BookStatusEnum {

    BOOK_ABLE(0,"可借"),

    BOOK_UNABLE(1,"不可借"),

    BOOK_DELETE(-1,"删除");

    private final int code;

    private final String name;

    BookStatusEnum(int code, String name) {
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
