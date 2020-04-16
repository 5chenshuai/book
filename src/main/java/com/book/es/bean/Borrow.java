package com.book.es.bean;


import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class Borrow {

    private Integer id;

    @NotEmpty(message = "编号不能为空")
    private String bookNumber;

    @NotEmpty(message = "书名不能为空")
    private String bookName;

    private Integer userId;

    private Date applyDay;

    private Date examineDay;

    private Date returnDay;

    private Integer status;

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

    public Date getApplyDay() {
        return applyDay;
    }

    public void setApplyDay(Date applyDay) {
        this.applyDay = applyDay;
    }

    public Date getExamineDay() {
        return examineDay;
    }

    public void setExamineDay(Date examineDay) {
        this.examineDay = examineDay;
    }

    public Date getReturnDay() {
        return returnDay;
    }

    public void setReturnDay(Date returnDay) {
        this.returnDay = returnDay;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "id=" + id +
                ", bookNumber='" + bookNumber + '\'' +
                ", bookName='" + bookName + '\'' +
                ", userId=" + userId +
                ", applyDay=" + applyDay +
                ", examineDay=" + examineDay +
                ", returnDay=" + returnDay +
                ", status=" + status +
                '}';
    }
}
