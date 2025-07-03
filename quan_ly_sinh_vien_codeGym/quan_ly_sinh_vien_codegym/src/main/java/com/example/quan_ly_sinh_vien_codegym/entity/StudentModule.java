package com.example.quan_ly_sinh_vien_codegym.entity;

import java.time.LocalDate;

public class StudentModule {
    int studentModuleId;
    String studentId;
    int moduleId;
    LocalDate registrationDate;
    Boolean status;

    public StudentModule(int studentModuleId, String studentId, int moduleId, LocalDate registrationDate, Boolean status) {
        this.studentModuleId = studentModuleId;
        this.studentId = studentId;
        this.moduleId = moduleId;
        this.registrationDate = registrationDate;
        this.status = status;
    }

    public int getStudentModuleId() {
        return studentModuleId;
    }

    public void setStudentModuleId(int studentModuleId) {
        this.studentModuleId = studentModuleId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
