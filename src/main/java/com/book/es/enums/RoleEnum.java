package com.book.es.enums;

public enum RoleEnum {

    ROLE_ADMIN(1,"admin"),

    BOOK_BOOKMANGER(2,"bookmanger"),

    BOOK_STUDENT(3,"student"),

    BOOK_GUEST(4,"guest");

    private final int code;

    private final String name;

    RoleEnum(int code, String name) {
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
