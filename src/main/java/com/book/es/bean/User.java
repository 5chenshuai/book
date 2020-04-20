package com.book.es.bean;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

public class User {

    private Integer id;

    @NotEmpty(message = "用户名不能为空")
    private String userName;

    @NotEmpty(message = "密码不能为空")
    @Size(min = 5,max = 10,message = "密码长度介于5-10之间")
    private String password;
    /**
     * 用户对应的角色集合
     */
    private Set<Role> roles;

    private String name;
    @Email
    private String email;

    public User() {
    }

    public User(Integer id, @NotEmpty(message = "用户名不能为空") String userName, @NotEmpty(message = "密码不能为空") @Size(min = 5, max = 10, message = "密码长度介于5-10之间") String password, Set<Role> roles, String name, String email) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.roles = roles;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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