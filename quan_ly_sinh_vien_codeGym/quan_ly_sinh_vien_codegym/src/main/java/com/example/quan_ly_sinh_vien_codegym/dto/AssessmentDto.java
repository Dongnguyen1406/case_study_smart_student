package com.example.quan_ly_sinh_vien_codegym.dto;

public class AssessmentDto {
    private int assessmentId;
    private double averageScore;
    private String studentId;
    private int moduleId;
    private String moduleName;
    private boolean status;

    public AssessmentDto() {
    }

    public AssessmentDto(int assessmentId, double averageScore, String studentId, int moduleId, String moduleName, boolean status) {
        this.assessmentId = assessmentId;
        this.averageScore = averageScore;
        this.studentId = studentId;
        this.moduleId = moduleId;
        this.moduleName = moduleName;
        this.status = status;
    }

    public int getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(int assessmentId) {
        this.assessmentId = assessmentId;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
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

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
} 