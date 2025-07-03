package com.example.quan_ly_sinh_vien_codegym.Entity;

import java.time.LocalDate;

public class Class {
    int classId;
    String className;
    int moduleId;
    int courseId;
    String teacherId;
    LocalDate startDate;
    int quantityStudent;
    Boolean isDelete;

    public Class(int classId, String className, int moduleId, int courseId, String teacherId, LocalDate startDate, int quantityStudent, Boolean isDelete) {
        this.classId = classId;
        this.className = className;
        this.moduleId = moduleId;
        this.courseId = courseId;
        this.teacherId = teacherId;
        this.startDate = startDate;
        this.quantityStudent = quantityStudent;
        this.isDelete = isDelete;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getQuantityStudent() {
        return quantityStudent;
    }

    public void setQuantityStudent(int quantityStudent) {
        this.quantityStudent = quantityStudent;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }
}
