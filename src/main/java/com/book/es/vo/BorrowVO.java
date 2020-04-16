package com.book.es.vo;

import com.book.es.bean.Borrow;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class BorrowVO {

    private Integer id;

    private String bookNumber;

    private String bookName;

    private Integer userId;

    private Long applyDay;

    private Long examineDay;

    private Long returnDay;

    private Integer status;



    public BorrowVO() {
    }

    public BorrowVO(Borrow borrow) {
        this.id = borrow.getId();
        this.bookNumber = borrow.getBookNumber();
        this.bookName = borrow.getBookName();
        this.userId = borrow.getUserId();
        this.applyDay = borrow.getApplyDay()==null?null:borrow.getApplyDay().getTime();
        this.examineDay = borrow.getExamineDay()==null?null:borrow.getExamineDay().getTime();
        this.returnDay = borrow.getReturnDay()==null?null:borrow.getReturnDay().getTime();
        this.status = borrow.getStatus();
    }

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

    public Long getApplyDay() {
        return applyDay;
    }

    public void setApplyDay(Long applyDay) {
        this.applyDay = applyDay;
    }

    public Long getExamineDay() {
        return examineDay;
    }

    public void setExamineDay(Long examineDay) {
        this.examineDay = examineDay;
    }

    public Long getReturnDay() {
        return returnDay;
    }

    public void setReturnDay(Long returnDay) {
        this.returnDay = returnDay;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


}
