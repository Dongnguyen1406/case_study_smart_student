package com.example.quan_ly_sinh_vien_codegym.dto;

import java.time.LocalDate;

public class StudentDto { 
    private String studentId;
    private String studentName;
    private LocalDate dob;
    private String gender;
    private String address;
    private String numberPhone;
    private String email;
    private Integer classId;
    private LocalDate startLearnDate;
    private String className;

    public StudentDto(String id, String fullName, LocalDate parsedDob, String gender, String address, String email, String phone, int classId) {
    }

    public StudentDto(String studentId, String studentName, LocalDate dob, String gender, String address, String numberPhone, String email, Integer classId, LocalDate startLearnDate, String className) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.numberPhone = numberPhone;
        this.email = email;
        this.classId = classId;
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

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
