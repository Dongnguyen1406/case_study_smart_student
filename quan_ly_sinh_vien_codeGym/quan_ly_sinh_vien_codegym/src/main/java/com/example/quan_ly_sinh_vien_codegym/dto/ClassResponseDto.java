package com.example.quan_ly_sinh_vien_codegym.dto;

import java.time.LocalDate;

public class ClassResponseDto {
    private int classId;
    private String className;
    private String moduleName;
    private String courseName;
    private String teacherName;
    private LocalDate startDate;
    private int quantity;
    private int moduleId;
    private int courseId;

    public ClassResponseDto() {
    }

    public ClassResponseDto(int classId, String className, String moduleName, String courseName, String teacherName, LocalDate startDate, int quantity) {
        this.classId = classId;
        this.className = className;
        this.moduleName = moduleName;
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.startDate = startDate;
        this.quantity = quantity;
    }

    public ClassResponseDto(String className, String moduleName, String courseName, String teacherName, LocalDate startDate, int quantity) {
        this.className = className;
        this.moduleName = moduleName;
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.startDate = startDate;
        this.quantity = quantity;
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

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
}
