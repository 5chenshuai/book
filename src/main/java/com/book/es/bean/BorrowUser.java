package com.book.es.bean;

import java.util.Date;

public class BorrowUser {
    private String bookName;

    private Date shouldReturnDay;

    private String name;

    private String email;

    public BorrowUser() {
    }

    public BorrowUser(String bookName, Date shouldReturnDay, String name, String email) {
        this.bookName = bookName;
        this.shouldReturnDay = shouldReturnDay;
        this.name = name;
        this.email = email;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Date getShouldReturnDay() {
        return shouldReturnDay;
    }

    public void setShouldReturnDay(Date shouldReturnDay) {
        this.shouldReturnDay = shouldReturnDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "BorrowUser{" +
                "bookName='" + bookName + '\'' +
                ", shouldReturnDay=" + shouldReturnDay +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
