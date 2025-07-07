package com.example.quan_ly_sinh_vien_codegym.entity;

import java.time.LocalDate;

public class Teacher {
    String teacherId;
    String teacherName;
    LocalDate dob;
    String gender;
    String address;
    String numberPhone;
    String email;
    Boolean status;
    Boolean isDelete;

    public Teacher() {
    }

    public Teacher(String teacherId, String teacherName, LocalDate dob, String gender, String address, String numberPhone, String email, Boolean status, Boolean isDelete) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.numberPhone = numberPhone;
        this.email = email;
        this.status = status;
        this.isDelete = isDelete;
    }

    public Teacher(String teacherId, String teacherName, LocalDate dob, String gender, String address, String numberPhone, String email) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.numberPhone = numberPhone;
        this.email = email;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }
}
