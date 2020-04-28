package com.book.es.vo;

import org.apache.shiro.authc.UsernamePasswordToken;

import java.io.Serializable;

public class UserVO {
    private Integer id;

    private String userName;


    private String role;

    private String name;

    private String email;

    public UserVO(Integer id, String userName, String role, String name, String email) {
        this.id = id;
        this.userName = userName;
        this.role = role;
        this.name = name;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
}
