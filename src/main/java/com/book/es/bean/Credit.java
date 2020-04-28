package com.book.es.bean;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class Credit {

    private Integer id;

    private String bookNumber;

    private String bookName;

    private Integer userId;

    private Date examineDay;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(String bookNumber) {
        this.bookNumber = bookNumber;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getExamineDay() {
        return examineDay;
    }

    public void setExamineDay(Date examineDay) {
        this.examineDay = examineDay;
    }
}
