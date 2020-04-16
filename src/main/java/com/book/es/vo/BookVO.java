package com.book.es.vo;

import com.book.es.bean.Book;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class BookVO {

    private Integer id;

    private String bookName;

    private String author;

    private String press;

    private String floor;

    private String bookshelf;

    private String bookNumber;

    private Integer status;

    private String edition;

    private String describe;

    private String picture;

    private Long createdTime;

    public BookVO(Book book) {
        this.id = book.getId();
        this.bookName = book.getBookName();
        this.author = book.getAuthor();
        this.press = book.getPress();
        this.floor = book.getFloor();
        this.bookshelf = book.getBookshelf();
        this.bookNumber = book.getBookNumber();
        this.status = book.getStatus();
        this.edition = book.getEdition();
        this.describe = book.getDescribe();
        this.picture = book.getPicture();
        this.createdTime = book.getCreatedTime()==null?null:book.getCreatedTime().getTime();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getBookshelf() {
        return bookshelf;
    }

    public void setBookshelf(String bookshelf) {
        this.bookshelf = bookshelf;
    }

    public String getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(String bookNumber) {
        this.bookNumber = bookNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", press='" + press + '\'' +
                ", floor='" + floor + '\'' +
                ", bookshelf='" + bookshelf + '\'' +
                ", bookNumber='" + bookNumber + '\'' +
                ", status=" + status +
                ", edition='" + edition + '\'' +
                ", describe='" + describe + '\'' +
                ", picture='" + picture + '\'' +
                ", createdTime=" + createdTime +
                '}';
    }
}