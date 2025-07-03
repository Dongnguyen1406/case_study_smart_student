package com.example.quan_ly_sinh_vien_codegym.entity;

public class Role {
    int roleId;
    String statusName;

    public Role(int roleId, String statusName) {
        this.roleId = roleId;
        this.statusName = statusName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
