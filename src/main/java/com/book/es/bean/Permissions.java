package com.book.es.bean;

public class Permissions {
    private Integer id;
    private String permissionsName;

    public Permissions(Integer id, String permissionsName) {
        this.id = id;
        this.permissionsName = permissionsName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionsName() {
        return permissionsName;
    }

    public void setPermissionsName(String permissionsName) {
        this.permissionsName = permissionsName;
    }

    @Override
    public String toString() {
        return "Permissions{" +
                "id=" + id +
                ", permissionsName='" + permissionsName + '\'' +
                '}';
    }
}