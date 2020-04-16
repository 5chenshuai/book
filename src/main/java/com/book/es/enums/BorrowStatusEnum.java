package com.book.es.enums;

public enum BorrowStatusEnum {

    BORROWING_CREATED(0,"申请借书"),

    BORROWING_ABLE(1,"批准借书"),

    BORROWING_UNABLE(2,"回绝借书"),

    BORROWING_RETURN(3,"已归还"),

    BORROWING_CANCEL(4,"撤销申请"),

    BORROWING_WAIT(-1,"等待");

    private final int code;

    private final String name;

    BorrowStatusEnum(int code, String name) {
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
