package com.book.es.bean;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Document(indexName = "bookmanger",type = "book")
public class Book {

    @Id
    private Integer id;

    @NotEmpty(message = "书名不能为空")
    private String bookName;

    @NotEmpty(message = "作者不能为空")
    private String author;

    @NotEmpty(message = "出版社不能为空")
    private String press;

    @NotEmpty(message = "楼层不能为空")
    private String floor;

    @NotEmpty(message = "书架不能为空")
    private String bookshelf;

    @NotEmpty(message = "编号不能为空")
    private String bookNumber;

    private Integer status;

    @NotEmpty(message = "版本不能为空")
    private String edition;

    @NotEmpty(message = "简介不能为空")
    private String describe;

    private String picture;

    private Date createdTime;

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

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
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
