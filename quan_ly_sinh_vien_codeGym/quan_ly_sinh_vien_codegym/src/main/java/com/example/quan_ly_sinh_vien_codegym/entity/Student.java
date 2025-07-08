package com.example.quan_ly_sinh_vien_codegym.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Student {
    String studentId;
    String studentName;
    LocalDate dob;
    String gender;
    String address;
    String numberPhone;
    String email;
    LocalDate startLearnDate;
    String className;
    Boolean status;
    Boolean isDelete;

    public Student(String studentId, String studentName, LocalDate dob, String gender, String address, String numberPhone, String email, String className, int classId) {
    }

    public Student(String studentId, String studentName, LocalDate dob, String gender, String address, String numberPhone, String email, LocalDate startLearnDate, String className, Boolean status, Boolean isDelete) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.numberPhone = numberPhone;
        this.email = email;
        this.startLearnDate = startLearnDate;
        this.className = className;
        this.status = status;
        this.isDelete = isDelete;
    }

    public Student(String studentName, LocalDate dob, String gender, String address, String numberPhone, String email, LocalDate startLearnDate, String className) {
        this.studentName = studentName;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.numberPhone = numberPhone;
        this.email = email;
        this.startLearnDate = startLearnDate;
        this.className = className;
    }

    public Student(String studentId, String studentName, LocalDate dob, String gender, String address, String numberPhone, String email, LocalDate startLearnDate, String className) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.numberPhone = numberPhone;
        this.email = email;
        this.startLearnDate = startLearnDate;
        this.className = className;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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

    public LocalDate getStartLearnDate() {
        return startLearnDate;
    }

    public void setStartLearnDate(LocalDate startLearnDate) {
        this.startLearnDate = startLearnDate;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
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

    public String getDobFormatted() {
        return dob.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String getStartLearnDateFormatted() {
        return startLearnDate != null
                ? startLearnDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                : "";
    }
}